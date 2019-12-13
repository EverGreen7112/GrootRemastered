package com.evergreen.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.evergreen.everlib.subsystems.motors.subsystems.MotorController;
import com.evergreen.everlib.subsystems.motors.subsystems.MotorController.ControllerType;
import com.evergreen.everlib.subsystems.sensors.DistanceSensorGroup;
import com.evergreen.everlib.subsystems.sensors.EncoderEG;
import com.evergreen.everlib.subsystems.sensors.LaserSensor;
import com.evergreen.everlib.utils.ranges.MaxLimit;
import com.evergreen.robot.SubsystemConstants.ElevatorConstants;
import com.evergreen.robot.SubsystemConstants.GripperConstants;


/**
 * SubsystemComponents
 */
public interface SubsystemComponents extends RobotMap{

    /**
     * The elevator 
     */
    public interface ElevatorComponents {
        
        static MotorController motors = new MotorController (
            ControllerType.VICTOR_SPX, MotorPorts.ElevatorA, MotorPorts.ElevatorB);
        
        static DigitalInput microSwitch = new DigitalInput(SwitchPorts.elevator);

        static DistanceSensorGroup distanceSensor = new DistanceSensorGroup(
            new LaserSensor (
                AnalogPorts.elevator, ElevatorConstants.laserSlope, 
                ElevatorConstants.laserIntercept),
            
            new EncoderEG(
                EncoderPorts.elevatorA, EncoderPorts.elevatorB, //Ports
                new MaxLimit(ElevatorConstants.MAX_HEIGHT),  //Expected value range
                ElevatorConstants.ENCODER_HEIGHT, //offset
                ElevatorConstants.DISTANCE_PER_ENCODER_PULSE) //distacne per pulse
            
            );

    }

    public interface ChassisComponents {
        MotorController 
            leftMotors = new MotorController (
                ControllerType.VICTOR_SPX, MotorPorts.chassisBackLeft, MotorPorts.chassisFrontLeft),
            
            rightMotor = new MotorController (
                ControllerType.TALON_SRX, MotorPorts.chassisBackRight, 
                MotorPorts.chassisFrontRight);
    }

    public interface CargoGripperComponents {
        MotorController motors = new MotorController (
            ControllerType.VICTOR_SPX, MotorPorts.gripperLeft, MotorPorts.gripperRight);
        LaserSensor distanceSensor = new LaserSensor(
            AnalogPorts.gripper, GripperConstants.laserSlope, GripperConstants.laserOffset);
    }

    public interface GripperPanelComponents {
        DoubleSolenoid 
            toungePistons = new DoubleSolenoid (
                PistonsPorts.toungeForward, PistonsPorts.toungeReverse),
            
            pushPistons = new DoubleSolenoid (
                PistonsPorts.pushForward, PistonsPorts.pushReverse);
    }

    /**
     * GripperMovementComponents
     */
    public interface GripperMovementComponents {
        static DoubleSolenoid pistons = new DoubleSolenoid (
            PistonsPorts.gripperMovementForward, PistonsPorts.gripperMovementReverse);
    }
}