package frc.robot.commands;

import java.util.function.Supplier;

import frc.everlib.subsystems.motors.commands.MotorSystemBangBang;
import frc.everlib.subsystems.motors.commands.MoveMotorSystem;
import frc.robot.Groot;
import frc.robot.Groot.ToLog;
import frc.robot.SubsystemConstants.ElevatorConstants;
import frc.wpilib2020.framework.SequentialCommandGroup;

/**
 * MoveElevatorToLevel
 */
public class MoveElevatorToLevel extends SequentialCommandGroup implements ElevatorConstants, ToLog {

    public MoveElevatorToLevel(Level level) {

        addCommands(
            new MotorSystemBangBang(Groot.elevator, targetSpeedModifier, 
                () -> level.m_height, level.m_name),
            new MoveMotorSystem(this.getName() + " - Stall", Groot.elevator, level.m_stall));
    }

    public enum Level {
        BOTTOM_HATCH(BOTTOM_HATCH_HEIGHT, bottomStall, "Bottom Hatch", l_bottomHatchMove),
        MIDDLE_HATCH(MIDDLE_HATCH_HEIGHT, middleStall, "Middle Hatch", l_middleHatchMove),
        TOP_HATCH(TOP_HATCH_HEIGHT, topStall, "Top Hatch", l_topHatchMove),
        BOTTOM_CARGO(BOTTOM_CARGO_HEIGHT, bottomStall, "Bottom Cargo", l_bottomCargoMove),
        MIDDLE_CARGO(MIDDLE_CARGO_HEIGHT, middleStall, "Middle Cargo", l_middleCargoMove),
        TOP_CARGO(TOP_CARGO_HEIGHT, topStall, "Top Cargo", l_topCargoMove);

        double m_height;
        String m_name;
        Supplier<Double> m_stall;
        boolean m_toLog;

        Level(double height, Supplier<Double> stall, String name, boolean toLog) {
            m_height = height;
            m_stall = stall;
            m_name = name;
            m_toLog = toLog;
        }
    }
}