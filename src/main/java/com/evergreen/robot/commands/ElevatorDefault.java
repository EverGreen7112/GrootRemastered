package com.evergreen.robot.commands;

import com.evergreen.everlib.CommandEG;
import com.evergreen.everlib.shuffleboard.handlers.Switch;
import com.evergreen.everlib.shuffleboard.handlers.SwitchHandler;
import com.evergreen.everlib.subsystems.motors.commands.MoveMotorSystem;
import com.evergreen.robot.OI;
import com.evergreen.robot.Groot;
import com.evergreen.robot.SubsystemConstants;

/**
 * ElevatorDefault
 */
public class ElevatorDefault extends CommandEG implements SubsystemConstants.ElevatorConstants {

    public ElevatorDefault() {
        super("Elevator default command");
        addRequirements(Groot.elevator);
    }
    
    public final static Switch speedLockSwitch = SwitchHandler.addSwitch("Elevator Stall");

    private double lastSpeed;
    private boolean speedLocked = false;

    CommandEG baseDefault = new MoveMotorSystem("Elevator Default Movement",
     Groot.elevator, OI.buttonJoystick::getY, speedModifier);
    CommandEG speedLock = new MoveMotorSystem("Elevator Stall", Groot.elevator, () -> lastSpeed);

    @Override
    public void initialize() {
        baseDefault.schedule();
    }

    @Override
    public void execute() {
        
        if (speedLockSwitch.get() && !speedLocked)
        {
            speedLocked = true;
            speedLock.schedule();
        }

        else if (!speedLockSwitch.get() && speedLocked)
        {
            speedLocked = false;
            baseDefault.schedule();
        }

        else if (!speedLocked)
        {
            lastSpeed = OI.buttonJoystick.getY();
        }

    }
}