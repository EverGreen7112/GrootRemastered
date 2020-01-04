package com.evergreen.robot;

import java.util.function.Supplier;

import com.evergreen.everlib.shuffleboard.constants.ConstantDouble;
import com.evergreen.everlib.shuffleboard.constants.DashboardConstants;
import com.evergreen.everlib.shuffleboard.handlers.Explorer;
import com.evergreen.everlib.utils.PIDSettings;

/**
 * SubsystemConstants
 */
public interface SubsystemConstants {

    public interface ElevatorConstants {

        Explorer namespace = DashboardConstants.getInstance().cd("/Elevator/Constants");

        public final double 
            MAX_HEIGHT = new ConstantDouble("MAX HEIGHT", 225.0).get(),
            
            ENCODER_HEIGHT = new ConstantDouble("ENCODER HEIGHT", 47.0).get(),
            LASER_HEIGHT = new ConstantDouble("LASER HEIGHT", 32.0).get(),
            
            DISTANCE_PER_ENCODER_PULSE = new ConstantDouble("ELEVATOR ENCODER DPP", 0.8157894).get(),
            
            BOTTOM_HATCH_HEIGHT = new ConstantDouble("BOTTOM HATCH LEVEL HEIGHT", 48.26).get(),
            MIDDLE_HATCH_HEIGHT = new ConstantDouble("MIDDLE HATCH LEVEL HEIGHT", 119.38).get(),
            TOP_HATCH_HEIGHT = new ConstantDouble("TOP HATCH LEVLE HEIGHT", 190.5).get(),
            BOTTOM_CARGO_HEIGHT = new ConstantDouble("BOTTOM CARGO LEVEL HEIGHT", 69.85).get(),
            MIDDLE_CARGO_HEIGHT = new ConstantDouble("MIDDLE CARGO LEVEL HEIGHT", 140.97).get(),
            TOP_CARGO_HEIGHT = new ConstantDouble("TOP CARGO HEIGHT HEIGHT", 212.09).get();

        public final ConstantDouble
            laserSlope = new ConstantDouble("Laser Sensor/Slope", 1.0),
            laserIntercept =  new ConstantDouble("Laser Sensor/Intercept", 0.0),

            speedModifier = new ConstantDouble("Speed Modifier", 0.7),
            
            targetSpeedModifier = new ConstantDouble(" Movement to target speed", 0.7),

            bottomStall = new ConstantDouble("Bottom Levels Stall Power", 0.0),
            middleStall = new ConstantDouble("Middle Levels Stall Power", 0.13),
            topStall = new ConstantDouble("Top Levels Stall Power", 0.15);

        // PIDSettings pidSettings = new PIDSettings(0, 0, 0);

    }

    /**
     * GripperConstants
     */
    public interface GripperConstants {
        Explorer namespace = DashboardConstants.getInstance().cd("/Gripper/Cargo/Constants");

        ConstantDouble
            laserSlope = new ConstantDouble("Cargo Gripper Constants/Laser/Slope", 1.0),
            laserOffset = new ConstantDouble("Cargo Gripper Constants/Laser/Offset", 2.0),
            cargoDistance = new ConstantDouble("Cargo Gripper Constants/Gripping Distance", 0.2),

            inSpeed = new ConstantDouble("Cargo Gripper Constants/Catch Speed", 0.8),
            outSpeed = new ConstantDouble("Cargo Gripper Constants/release speed", -0.7);
    }

    /**
     * ChassisConstants
     */
    public interface ChassisConstants {

        Explorer namespace = DashboardConstants.getInstance().cd("/Chassis/Constants");
        ConstantDouble driveSpeed = new ConstantDouble("Speed Modifier", 0.8);


        Explorer speedNameSpace = DashboardConstants.getInstance().cd("Speeds");
        ConstantDouble
            fastSpeed = new ConstantDouble("Fast", 1.0),
            defenseSpeed = new ConstantDouble("slow", 0.4),
            autoSpeed = new ConstantDouble("Autonomous", 0.1);

        Explorer smartPNameSpace = DashboardConstants.getInstance().cd("../Smart P");
        ConstantDouble
            smartPMaxFix = new ConstantDouble("Max Fix", 0.8),
            maxStaticFriction = new ConstantDouble(
                "Smart P/max static friction", 0.0115);
        

        Supplier<Double> 
            speedModifier = () -> Utilities.smartP.get() ? driveSpeed.get() : autoSpeed.get();
        
            PIDSettings pidSettings = new PIDSettings(Groot.chassis, 0, 0, 0);
    }

}