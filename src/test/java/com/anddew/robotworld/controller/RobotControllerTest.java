package com.anddew.robotworld.controller;

import com.anddew.robotworld.Orchestra;
import com.anddew.robotworld.Repertoire;
import com.anddew.robotworld.history.HistoryHolder;
import com.anddew.robotworld.model.Song;
import com.anddew.robotworld.model.robot.Robot;
import com.anddew.robotworld.model.robot.RobotFactory;
import com.anddew.robotworld.model.robot.RobotType;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class RobotControllerTest {

    private static final String ROBOT_NAME_PARAMETER = "name";
    private static final String ROBOT_NAME = "validName";
    private static final String ROBOT_TYPE_PARAMETER = "type";
    private static final RobotType ROBOT_TYPE = RobotType.GUITAR;
    private static final String SONG_TITLE_PARAMETER = "title";

    private RobotController robotController;
    private Map<String, String> parameters = new HashMap<>();
    private Robot robot;
    private Song song;
    private Orchestra orchestra;
    private Repertoire repertoire;
    private RobotFactory robotFactory;
    private HistoryHolder historyHolder;

    public RobotControllerTest() {
        robotController = new RobotController();
        robot = mock(Robot.class);
        when(robot.getName()).thenReturn(ROBOT_NAME);
        song = mock(Song.class);
        orchestra = mock(Orchestra.class);
        Whitebox.setInternalState(robotController, "orchestra", orchestra);
        repertoire = mock(Repertoire.class);
        Whitebox.setInternalState(robotController, "repertoire", repertoire);
        robotFactory = mock(RobotFactory.class);
        Whitebox.setInternalState(robotController, "robotFactory", robotFactory);
        historyHolder = mock(HistoryHolder.class);
        Whitebox.setInternalState(robotController, "historyHolder", historyHolder);
    }

    @Test
    public void testGetFreeRobots() {
        robotController.getFreeRobots();
        verify(orchestra).getAll();
        verifyNoMoreInteractions(orchestra, repertoire, robotFactory, historyHolder);
    }

    @Test
    public void testRegisterRobotEmptyNameMethod() {
        parameters.put("name", "");
        ResponseEntity responseEntity = robotController.registerRobot(parameters);
        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRegisterRobotMethod() {
        parameters.put(ROBOT_NAME_PARAMETER, ROBOT_NAME);
        parameters.put(ROBOT_TYPE_PARAMETER, ROBOT_TYPE.name());
        when(robotFactory.create(ROBOT_NAME, ROBOT_TYPE)).thenReturn(robot);

        ResponseEntity responseEntity = robotController.registerRobot(parameters);
        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void testRegisterRobotFailed() {
        when(robotFactory.create(ROBOT_NAME, ROBOT_TYPE)).thenThrow(new RuntimeException());
        ResponseEntity responseEntity = robotController.registerRobot(parameters);
        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveRobot() {
        ResponseEntity responseEntity = robotController.removeRobot(parameters);
        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void testPlay() {
        parameters.put(ROBOT_NAME_PARAMETER, ROBOT_NAME);
        parameters.put(ROBOT_TYPE_PARAMETER, ROBOT_TYPE.name());

        String songTitle = "someSong";
        parameters.put(SONG_TITLE_PARAMETER, songTitle);
        when(repertoire.getSong(songTitle)).thenReturn(song);

        when(orchestra.retrieve(ROBOT_NAME)).thenReturn(robot);

        String musicalPart = "validMusicalPart";
        when(robot.play(song)).thenReturn(musicalPart);

        ResponseEntity responseEntity = robotController.play(parameters);
        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void testPlayFailed() {
        ResponseEntity responseEntity = robotController.play(parameters);
        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testBroadcast() {
        String songTitle = "someSong";
        parameters.put(SONG_TITLE_PARAMETER, songTitle);
        when(repertoire.getSong(songTitle)).thenReturn(song);
        robotController.broadcast(parameters);
    }

}