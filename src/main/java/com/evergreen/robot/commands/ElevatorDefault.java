package com.evergreen.robot.commands;

import com.evergreen.everlib.CommandEG;
import com.evergreen.everlib.shuffleboard.constants.ConstantBoolean;
import com.evergreen.everlib.subsystems.motors.commands.MoveMotorSystem;
import com.evergreen.robot.OI;
import com.evergreen.robot.Groot;
import com.evergreen.robot.SubsystemConstants;

/**
 * ElevatorDefault
 */
public class ElevatorDefault extends CommandEG implements SubsystemConstants.ElevatorConstants {

    private static final ElevatorDefault m_instance = new ElevatorDefault();
    private final static ConstantBoolean m_speedLockSwitch = new ConstantBoolean("Elevator Stall");

    private ElevatorDefault() {
        super("Elevator default command");
        addRequirements(Groot.elevator);
    }
    

    private double lastSpeed;
    private boolean speedLocked = false;

    CommandEG baseDefault = new MoveMotorSystem("Elevator Default Movement",
     Groot.elevator, OI.JSButton()::getY, speedModifier);
    CommandEG speedLock = new MoveMotorSystem("Elevator Stall", Groot.elevator, () -> lastSpeed);

    @Override
    public void initialize() {
        baseDefault.schedule();
    }

    @Override
    public void execute() {
        
        if (m_speedLockSwitch.get() && !speedLocked)
        {
            speedLocked = true;
            speedLock.schedule();
        }

        else if (!m_speedLockSwitch.get() && speedLocked)
        {
            speedLocked = false;
            baseDefault.schedule();
        }

        else if (!speedLocked)
        {
            lastSpeed = OI.JSButton().getY();
        }
    }

    public static ElevatorDefault getInstance() {
        return m_instance;
    }

    public static ConstantBoolean getStallSwitch() {
        return m_speedLockSwitch;
    }
}
