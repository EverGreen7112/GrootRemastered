package com.evergreen.robot;

import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick.X;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick.Y;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick.Z;
import com.evergreen.everlib.oi.joysticks.F310GamePad;
import com.evergreen.everlib.oi.joysticks.F310GamePad.F310;
import com.evergreen.everlib.oi.joysticks.JoystickEG;
import com.evergreen.everlib.shuffleboard.constants.commands.ToggleSwitch;
import com.evergreen.robot.CommandList.CargoGripperCommands;
import com.evergreen.robot.CommandList.ChassisCommands;
import com.evergreen.robot.CommandList.ElevatorCommands;
import com.evergreen.robot.CommandList.GripperMovementCommands;
import com.evergreen.robot.CommandList.HatchGripperCommands;
import com.evergreen.robot.commands.ElevatorDefault;

/**
 * OI
 */
public class OI implements RobotMap, SubsystemConstants {

    private static final ExtremeProJoystick 
        m_drivingLeft = new ExtremeProJoystick("Joystick Driving Left", JoytsickPorts.leftChassis),
        m_drivingRight = new ExtremeProJoystick("Joystick Driving Right", JoytsickPorts.rightChassis);
    
    private static final F310GamePad    
        m_buttonJoystick = new F310GamePad("Joystick Button", JoytsickPorts.button);
    
    static {
        //------------Joystick Set Up------------
        m_drivingLeft.setExponential();
        m_drivingLeft.setInverted();

        m_drivingRight.setExponential();   


        //-----------Bind Buttons------------
        
        //-----Chassis-----
         m_drivingRight.thumb().whileHeld(ChassisCommands.slowDrive);

         m_drivingRight.trigger().whileHeld(ChassisCommands.fastDrive);

         m_drivingLeft.trigger().whileHeld(ChassisCommands.autoDrive);
         

        //------Cargo Gripper-----
        m_buttonJoystick.getButton(F310.X).whileHeld(CargoGripperCommands.take);
        m_buttonJoystick.getButton(F310.B).whileHeld(CargoGripperCommands.release);
        
        //Hatch Gripper
        m_buttonJoystick.getButton(F310.Y).whenPressed(HatchGripperCommands.takeIn);
        
        m_buttonJoystick.getButton(F310.A).whenPressed(HatchGripperCommands.takeOut);

        //Hatch Holder
        m_buttonJoystick.getButton(F310.RB).whenPressed(HatchGripperCommands.grip);
        m_buttonJoystick.getButton(F310.LB).whenPressed(HatchGripperCommands.release);

        //----Gripper Movement-----
        m_buttonJoystick.getButton(F310.JOYSTICK_RIGHT).whenPressed(GripperMovementCommands.flip); //Flip gripper.

        //-----Elevator-----
        m_drivingLeft.getButton(X.RIGHT, Y.BACK, Z.BOTTOM)
            .whenPressed(ElevatorCommands.toBottomHatch);

        m_drivingLeft.getButton(X.RIGHT, Y.MIDDLE, Z.BOTTOM)
            .whenPressed(ElevatorCommands.toMidHatch);

        m_drivingLeft.getButton(X.RIGHT, Y.FORWARD, Z.BOTTOM)
            .whenPressed(ElevatorCommands.toTopHatch);


        m_drivingLeft.getButton(X.LEFT, Y.BACK, Z.BOTTOM)
            .whenPressed(ElevatorCommands.toBottomCargo);

        m_drivingLeft.getButton(X.LEFT, Y.MIDDLE, Z.BOTTOM)
            .whenPressed(ElevatorCommands.toMidCargo);
    
        m_drivingLeft.getButton(X.LEFT, Y.FORWARD, Z.BOTTOM)
           .whenPressed(ElevatorCommands.toTopCargo);
        
        m_buttonJoystick.getButton(F310.BACK).whileHeld(new ToggleSwitch(
            "Toggle Elevator SpeedLock", ElevatorDefault.getStallSwitch()));

    }

    public static JoystickEG JSLeft() {
        return m_drivingLeft;
    }

    public static JoystickEG JSRight() {
        return m_drivingRight;
    }

    public static JoystickEG JSButton() {
        return m_buttonJoystick;
    }
}