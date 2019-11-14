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
            MAX_HEIGHT = DashboardConstants.addDouble(Groot.elevator, "MAX HEIGHT", 225.0).get(),
            
            ENCODER_HEIGHT = DashboardConstants.addDouble(Groot.elevator, "ENCODER HEIGHT", 47.0).get(),
            LASER_HEIGHT = DashboardConstants.addDouble(Groot.elevator, "LASER HEIGHT", 32.0).get(),
            
            DISTANCE_PER_ENCODER_PULSE = DashboardConstants.addDouble("ELEVATOR ENCODER DPP", 0.8157894).get(),
            
            BOTTOM_HATCH_HEIGHT = DashboardConstants.addDouble(Groot.elevator, "BOTTOM HATCH LEVEL HEIGHT", 48.26).get(),
            MIDDLE_HATCH_HEIGHT = DashboardConstants.addDouble(Groot.elevator, "MIDDLE HATCH LEVEL HEIGHT", 119.38).get(),
            TOP_HATCH_HEIGHT = DashboardConstants.addDouble(Groot.elevator, "TOP HATCH LEVLE HEIGHT", 190.5).get(),
            BOTTOM_CARGO_HEIGHT = DashboardConstants.addDouble(Groot.elevator, "BOTTOM CARGO LEVEL HEIGHT", 69.85).get(),
            MIDDLE_CARGO_HEIGHT = DashboardConstants.addDouble(Groot.elevator, "MIDDLE CARGO LEVEL HEIGHT", 140.97).get(),
            TOP_CARGO_HEIGHT = DashboardConstants.addDouble(Groot.elevator, "TOP CARGO HEIGHT HEIGHT", 212.09).get();

        Supplier<Double>
            laserSlope = DashboardConstants.addDouble(Groot.elevator, "Laser Sensor - Slope", 1.0),
            laserIntercept =  DashboardConstants.addDouble(Groot.elevator, "Laser Sensor - Intercept", 0.0),

            speedModifier = DashboardConstants.addDouble(Groot.elevator, "Speed Modifier", 0.7),
            
            targetSpeedModifier = DashboardConstants.addDouble(Groot.elevator, " Movement to target speed", 0.7),

            bottomStall = DashboardConstants.addDouble(Groot.elevator, "Bottom Levels Stall Power", 0.0),
            middleStall = DashboardConstants.addDouble(Groot.elevator, "Middle Levels Stall Power", 0.13),
            topStall = DashboardConstants.addDouble(Groot.elevator, "Top Levels Stall Power", 0.15);

        // PIDSettings pidSettings = new PIDSettings(Groot.elevator, 0, 0, 0);

    }

    /**
     * GripperConstants
     */
    public interface GripperConstants {
        Supplier<Double>
            laserSlope = DashboardConstants.addDouble(Groot.cargoGripper, "Laser - Slope", 1.0),
            laserOffset = DashboardConstants.addDouble(Groot.cargoGripper, "Laser - Offset", 2.0),
            cargoDistance = DashboardConstants.addDouble(Groot.cargoGripper, "Gripping Distance", 0.2),

            inSpeed = DashboardConstants.addDouble(Groot.cargoGripper, "Catch Speed", 0.8),
            outSpeed = DashboardConstants.addDouble(Groot.cargoGripper, "Release Speed", -0.7);
    }

    /**
     * ChassisConstants
     */
    public interface ChassisConstants {
        Supplier<Double> 
        //Speeds
            driveSpeed = DashboardConstants.addDouble(Groot.chassis, "Speed Modifier", 0.8),
            fastSpeed = DashboardConstants.addDouble(Groot.chassis, "Speeds - Fast", 1.0),
            defenseSpeed = DashboardConstants.addDouble(Groot.chassis, "Speeds - slow", 0.4),
            autoSpeed = DashboardConstants.addDouble(Groot.chassis, "Speeds - Autonomous", 0.1),

            speedModifier = () -> Utilities.smartP.get() ? driveSpeed.get() : autoSpeed.get(),

            smartPMaxFix = DashboardConstants.addDouble(Groot.chassis, "Smart P - max fix", 0.8),
            maxStaticFriction = DashboardConstants.addDouble(
                Groot.chassis, "Smart P - max static friction", 0.0115);

            PIDSettings pidSettings = new PIDSettings(Groot.chassis, 0, 0, 0);

    }

}