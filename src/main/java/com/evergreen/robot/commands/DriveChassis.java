package com.evergreen.robot.commands;

import com.evergreen.everlib.shuffleboard.constants.ConstantDouble;
import com.evergreen.everlib.subsystems.motors.commands.TankDrive;
import com.evergreen.robot.Groot;
import com.evergreen.robot.OI;
import com.evergreen.robot.SubsystemConstants;
import com.evergreen.robot.Utilities;

/**
 * DriveChassis
 */
public class DriveChassis extends TankDrive implements SubsystemConstants {
    
    public DriveChassis(DriveMode mode) {
       super("Drives/"+ mode.toString(), Groot.chassis, 
        () -> OI.JSLeft().getY(), () -> OI.JSRight().getY(), mode.getModifier());

        if (mode.equals(DriveMode.AUTO)) 
            m_speedMap.put(1, () -> m_speedMap.get(1).get() + Utilities.getChassisFix());
        
    }

    public enum DriveMode {
        NORMAL(ChassisConstants.driveSpeed),
        SLOW(ChassisConstants.fastSpeed),
        FAST(ChassisConstants.defenseSpeed),
        AUTO(ChassisConstants.autoSpeed);

        ConstantDouble m_speedModifier;

        DriveMode(ConstantDouble speed) {
            m_speedModifier = speed;
        }

        public ConstantDouble getModifier() {
            return m_speedModifier;
        } 
    }
}