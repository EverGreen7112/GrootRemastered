package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.everlib.oi.joysticks.ExtremeProJoystick;
import frc.everlib.oi.joysticks.F310GamePad;
import frc.everlib.shuffleboard.commands.ToggleSwitch;
import frc.everlib.subsystems.motors.commands.MoveMotorSystem;
import frc.everlib.subsystems.pistons.commands.SetPistonSubsystem;
import frc.everlib.subsystems.pistons.commands.TogglePistonSubsystem;
import frc.robot.commands.ElevatorDefault;
import frc.robot.commands.MoveElevatorToLevel;
import frc.robot.commands.MoveElevatorToLevel.Level;

/**
 * OI
 */
public class OI implements RobotMap, SubsystemConstants {

    public static final ExtremeProJoystick 
        leftChassisJoystick = new ExtremeProJoystick(JoytsickPorts.leftChassis),
        rightChassisJoystick = new ExtremeProJoystick(JoytsickPorts.rightChassis);
    
    public static final F310GamePad    
        buttonJoystick = new F310GamePad(JoytsickPorts.button);
    
    static
    {
        //------------Joystick Set Up------------
        leftChassisJoystick.setExponential();
        leftChassisJoystick.setInverted();

        rightChassisJoystick.setExponential();   


        //-----------Bind Buttons------------
        
        //-----Chassis-----
         Utilities.setHeldChassisDrive(
             "Slow Drive (Right Thumb)", rightChassisJoystick.thumb, ChassisConstants.defenseSpeed);

         Utilities.setHeldChassisDrive(
             "Fast Drive (Right Trigger)", rightChassisJoystick.trigger, ChassisConstants.fastSpeed);

         Utilities.setHeldChassisDrive(
             "Smart P (Left Trigger)", leftChassisJoystick.trigger, Utilities::getChassisFix, 
             ChassisConstants.autoSpeed);
         

        //------Cargo Gripper-----
        buttonJoystick.X.whileHeld(new MoveMotorSystem(
            "Roller Gripper catch (X)", Groot.cargoGripper, GripperConstants.inSpeed));
        buttonJoystick.B.whileHeld(new MoveMotorSystem(
            "Roller Gripper Release (B)", Groot.cargoGripper, GripperConstants.outSpeed));
        
        //Hatch Gripper
        buttonJoystick.Y.whenPressed(new SetPistonSubsystem(
            "Take In Hatch (Y)", Groot.hatchGripper, Value.kForward));
        
        buttonJoystick.A.whenPressed(new SetPistonSubsystem(
            "Take out hatch (A)", Groot.hatchGripper, Value.kReverse));

        
        //Hatch Holder
        buttonJoystick.RB.whenPressed(new SetPistonSubsystem(
            "Catch Hatch (RB)", Groot.hatchHolder, Value.kForward));
        buttonJoystick.LB.whenPressed(new SetPistonSubsystem(
            "Release Hatch (LB)", Groot.hatchHolder, Value.kReverse
        ));

        //----Gripper Movement-----
        buttonJoystick.rightJoystick.whenPressed( 
            new SetPistonSubsystem("Flip Gripper's Hatch Gripper Caution", Groot.hatchGripper, Value.kForward)
            //Takes the hatchGripper in to let the gripper fold 
            .andThen(
            new TogglePistonSubsystem(
            "Flip Gripper (Right Button Joystick)", Groot.gripperMovement))); //Flip gripper.

        //-----Elevator-----
        leftChassisJoystick.bottomRightBack.whenPressed(new MoveElevatorToLevel(Level.BOTTOM_HATCH));        leftChassisJoystick.bottomRightBack.whenPressed(new MoveElevatorToLevel(Level.BOTTOM_HATCH));
        leftChassisJoystick.bottomRightMiddle.whenPressed(new MoveElevatorToLevel(Level.MIDDLE_HATCH));
        leftChassisJoystick.bottomRightForward.whenPressed(new MoveElevatorToLevel(Level.TOP_HATCH));

        leftChassisJoystick.bottomLeftBack.whenPressed(new MoveElevatorToLevel(Level.BOTTOM_CARGO));
        leftChassisJoystick.bottomLeftMiddle.whenPressed(new MoveElevatorToLevel(Level.MIDDLE_CARGO));
        leftChassisJoystick.bottomLeftMiddle.whenPressed(new MoveElevatorToLevel(Level.TOP_CARGO));
        
        buttonJoystick.BACK.whileHeld(new ToggleSwitch(
            "Toggle Elevator SpeedLock", ElevatorDefault.speedLockSwitch));
        
        
    }
}