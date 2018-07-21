package com.anddew.robotworld;

import com.anddew.robotworld.model.robot.Robot;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrchestraTest {

    private static final String MOCK_ROBOT_NAME = "mockRobot";

    private Orchestra orchestra;
    private Robot robot;

    public OrchestraTest() {
        orchestra = new Orchestra();
        robot = mock(Robot.class);
        when(robot.getName()).thenReturn(MOCK_ROBOT_NAME);
    }

    @Test
    public void testGetAllEmpty() {
        Collection<Robot> robots = orchestra.getAll();
        Assert.assertTrue(robots.isEmpty());
    }

    @Test
    public void testRegisterRobotMethod() {
        int sizeBefore = orchestra.getAll().size();
        orchestra.register(robot);
        Assert.assertTrue(orchestra.getAll().size() == (sizeBefore + 1));
        Assert.assertTrue(orchestra.getAll().contains(robot));
    }

    @Test(expected = RuntimeException.class)
    public void testRegisterSameName() {
        orchestra.register(robot);
        orchestra.register(robot);
    }

    @Test
    public void testRetrieveRobot() {
        orchestra.register(robot);
        Robot retrievedRobot = orchestra.retrieve(robot.getName());
        Assert.assertEquals(robot, retrievedRobot);
    }

    @Test(expected = RuntimeException.class)
    public void testRetrieveUnknownRobot() {
        orchestra.retrieve("unknown");
    }

    @Test
    public void testRetrieveAll() {
        orchestra.register(robot);
        Collection<Robot> robots = orchestra.retrieveAll();
        Assert.assertTrue(robots.size() == 1);
        Assert.assertTrue(orchestra.retrieveAll().isEmpty());
    }

    @Test
    public void testReleaseRobot() {
        orchestra.register(robot);
        orchestra.retrieve(robot.getName());
        orchestra.release(robot);
    }

    @Test(expected = RuntimeException.class)
    public void testReleaseUnknownRobot() {
        orchestra.release(robot);
    }

    @Test
    public void testRemoveRobot() {
        orchestra.register(robot);
        orchestra.remove(robot.getName());
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveUnknownRobot() {
        orchestra.remove("unknownRobotName");
    }

}