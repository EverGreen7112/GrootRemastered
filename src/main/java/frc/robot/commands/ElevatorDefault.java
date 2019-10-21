package frc.robot.commands;

import frc.everlib.CommandEG;
import frc.everlib.shuffleboard.handlers.Switch;
import frc.everlib.shuffleboard.handlers.SwitchHandler;
import frc.everlib.subsystems.motors.commands.MoveMotorSystem;
import frc.robot.OI;
import frc.robot.Groot;
import frc.robot.SubsystemConstants;

/**
 * ElevatorDefault
 */
public class ElevatorDefault extends CommandEG implements SubsystemConstants.ElevatorConstants {

    public ElevatorDefault() {
        super("Elevator default command");
    }
    
    public final static Switch speedLockSwitch = SwitchHandler.addSwitch("Elevator Stall");
    double lastSpeed;
    boolean speedLocked = false;

    CommandEG baseDefault = new MoveMotorSystem("Elevator Default Movement",
     Groot.elevator, OI.buttonJoystick::getY, speedModifier);
    CommandEG speedLock = new MoveMotorSystem("Elevator Stall", Groot.elevator, () -> lastSpeed);

    @Override
    protected void initialize() {
        baseDefault.start();
    }

    @Override
    protected void execute() {
        
        if (speedLockSwitch.get() && !speedLocked)
        {
            speedLocked = true;
            speedLock.start();
        }

        else if (!speedLockSwitch.get() && speedLocked)
        {
            speedLocked = false;
            baseDefault.start();
        }

        else if (!speedLocked)
        {
            lastSpeed = OI.buttonJoystick.getY();
        }

    }
}