package com.evergreen.robot;

import java.util.function.Supplier;

import com.evergreen.everlib.shuffleboard.handlers.DashboardConstants;
import com.evergreen.everlib.utils.PIDSettings;

/**
 * SubsystemConstants
 */
public interface SubsystemConstants {

    public interface ElevatorConstants {
        double 
            MAX_HEIGHT = DashboardConstants.addDouble("Elevator/MAX HEIGHT", 225.0).get(),
            
            ENCODER_HEIGHT = DashboardConstants.addDouble("Elevator/ENCODER HEIGHT", 47.0).get(),
            LASER_HEIGHT = DashboardConstants.addDouble("Elevator/LASER HEIGHT", 32.0).get(),
            
            DISTANCE_PER_ENCODER_PULSE = DashboardConstants.addDouble("Elevator/ELEVATOR ENCODER DPP", 0.8157894).get(),
            
            BOTTOM_HATCH_HEIGHT = DashboardConstants.addDouble("Elevator/BOTTOM HATCH LEVEL HEIGHT", 48.26).get(),
            MIDDLE_HATCH_HEIGHT = DashboardConstants.addDouble("Elevator/MIDDLE HATCH LEVEL HEIGHT", 119.38).get(),
            TOP_HATCH_HEIGHT = DashboardConstants.addDouble("Elevator/TOP HATCH LEVLE HEIGHT", 190.5).get(),
            BOTTOM_CARGO_HEIGHT = DashboardConstants.addDouble("Elevator/BOTTOM CARGO LEVEL HEIGHT", 69.85).get(),
            MIDDLE_CARGO_HEIGHT = DashboardConstants.addDouble("Elevator/MIDDLE CARGO LEVEL HEIGHT", 140.97).get(),
            TOP_CARGO_HEIGHT = DashboardConstants.addDouble("Elevator/TOP CARGO HEIGHT HEIGHT", 212.09).get();

        Supplier<Double>
            laserSlope = DashboardConstants.addDouble("Elevator/Laser Sensor - Slope", 1.0),
            laserIntercept =  DashboardConstants.addDouble("Elevator/Laser Sensor - Intercept", 0.0),

            speedModifier = DashboardConstants.addDouble("Elevator/Speed Modifier", 0.7),
            
            targetSpeedModifier = DashboardConstants.addDouble("Elevator/ Movement to target speed", 0.7),

            bottomStall = DashboardConstants.addDouble("Elevator/Bottom Levels Stall Power", 0.0),
            middleStall = DashboardConstants.addDouble("Elevator/Middle Levels Stall Power", 0.13),
            topStall = DashboardConstants.addDouble("Elevator/Top Levels Stall Power", 0.15);

        // PIDSettings pidSettings = new PIDSettings(0, 0, 0);

    }

    /**
     * GripperConstants
     */
    public interface GripperConstants {
        Supplier<Double>
            laserSlope = DashboardConstants.addDouble("Cargo Gripper/Laser - Slope", 1.0),
            laserOffset = DashboardConstants.addDouble("Cargo Gripper/Laser - Offset", 2.0),
            cargoDistance = DashboardConstants.addDouble("Cargo Gripper/Gripping Distance", 0.2),

            inSpeed = DashboardConstants.addDouble("Cargo Gripper/Catch Speed", 0.8),
            outSpeed = DashboardConstants.addDouble("Cargo Gripper/release speed", -0.7);
    }

    /**
     * ChassisConstants
     */
    public interface ChassisConstants {
        Supplier<Double> 
        //Speeds
            driveSpeed = DashboardConstants.addDouble("Chassis/Speed Modifier", 0.8),
            fastSpeed = DashboardConstants.addDouble("Chassis/Speeds - Fast", 1.0),
            defenseSpeed = DashboardConstants.addDouble("Chassis/Speeds - slow", 0.4),
            autoSpeed = DashboardConstants.addDouble("Chassis/Speeds - Autonomous", 0.1),

            speedModifier = () -> Utilities.smartP.get() ? driveSpeed.get() : autoSpeed.get(),

            smartPMaxFix = DashboardConstants.addDouble("Chassis/Smart P - max fix", 0.8),
            maxStaticFriction = DashboardConstants.addDouble(
                "Smart P - max static friction", 0.0115);

            PIDSettings pidSettings = new PIDSettings(Groot.chassis, 0, 0, 0);

    }

}