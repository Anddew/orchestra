package com.anddew.robotworld.orchestra;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class HistoryAspect {

    @Autowired
    private History history;

    @Pointcut("execution(* com.anddew.robotworld.robot.Robot.play(..))")
    public void log(JoinPoint joinPoint) {
        String message = new StringBuilder().append(joinPoint.toLongString()).toString();
        history.postMessage(message);
    }


}