package frc.robot.commands;

import java.util.function.Supplier;

import frc.everlib.betamimic.SequentialCommandGroup;
import frc.everlib.subsystems.motors.commands.MotorSystemBangBang;
import frc.everlib.subsystems.motors.commands.MoveMotorSystem;
import frc.robot.Groot;
import frc.robot.SubsystemConstants;

/**
 * MoveElevatorToLevel
 */
public class MoveElevatorToLevel extends SequentialCommandGroup implements SubsystemConstants.ElevatorConstants {

    public MoveElevatorToLevel(Level level) {

        addSequential(
             new MotorSystemBangBang(Groot.elevator, targetSpeedModifier, 
                () -> level.m_height, level.m_name));
        addSequential(
            new MoveMotorSystem(this.getName() + " - Stall", Groot.elevator, level.m_stall));
    }

    public enum Level {
        BOTTOM_HATCH(BOTTOM_HATCH_HEIGHT, bottomStall, "Bottom Hatch"),
        MIDDLE_HATCH(MIDDLE_HATCH_HEIGHT, middleStall, "Middle Hatch"),
        TOP_HATCH(TOP_HATCH_HEIGHT, topStall, "Top Hatch"),
        BOTTOM_CARGO(BOTTOM_CARGO_HEIGHT, bottomStall, "Bottom Cargo"),
        MIDDLE_CARGO(MIDDLE_CARGO_HEIGHT, middleStall, "Middle Cargo"),
        TOP_CARGO(TOP_CARGO_HEIGHT, topStall, "Top Cargo");

        double m_height;
        String m_name;
        Supplier<Double> m_stall;

        Level(double height, Supplier<Double> stall, String name) {
            m_height = height;
            m_stall = stall;
            m_name = name;
        }
    }
}