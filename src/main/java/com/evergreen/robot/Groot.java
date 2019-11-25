/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.evergreen.robot;

import com.evergreen.everlib.shuffleboard.handlers.DashboardStreams;
import com.evergreen.everlib.subsystems.motors.subsystems.DriveTank;
import com.evergreen.everlib.subsystems.motors.subsystems.MotorSubsystem;
import com.evergreen.everlib.subsystems.pistons.subsystems.PistonSubsystem;
import com.evergreen.everlib.utils.ranges.MinLimit;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Groot extends TimedRobot implements SubsystemComponents, SubsystemConstants {
  
  public static final MotorSubsystem elevator = new MotorSubsystem(
      "Elevator", ElevatorComponents.distanceSensor, ElevatorComponents.motors);
    
  public static final MotorSubsystem cargoGripper = new MotorSubsystem(
      "Cargo Gripper", CargoGripperComponents.distanceSensor, 
      new MinLimit(GripperConstants.cargoDistance), 
      CargoGripperComponents.motors);


  public static final PistonSubsystem gripperMovement = new PistonSubsystem(
    GripperMovementComponents.pistons, "Gripper Movement");
  
  public static final PistonSubsystem hatchGripper = new PistonSubsystem(
    GripperPanelComponents.pushPistons, "Panel Gripper");
  
  public static final PistonSubsystem hatchHolder = new PistonSubsystem(
    GripperPanelComponents.toungePistons, "Panel Holder");


  public static final DriveTank 
    chassis = new DriveTank("Chassis", ChassisComponents.leftMotors, ChassisComponents.rightMotor);

  public static final NetworkTable imageProccesing = 
    NetworkTableInstance.getDefault().getTable("ImageProcessing");

  
  public interface ToLog {
    boolean //TODO turn into CommandList interface
      l_bottomCargoMove = false,
      l_bottomHatchMove = false,
      l_fastDrive = false,
      l_flipGripper = false,
      l_gripperCatch = false,
      l_gripperRelease = false,
      l_middleCargoMove = false,
      l_middleHatchMove = false,
      l_slowDrive = false,
      l_smartP = false,
      l_speedLock = false,
      l_takeHatch = false,
      l_throwHatch = false,
      l_topCargoMove = false,
      l_topHatchMove = false;
  }
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //-------------Default Commands
    // elevator.setDefaultCommand(new ElevatorDefault());
    
    // chassis.setDefaultCommand(new TankDrive(
    //   "Chassis default (drive eith joystick)", 
    //   chassis,
    //   OI.leftChassisJoystick::getY, 
    //   OI.rightChassisJoystick::getY));

    // cargoGripper.move(0.7);
    
    gripperMovement.toggle();

    DashboardStreams.addLoggable(cargoGripper);
    DashboardStreams.addDouble("DrivingLeft", OI.leftChassisJoystick::getY);
    DashboardStreams.addDouble("DrivingRight", OI.rightChassisJoystick::getY);
    DashboardStreams.addDouble("ButtonJS", OI.buttonJoystick::getY);
  }

  @Override
  public void disabledInit() {
    super.disabledInit();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    DashboardStreams.update();
  }

  @Override
  public void autonomousInit() {
    // gripperMovement.setForward(); //Folded
    // hatchGripper.setForward(); //In
    // hatchHolder.setForward(); //Out
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // gripperMovement.setForward(); //Folded
    // hatchGripper.setForward(); //In
    // hatchHolder.setForward(); //Out

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
