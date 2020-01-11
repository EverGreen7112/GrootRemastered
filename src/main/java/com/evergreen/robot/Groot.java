/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.evergreen.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick.X;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick.Y;
import com.evergreen.everlib.oi.joysticks.ExtremeProJoystick.Z;
import com.evergreen.everlib.oi.joysticks.F310GamePad;
import com.evergreen.everlib.oi.joysticks.F310GamePad.F310;
import com.evergreen.everlib.shuffleboard.loggables.DashboardStreams;
import com.evergreen.everlib.oi.joysticks.JoystickEG;
import com.evergreen.everlib.structure.Tree;
import com.evergreen.everlib.subsystems.motors.subsystems.DriveTank;
import com.evergreen.everlib.subsystems.motors.subsystems.MotorSubsystem;
import com.evergreen.everlib.subsystems.pistons.subsystems.PistonSubsystem;
import com.evergreen.everlib.utils.ranges.MinLimit;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Groot extends Tree implements SubsystemComponents, SubsystemConstants, CommandList {
  
  //--------------------Subsystems--------------------
  //----------Motor Subsystems----------
  public static final MotorSubsystem m_elevator = new MotorSubsystem(
      "Elevator", ElevatorComponents.distanceSensor, ElevatorComponents.motors);
    
  public static final MotorSubsystem m_cargoGripper = new MotorSubsystem(
      "Cargo Gripper", CargoGripperComponents.distanceSensor, 
      new MinLimit(GripperConstants.cargoDistance), 
      CargoGripperComponents.motors);


  //----------Piston SUbsystems----------
  public static final PistonSubsystem m_gripperFlipper = new PistonSubsystem(
    GripperFlipperComponents.pistons, "Gripper Movement");
  
  public static final PistonSubsystem m_hatchGripper = new PistonSubsystem(
    HatchGripperComponents.pushPistons, "Panel Gripper");
  
  public static final PistonSubsystem m_hatchHolder = new PistonSubsystem(
    HatchGripperComponents.toungePistons, "Panel Holder");


  //----------DriveTrains----------
  public static final DriveTank 
    m_chassis = new DriveTank("Chassis", ChassisComponents.leftMotors, ChassisComponents.rightMotor);


  //--------------------Network Tables--------------------
  public static final NetworkTable m_imageProccesing = 
    NetworkTableInstance.getDefault().getTable("ImageProcessing");


  //--------------------Joysticks--------------------
  private static final ExtremeProJoystick
      m_JSLeft = new ExtremeProJoystick("Joystick Driving Left", JoytsickPorts.leftChassis),
      m_JSRigjt = new ExtremeProJoystick("Joystick Driving Right", JoytsickPorts.rightChassis);
  
  private static final F310GamePad    
      m_JSButton = new F310GamePad("Joystick Button", JoytsickPorts.button);


  @Override
  protected void componentSetup() {
    CargoGripperComponents.motors.setInverted(1, true);

    m_JSLeft.setExponential();
    m_JSLeft.setInverted();

    m_JSRigjt.setExponential(); 
  }

  @Override
  protected void bindButtons() {  
    
    //-----Chassis-----
     m_JSRigjt.thumb().whileHeld(ChassisCommands.slowDrive);
     m_JSRigjt.trigger().whileHeld(ChassisCommands.fastDrive);
     m_JSLeft.trigger().whileHeld(ChassisCommands.autoDrive);
     

    //------Cargo Gripper-----
    m_JSButton.getButton(F310.X).whileHeld(CargoGripperCommands.take);
    m_JSButton.getButton(F310.B).whileHeld(CargoGripperCommands.release);
    
    //-----Hatch Gripper-----
    m_JSButton.getButton(F310.Y).whenPressed(HatchGripperCommands.takeIn);
    m_JSButton.getButton(F310.A).whenPressed(HatchGripperCommands.takeOut);

    //-----Hatch Holder-----
    m_JSButton.getButton(F310.RB).whenPressed(HatchGripperCommands.grip);
    m_JSButton.getButton(F310.LB).whenPressed(HatchGripperCommands.release);

    //----Gripper Movement-----
    m_JSButton.getButton(F310.JOYSTICK_RIGHT).whenPressed(GripperFlipperCommands.flip); //Flip gripper.

    //-----Elevator-----
    m_JSLeft.getButton(X.RIGHT, Y.BACK, Z.BOTTOM)
        .whenPressed(ElevatorCommands.toBottomHatch);

    m_JSLeft.getButton(X.RIGHT, Y.MIDDLE, Z.BOTTOM)
        .whenPressed(ElevatorCommands.toMidHatch);

    m_JSLeft.getButton(X.RIGHT, Y.FORWARD, Z.BOTTOM)
        .whenPressed(ElevatorCommands.toTopHatch);


    m_JSLeft.getButton(X.LEFT, Y.BACK, Z.BOTTOM)
        .whenPressed(ElevatorCommands.toBottomCargo);

    m_JSLeft.getButton(X.LEFT, Y.MIDDLE, Z.BOTTOM)
        .whenPressed(ElevatorCommands.toMidCargo);

    m_JSLeft.getButton(X.LEFT, Y.FORWARD, Z.BOTTOM)
       .whenPressed(ElevatorCommands.toTopCargo);
    
    m_JSButton.getButton(F310.BACK).whileHeld(ElevatorCommands.toggleSpeedLock);
    
  }

  @Override
  protected void commandConfig() {
    m_elevator.setDefaultCommand(ElevatorCommands.defaultCommand);
    m_chassis.setDefaultCommand(ChassisCommands.normalDrive);
  }

  @Override
  protected void log() {
    DashboardStreams.addLoggable(m_JSButton);
    DashboardStreams.addLoggable(m_JSLeft);
    DashboardStreams.addLoggable(m_JSButton);

    DashboardStreams.addLoggable(m_cargoGripper);
  }

  @Override
  protected void whenEnabled() {
    m_gripperFlipper.setReverse(); //Fold
  }

  @Override
  protected void autoConfig() {

  }

  @Override
  protected void teleopConfig() {

  }

  @Override
  protected void test() {
    // m_cargoGripper.move(0.7);
    // CargoGripperComponents.motors.set(0.7);
    // new WPI_VictorSPX(4).set(0.7);

    // m_hatchHolder.setForward();
    // HatchGripperComponents.pushPistons.set(Value.kForward);
    
  }



  //--------------------Gettters---------
    public static DriveTank getChassis() {
    return m_chassis;
  }

    public static MotorSubsystem getCargoGripper() {
    return m_cargoGripper;
  }

    public static MotorSubsystem getElevator() {
    return m_elevator;
  }

    public static PistonSubsystem getHatchGripper() {
    return m_hatchGripper;
  }

    public static PistonSubsystem getHatchHolder() {
    return m_hatchHolder;
  }

    public static PistonSubsystem getGripperFlipper() {
    return m_gripperFlipper;
  }

    public static NetworkTable getNetworkTable() {
    return m_imageProccesing;
  }

    public static JoystickEG JSLeft() {
      return m_JSLeft;
  }

    public static JoystickEG JSRight() {
      return m_JSRigjt;
  }
  
   public static JoystickEG JSButton() {
      return m_JSButton;
  }
  
}
  
  