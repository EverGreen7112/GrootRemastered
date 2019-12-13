package com.evergreen.everlib.subsystems.motors.subsystems;

import java.util.List;
import java.util.Map;

import com.evergreen.everlib.subsystems.SubsystemEG;
import com.evergreen.everlib.subsystems.sensors.DistanceSensor;
import com.evergreen.everlib.utils.loggables.LoggableData;
import com.evergreen.everlib.utils.loggables.LoggableDouble;
import com.evergreen.everlib.utils.ranges.Range;
import com.wpilib2020.framework.Command;

/**
 * A {@link Subsystem} consisting of one or more motor m_controllers.
 */
public class MotorSubsystem extends SubsystemEG {
    /**The subsystem's motor controllers. */
    protected MotorController[] m_controllers;

    /**The range in which the subsystem is allowed to move. */
    public Range m_Range; //TODO protected

    public static DistanceSensor DEFAULT_SENSOR = null;
    public static Range DEFAULT_RANGE = Range.TRUE;
    public static Command DEFAULT_DEFAULT_COMMAND = null;

    public MotorSubsystem(String name, DistanceSensor distanceSensor, Range range, 
        Command defaultCommand, MotorController... motors)
    {
        super(name, defaultCommand,distanceSensor);
        m_controllers = motors;
        m_Range = range;
    }

    public MotorSubsystem(String name, MotorController... motors)
    {
        this(name, DEFAULT_SENSOR, DEFAULT_RANGE, DEFAULT_DEFAULT_COMMAND, motors);
    }

    public MotorSubsystem(String name, DistanceSensor distanceSensor, MotorController... motors) {
        this(name, distanceSensor, DEFAULT_RANGE, DEFAULT_DEFAULT_COMMAND, motors);
    }

    public MotorSubsystem(String name, DistanceSensor distanceSensor, Range range, MotorController... motors)
    {
        this(name, distanceSensor, range, DEFAULT_DEFAULT_COMMAND, motors);
    }


    public void move(double speed) {
        if (canMove()) {
            for (MotorController control : m_controllers) {
                control.set(speed);
            }
        }
    }

    public void set(int index, double speed)
    {
        if(canMove()) 
            m_controllers[index].set(speed);
    }

    public void set(Map<Integer, Double> speedMap)
    {
        if(canMove()) 
            speedMap.forEach(this::set);
    }

    public void stop()
    {
        for(MotorController motor : m_controllers)
        {
            motor.stopMotor();
        }

    }

    public MotorController[] getMotorControllers() {
        return m_controllers;
    }

    public boolean canMove() {
        return m_subsystemSwitch.get() && 
        (getSensor() == null ||
        m_Range.inRange(getDistance()));
    }

    @Override
    public List<LoggableData> getLoggableData() {
        List<LoggableData> loggables = super.getLoggableData();


        for (int i = 0; i < m_controllers.length; i++) {
            loggables.add(new LoggableDouble(
                getName() + " - Controller #" + i + " speed", m_controllers[i]::get));            
        }

        return super.getLoggableData();
    }

    
}
