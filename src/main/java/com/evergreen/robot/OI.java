package com.evergreen.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick;
import com.evergreen.everlib.oi.joysticks.F310GamePad;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick.X;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick.Y;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick.Z;
import com.evergreen.everlib.oi.joysticks.F310GamePad.F310;
import com.evergreen.everlib.shuffleboard.commands.ToggleSwitch;
import com.evergreen.everlib.subsystems.motors.commands.MoveMotorSystem;
import com.evergreen.everlib.subsystems.pistons.commands.SetPistonSubsystem;
import com.evergreen.everlib.subsystems.pistons.commands.TogglePistonSubsystem;
import com.evergreen.robot.Groot.ToLog;
import com.evergreen.robot.commands.ElevatorDefault;
import com.evergreen.robot.commands.MoveElevatorToLevel;
import com.evergreen.robot.commands.MoveElevatorToLevel.Level;

/**
 * OI
 */
public class OI implements RobotMap, SubsystemConstants, ToLog {

    public static final ExtremeProJoystick 
        leftChassisJoystick = new ExtremeProJoystick(JoytsickPorts.leftChassis),
        rightChassisJoystick = new ExtremeProJoystick(JoytsickPorts.rightChassis);
    
    public static final F310GamePad    
        buttonJoystick = new F310GamePad(JoytsickPorts.button);
    
    static {
        //------------Joystick Set Up------------
        leftChassisJoystick.setExponential();
        leftChassisJoystick.setInverted();

        rightChassisJoystick.setExponential();   


        //-----------Bind Buttons------------
        
        //-----Chassis-----
         Utilities.setHeldChassisDrive(
             "Slow Drive (Right Thumb)", 
             rightChassisJoystick.thumb(), 
             ChassisConstants.defenseSpeed,
             l_slowDrive);

         Utilities.setHeldChassisDrive(
             "Fast Drive (Right Trigger)", rightChassisJoystick.trigger(), 
             ChassisConstants.fastSpeed, l_fastDrive);

         Utilities.setHeldChassisDrive(
             "Smart P (Left Trigger)", leftChassisJoystick.trigger(), Utilities::getChassisFix, 
             ChassisConstants.autoSpeed, l_smartP);
         

        //------Cargo Gripper-----
        buttonJoystick.get(F310.X).whileHeld(new MoveMotorSystem(
            "Roller Gripper catch (X)", Groot.cargoGripper, GripperConstants.inSpeed,
            l_gripperCatch));
        buttonJoystick.get(F310.B).whileHeld(new MoveMotorSystem(
            "Roller Gripper Release (B)", Groot.cargoGripper, GripperConstants.outSpeed,
            l_gripperRelease));
        
        //Hatch Gripper
        buttonJoystick.get(F310.Y).whenPressed(new SetPistonSubsystem(
            "Take In Hatch (Y)", Groot.hatchGripper, Value.kForward));
        
        buttonJoystick.get(F310.A).whenPressed(new SetPistonSubsystem(
            "Take out hatch (A)", Groot.hatchGripper, Value.kReverse));

        //Hatch Holder
        buttonJoystick.get(F310.RB).whenPressed(new SetPistonSubsystem(
            "Catch Hatch (RB)", Groot.hatchHolder, Value.kForward));
        buttonJoystick.get(F310.LB).whenPressed(new SetPistonSubsystem(
            "Release Hatch (LB)", Groot.hatchHolder, Value.kReverse
        ));
        //----Gripper Movement-----
        buttonJoystick.get(F310.JOYSTICK_RIGHT).whenPressed( 
            new SetPistonSubsystem("Flip Gripper's Hatch Gripper Caution", Groot.hatchGripper, 
            Value.kForward, l_flipGripper)
            //Takes the hatchGripper in to let the gripper fold 
            .andThen(
            new TogglePistonSubsystem(
            "Flip Gripper (Right Button Joystick)", Groot.gripperMovement,
            l_flipGripper))); //Flip gripper.

        //-----Elevator-----
        leftChassisJoystick.getButton(X.RIGHT, Y.BACK, Z.BOTTOM)
            .whenPressed(new MoveElevatorToLevel(Level.BOTTOM_HATCH));

        leftChassisJoystick.getButton(X.RIGHT, Y.MIDDLE, Z.BOTTOM)
            .whenPressed(new MoveElevatorToLevel(Level.MIDDLE_HATCH));

        leftChassisJoystick.getButton(X.RIGHT, Y.FORWARD, Z.BOTTOM)
            .whenPressed(new MoveElevatorToLevel(Level.MIDDLE_HATCH));


        leftChassisJoystick.getButton(X.LEFT, Y.BACK, Z.BOTTOM)
            .whenPressed(new MoveElevatorToLevel(Level.MIDDLE_HATCH));

        leftChassisJoystick.getButton(X.LEFT, Y.MIDDLE, Z.BOTTOM)
            .whenPressed(new MoveElevatorToLevel(Level.MIDDLE_HATCH));
    
        leftChassisJoystick.getButton(X.LEFT, Y.FORWARD, Z.BOTTOM)
           .whenPressed(new MoveElevatorToLevel(Level.MIDDLE_HATCH));
        
        buttonJoystick.get(F310.BACK).whileHeld(new ToggleSwitch(
            "Toggle Elevator SpeedLock", l_speedLock, ElevatorDefault.speedLockSwitch));

    }
}