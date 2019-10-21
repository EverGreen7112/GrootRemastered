package frc.everlib.subsystems.motors.subsystems;

import java.util.Map;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.everlib.subsystems.SubsystemEG;
import frc.everlib.subsystems.sensors.DistanceSensor;
import frc.everlib.utils.ranges.Range;

/**
 * A {@link Subsystem} consisting of one or more motor m_controllers.
 */
public class MotorSubsystem extends SubsystemEG {
    /**The subsystem's motor controllers. */
    protected MotorController[] m_controllers;

    /**The range in which the subsystem is allowed to move. */
    protected Range m_Range;

    /**The sensor mesuring the distance the subsystem has gone. */
    protected DistanceSensor m_distanceSensor;

    /**The subsystem's default command */
    protected Command m_defaultCommand;
    
    public MotorSubsystem(String name, MotorController... motors)
    {
        super(name);    
        m_controllers = motors;
        m_Range = (v) -> true;
    }

    public MotorSubsystem(String name, DistanceSensor distanceSensor, MotorController... motors) {
        super(name);
        
        m_controllers = motors;

        m_distanceSensor = distanceSensor;
        m_distanceSensor.setSubsystem(this);

    }

    public MotorSubsystem(String name, DistanceSensor distanceSensor, Range Range, MotorController... motors)
    {
        super(name);
        m_controllers = motors;

        m_distanceSensor = distanceSensor;
        m_distanceSensor.setSubsystem(this);

        m_Range = Range;
    }

    public MotorSubsystem(String name, DistanceSensor distanceSensor, Range Range, 
        Command defaultCommand, MotorController... motors)
    {
        super(name);
        
        m_controllers = motors;
        m_Range = Range;

        m_distanceSensor = distanceSensor;
        m_distanceSensor.setSubsystem(this);

        m_defaultCommand = defaultCommand;
    }


    public void move(double speed) {
        for (MotorController control : m_controllers) {
            control.set(speed);
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
 
    public boolean canMove() {
        return m_Range.inRange(getDistance()) && m_subsystemSwitch.get();
    }


    public MotorController[] getMotorControllers() {
        return m_controllers;
    }

    @Override
    protected void initDefaultCommand() {   
        if(m_defaultCommand != null)
        {
            setDefaultCommand(m_defaultCommand);
        }
    }
}
