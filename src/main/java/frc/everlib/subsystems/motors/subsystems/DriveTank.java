package frc.everlib.subsystems.motors.subsystems;

import java.util.Map;

import frc.everlib.subsystems.sensors.DistanceSensor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;

/**
 * DriveTank
 */
public class DriveTank extends MotorSubsystem {

    public DriveTank(String name, MotorController leftMotors, MotorController rightMotors) {
        super(name, leftMotors, rightMotors);
    }

    public DriveTank(String name, MotorController leftMotors, MotorController rightMotors, DistanceSensor sensor) {
        super(name, leftMotors, rightMotors);
    }

    public DriveTank(String name, DistanceSensor sensor, MotorController leftMotors, MotorController rightMotors) {
        super(name, leftMotors, rightMotors);
    }

    public void drive(double leftSpeed, double rightSpeed) {
        set(Map.of(0, leftSpeed, 1, rightSpeed));
    }

    public void setDefaultByJoystick(Joystick joystick, AxisType leftAxis, AxisType rightAxis) {

    }


}