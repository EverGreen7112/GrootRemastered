package frc.robot;

import java.util.function.Supplier;

import frc.everlib.shuffleboard.handlers.DashboardConstants;
import frc.everlib.utils.PIDSettings;

/**
 * SubsystemConstants
 */
public interface SubsystemConstants {

    public interface ElevatorConstants {
        double 
            MAX_HEIGHT = DashboardConstants.addDouble("Elevator Constants - MAX HEIGHT", 225.0).get(),
            
            ENCODER_HEIGHT = DashboardConstants.addDouble("Elevator Constants - ENCODER HEIGHT", 47.0).get(),
            LASER_HEIGHT = DashboardConstants.addDouble("Elevator Constants - LASER HEIGHT", 32.0).get(),
            
            DISTANCE_PER_ENCODER_PULSE = DashboardConstants.addDouble("Elevator Constants - ELEVATOR ENCODER DPP", 0.8157894).get(),
            
            BOTTOM_HATCH_HEIGHT = DashboardConstants.addDouble("Elevator Constants - BOTTOM HATCH LEVEL HEIGHT", 48.26).get(),
            MIDDLE_HATCH_HEIGHT = DashboardConstants.addDouble("Elevator Constants - MIDDLE HATCH LEVEL HEIGHT", 119.38).get(),
            TOP_HATCH_HEIGHT = DashboardConstants.addDouble("Elevator Constants - TOP HATCH LEVLE HEIGHT", 190.5).get(),
            BOTTOM_CARGO_HEIGHT = DashboardConstants.addDouble("Elevator Constants - BOTTOM CARGO LEVEL HEIGHT", 69.85).get(),
            MIDDLE_CARGO_HEIGHT = DashboardConstants.addDouble("Elevator Constants - MIDDLE CARGO LEVEL HEIGHT", 140.97).get(),
            TOP_CARGO_HEIGHT = DashboardConstants.addDouble("Elevator Constants - TOP CARGO HEIGHT HEIGHT", 212.09).get();

        Supplier<Double>
            laserSlope = DashboardConstants.addDouble("Elevator Constants - Laser Sensor - Slope", 1.0),
            laserIntercept =  DashboardConstants.addDouble("Elevator Constants - Laser Sensor - Intercept", 0.0),

            speedModifier = DashboardConstants.addDouble("Elevator Constants - Speed Modifier", 0.7),
            
            targetSpeedModifier = DashboardConstants.addDouble("Elevator Constants -  Movement to target speed", 0.7),

            bottomStall = DashboardConstants.addDouble("Elevator Constants - Bottom Levels Stall Power", 0.0),
            middleStall = DashboardConstants.addDouble("Elevator Constants - Middle Levels Stall Power", 0.13),
            topStall = DashboardConstants.addDouble("Elevator Constants - Top Levels Stall Power", 0.15);

        // PIDSettings pidSettings = new PIDSettings(0, 0, 0);

    }

    /**
     * GripperConstants
     */
    public interface GripperConstants {
        Supplier<Double>
            laserSlope = DashboardConstants.addDouble("Cargo Gripper Constants - Laser - Slope", 1.0),
            laserOffset = DashboardConstants.addDouble("Cargo Gripper Constants - Laser - Offset", 2.0),
            cargoDistance = DashboardConstants.addDouble("Cargo Gripper Constants - Gripping Distance", 0.2),

            inSpeed = DashboardConstants.addDouble("Cargo Gripper Constants - Catch Speed", 0.8),
            outSpeed = DashboardConstants.addDouble("Cargo Gripper Constants - release speed", -0.7);
    }

    /**
     * ChassisConstants
     */
    public interface ChassisConstants {
        Supplier<Double> 
        //Speeds
            driveSpeed = DashboardConstants.addDouble("Chassis Constants - Speed Modifier", 0.8),
            fastSpeed = DashboardConstants.addDouble("Chassis Constants - Speeds - Fast", 1.0),
            defenseSpeed = DashboardConstants.addDouble("Chassis Constants - Speeds - slow", 0.4),
            autoSpeed = DashboardConstants.addDouble("Chassis Constants - Speeds - Autonomous", 0.1),

            speedModifier = () -> Utilities.smartP.get() ? driveSpeed.get() : autoSpeed.get(),

            smartPMaxFix = DashboardConstants.addDouble("Chassis Constants - Smart P - max fix", 0.8),
            maxStaticFriction = DashboardConstants.addDouble(
                "Smart P - max static friction", 0.0115);

            PIDSettings pidSettings = new PIDSettings(Groot.chassis, 0, 0, 0);

    }

}