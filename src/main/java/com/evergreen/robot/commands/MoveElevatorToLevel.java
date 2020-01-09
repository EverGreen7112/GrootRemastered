package com.evergreen.robot.commands;

import java.util.function.Supplier;

import com.evergreen.everlib.subsystems.motors.commands.MotorSystemBangBang;
import com.evergreen.everlib.subsystems.motors.commands.MoveMotorSystem;
import com.evergreen.robot.Groot;
import com.evergreen.robot.SubsystemConstants.ElevatorConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * MoveElevatorToLevel
 */
public class MoveElevatorToLevel extends SequentialCommandGroup {

    public MoveElevatorToLevel(Level level) {

        addCommands(
            new MotorSystemBangBang(Groot.elevator, ElevatorConstants.targetSpeedModifier, 
                () -> level.m_height, level.m_name),
            new MoveMotorSystem(this.getName() + " - Stall", Groot.elevator, level.m_stall));
    }

    public enum Level {
        BOTTOM_HATCH(ElevatorConstants.BOTTOM_HATCH_HEIGHT, ElevatorConstants.bottomStall, "Bottom Hatch"),
        MIDDLE_HATCH(ElevatorConstants.MIDDLE_HATCH_HEIGHT, ElevatorConstants.middleStall, "Middle Hatch"),
        TOP_HATCH(ElevatorConstants.TOP_HATCH_HEIGHT, ElevatorConstants.topStall, "Top Hatch"),
        BOTTOM_CARGO(ElevatorConstants.BOTTOM_CARGO_HEIGHT, ElevatorConstants.bottomStall, "Bottom Cargo"),
        MIDDLE_CARGO(ElevatorConstants.MIDDLE_CARGO_HEIGHT, ElevatorConstants.middleStall, "Middle Cargo"),
        TOP_CARGO(ElevatorConstants.TOP_CARGO_HEIGHT, ElevatorConstants.topStall, "Top Cargo");

        double m_height;
        String m_name;
        Supplier<Double> m_stall;
        boolean m_toLog;

        Level(double height, Supplier<Double> stall, String name) {
            m_height = height;
            m_stall = stall;
            m_name = name;
        }
    }
}