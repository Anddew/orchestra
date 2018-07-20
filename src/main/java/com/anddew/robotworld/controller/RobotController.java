package com.anddew.robotworld.controller;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anddew.robotworld.history.HistoryHolder;
import com.anddew.robotworld.model.Repertoire;
import com.anddew.robotworld.model.RoboticOrchestra;
import com.anddew.robotworld.model.Song;
import com.anddew.robotworld.model.robot.Robot;
import com.anddew.robotworld.model.robot.RobotFactory;
import com.anddew.robotworld.model.robot.RobotType;


/**
 * Robot requests REST-controller.
 *
 * @author Anddew
 */
@RestController
@RequestMapping("/robot")
public class RobotController {

    private final static Logger LOGGER = Logger.getLogger(RobotController.class);

    private static final String ROBOT_NAME_PARAMETER = "name";
    private static final String ROBOT_TYPE_PARAMETER = "type";
    private static final String SONG_TITLE_PARAMETER = "title";

    @Autowired
    private RoboticOrchestra orchestra;

    @Autowired
    private Repertoire repertoire;

    @Autowired
    private RobotFactory robotFactory;

    @Autowired
    private HistoryHolder historyHolder;


    /**
     * Update free robots collection.
     *
     * @return collection of free robots
     */
    @GetMapping("/free")
    public Collection<Robot> getFreeRobots() {
        return orchestra.getAll();
    }

    /**
     * Register new robot.
     *
     * @param parameters request parameters with robot data
     * @return {@link HttpStatus#OK} if registration successful
     *          or {@link HttpStatus#BAD_REQUEST} if attempt to register robot was failed
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerRobot(@RequestBody Map<String, String> parameters) {
        String name = parameters.get(ROBOT_NAME_PARAMETER);
        String type = parameters.get(ROBOT_TYPE_PARAMETER);
        if (name == null || name.isEmpty()) {
            LOGGER.info("Error. Name is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input name!");
        }

        try {
            Robot robot = robotFactory.create(name, RobotType.valueOf(type));
            orchestra.register(robot);
            historyHolder.add("New robot '" + robot + "' was created.");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            LOGGER.error("Error during robot registration.", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Remove robot.
     *
     * @param parameters request parameters with robot name to remove
     * @return {@link HttpStatus#OK} if removing successful
     *          or {@link HttpStatus#BAD_REQUEST} if attempt to remove robot was failed
     */
    @PostMapping("/remove")
    public ResponseEntity<String> removeRobot(@RequestBody Map<String, String> parameters) {
        try {
            String name = parameters.get(ROBOT_NAME_PARAMETER);
            orchestra.remove(name);
            historyHolder.add("Robot '" + name + "' was removed.");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            LOGGER.error("Error during robot removing.", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    /**
     * Assign task to robot.
     *
     * @param parameters request parameters with robot name and song title to play
     * @return {@link HttpStatus#OK} if playing successful
     *          or {@link HttpStatus#BAD_REQUEST} if attempt to play was failed
     */
    @PostMapping("/play")
    public ResponseEntity<String> play(@RequestBody Map<String, String> parameters) {
        Song song = null;
        Robot robot = null;
        try {
            String title = parameters.get(SONG_TITLE_PARAMETER);
            song = repertoire.getSong(title);
            String name = parameters.get(ROBOT_NAME_PARAMETER);
            robot = orchestra.retrieve(name);

            historyHolder.add("New task - '" + name + "' plays '" + song + "'.");
            String musicalPart = robot.play(song);
            historyHolder.add("Robot '" + robot + "': '" + musicalPart + "'.");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            LOGGER.error("Error during robot playing.", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } finally {
            if (robot != null) {
                orchestra.release(robot);
            }
        }
    }

    /**
     * Assign task to all free robots.
     *
     * @param parameters song title to play
     */
    @PostMapping("/broadcast")
    public void broadcast(@RequestBody Map<String, String> parameters) {
        Song song = repertoire.getSong(parameters.get(SONG_TITLE_PARAMETER));
        Collection<Robot> freeRobots = orchestra.retrieveAll();

        for(Robot robot : freeRobots) {
            new Thread(()->{
                try {
                    historyHolder.add("New task - '" + robot.getName() + "' plays '" + song + "'.");
                    String musicalPart = robot.play(song);
                    historyHolder.add("Robot '" + robot + "': '" + musicalPart + "'.");
                } catch (RuntimeException e) {
                    LOGGER.error("Error during robot playing.", e);

                } finally {
                    if (robot != null) {
                        orchestra.release(robot);
                    }
                }
            }).start();
        }
    }

}