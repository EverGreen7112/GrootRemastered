package com.evergreen.everlib.subsystems.motors.subsystems;

import java.util.Map;

import com.evergreen.everlib.oi.joysticks.JoystickEG;
import com.evergreen.everlib.subsystems.motors.commands.TankDrive;
import com.evergreen.everlib.subsystems.sensors.DistanceSensor;

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

    public void setDefaultByJoystick(JoystickEG joystick, AxisType leftAxis, AxisType rightAxis) {
        setDefaultCommand(
            new TankDrive(
                getName() + " default command (drive tank by joystick)", 
                this,
                () -> joystick.getRawAxis(leftAxis),
                () -> joystick.getRawAxis(rightAxis)));
    }

    @Override
    public void set(int index, double speed) {
        super.set(index, speed * Side.valueOf(index).getModifier());
    }

    public enum Side {
        LEFT(0),
        RIGHT(1);

        private int m_port;
        private double m_modifier;

        private Side(int port) {
            m_port = port;
        }

        public int getPort() {
            return m_port;
        }

        public double getModifier() {
            return m_modifier;
        }

        public void setModifier(double modifier) {
            m_modifier = modifier;
        }

        public static Side valueOf(int port) {
            switch(port) {
                case 0:
                    return LEFT;
                case 1:
                    return RIGHT;
                default:
                    throw new NullPointerException("Chassis motor #" + port + " does not exist!");
            }
        }
    }

    public void set(Side side, double speed) {
        set(side.getPort(), speed * side.getModifier());
    }

    public void setModifier(Side side, double modifier) {
        side.setModifier(modifier);
    }

    public void setLeftModifier(double modifier) {
        Side.LEFT.setModifier(modifier);
    }

    public void setRightModifier(double modifier) {
        Side.RIGHT.setModifier(modifier);
    }

    public void setInverted(Side side) {
        m_controllers[side.getPort()].setInverted();
    }

    public boolean getInverted(Side side) {
        return m_controllers[side.getPort()].getInverted();
    }
}