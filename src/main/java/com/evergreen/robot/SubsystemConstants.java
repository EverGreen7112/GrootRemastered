package com.evergreen.robot;

import com.evergreen.everlib.shuffleboard.constants.ConstantDouble;
import com.evergreen.everlib.shuffleboard.constants.DashboardConstants;
import com.evergreen.everlib.utils.PIDSettings;

/**
 * SubsystemConstants
 */
public interface SubsystemConstants {

    public static class ElevatorConstants {
        
        static
        {
            DashboardConstants.getInstance().startConstantsOf("Elevator");
            DashboardConstants.getInstance().cd("Encoder");
        }

        public static final double 
            MAX_HEIGHT = new ConstantDouble("MAX HEIGHT", 225.0).get(),
            ENCODER_HEIGHT = new ConstantDouble("ENCODER HEIGHT", 47.0).get(),
            ENCODER_DPP = new ConstantDouble("ENCODER DPP", 0.8157894).get(), //Distance Per pulse
            LASER_HEIGHT = new ConstantDouble("LASER HEIGHT", 32.0).get();
        
        public static final ConstantDouble
            laserSlope = new ConstantDouble("Laser Slope", 1.0),
            laserIntercept =  new ConstantDouble("Laser Intercept", 0.0);

        
        static
        {
            DashboardConstants.getInstance().cd("../Level Heights");
        }

        public static final double
            BOTTOM_HATCH_HEIGHT = new ConstantDouble("BOTTOM HATCH LEVEL HEIGHT", 48.26).get(),
            MIDDLE_HATCH_HEIGHT = new ConstantDouble("MIDDLE HATCH LEVEL HEIGHT", 119.38).get(),
            TOP_HATCH_HEIGHT = new ConstantDouble("TOP HATCH LEVLE HEIGHT", 190.5).get(),
            BOTTOM_CARGO_HEIGHT = new ConstantDouble("BOTTOM CARGO LEVEL HEIGHT", 69.85).get(),
            MIDDLE_CARGO_HEIGHT = new ConstantDouble("MIDDLE CARGO LEVEL HEIGHT", 140.97).get(),
            TOP_CARGO_HEIGHT = new ConstantDouble("TOP CARGO HEIGHT HEIGHT", 212.09).get();


        static
        {
            DashboardConstants.getInstance().cd("../Speeds");
        }

        public static final ConstantDouble
            speedModifier = new ConstantDouble("Drive", 0.7),
            targetSpeedModifier = new ConstantDouble("Target", 0.7);

        static
        {
            DashboardConstants.getInstance().cd("Stall Power");
        }
        
        public static final ConstantDouble
            bottomStall = new ConstantDouble("Bottom", 0.0),
            middleStall = new ConstantDouble("Middle", 0.13),
            topStall = new ConstantDouble("Top", 0.15);

        // public static final PIDSettings pidSettings = new PIDSettings(0, 0, 0);

    }

    /**
     * GripperConstants
     */
    public static class GripperConstants {

        static
        {
            DashboardConstants.getInstance().startConstantsOf("Cargo Gripper");
        }

        public static final ConstantDouble
            laserSlope = new ConstantDouble("Laser/Slope", 1.0),
            laserOffset = new ConstantDouble("Laser/Offset", 2.0),
            cargoDistance = new ConstantDouble("Misc/Gripping Distance", 0.2),

            inSpeed = new ConstantDouble("Speeds/Catch", 0.8),
            outSpeed = new ConstantDouble("Speeds/Release", -0.7);
    }

    /**
     * ChassisConstants
     */
    public static class ChassisConstants {

        static
        {
            DashboardConstants.getInstance().startConstantsOf("Chassis");
            DashboardConstants.getInstance().cd("Speeds");
        }

        public static final ConstantDouble
            driveSpeed = new ConstantDouble("Speed Modifier", 0.8),    
            fastSpeed = new ConstantDouble("Fast", 1.0),
            defenseSpeed = new ConstantDouble("slow", 0.4),
            autoSpeed = new ConstantDouble("Autonomous", 0.1);
            
        static
        {
            DashboardConstants.getInstance().cd("../Smart P");
        }

        public static final ConstantDouble
            smartPMaxFix = new ConstantDouble("Max Fix", 0.8),
            maxStaticFriction = new ConstantDouble(
                "max static friction", 0.0115);
    
        
        public static final PIDSettings pidSettings = new PIDSettings(Groot.getChassis(), 0, 0, 0);
    }

}