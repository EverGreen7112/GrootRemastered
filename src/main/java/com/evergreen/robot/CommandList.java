package com.evergreen.robot;

import com.evergreen.everlib.shuffleboard.constants.commands.ToggleSwitch;
import com.evergreen.everlib.subsystems.motors.commands.MoveMotorSystem;
import com.evergreen.everlib.subsystems.pistons.commands.SetPistonSubsystem;
import com.evergreen.everlib.subsystems.pistons.commands.TogglePistonSubsystem;
import com.evergreen.robot.SubsystemConstants.GripperConstants;
import com.evergreen.robot.commands.DriveChassis;
import com.evergreen.robot.commands.DriveChassis.DriveMode;
import com.evergreen.robot.commands.ElevatorDefault;
import com.evergreen.robot.commands.MoveElevatorToLevel;
import com.evergreen.robot.commands.MoveElevatorToLevel.Level;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * CommandList
 */
public interface CommandList {

    /**
     * GripperCOmmands
     */
    public interface CargoGripperCommands {
        public static final MoveMotorSystem 
            take = new MoveMotorSystem(
                "Catch (x)", Groot.getCargoGripper(), GripperConstants.inSpeed),

            release = new MoveMotorSystem("Release (B)", Groot.getCargoGripper(), 
                GripperConstants.outSpeed);
    }

    /**
     * HatchGripperCommands
     */
    public interface HatchGripperCommands {
        SetPistonSubsystem
            takeIn = new SetPistonSubsystem("Take In", Groot.getHatchGripper(), Value.kForward),
            takeOut = new SetPistonSubsystem("Take Out", Groot.getHatchGripper(), Value.kReverse),

            grip = new SetPistonSubsystem("Catch", Groot.getHatchHolder(), Value.kForward),
            release = new SetPistonSubsystem("Release", Groot.getHatchHolder(), Value.kForward);
    }

    /**
     * GripperFlipperCommands
     */
    public interface GripperFlipperCommands {
        SetPistonSubsystem _caution = new SetPistonSubsystem(
            "Gripper Flip Caution", Groot.getHatchGripper(), Value.kReverse);
        TogglePistonSubsystem _flip = new TogglePistonSubsystem("Flip", Groot.getGripperFlipper());
        
        Command flip = _caution.andThen(_flip);
    }

    /**
     * InnerCommandList
     */
    public interface ChassisCommands {
        DriveChassis
            fastDrive = new DriveChassis(DriveMode.FAST),
            slowDrive = new DriveChassis(DriveMode.SLOW),
            normalDrive = new DriveChassis(DriveMode.NORMAL),
            autoDrive = new DriveChassis(DriveMode.AUTO);
    }

    /**
     * ElevatorCommands
     */
    public interface ElevatorCommands {
        ElevatorDefault
            defaultCommand = ElevatorDefault.getInstance();
        
        MoveElevatorToLevel
            toBottomHatch = new MoveElevatorToLevel(Level.BOTTOM_HATCH),
            toMidHatch = new MoveElevatorToLevel(Level.MIDDLE_HATCH),
            toTopHatch = new MoveElevatorToLevel(Level.BOTTOM_HATCH),

            toBottomCargo = new MoveElevatorToLevel(Level.BOTTOM_CARGO),
            toMidCargo = new MoveElevatorToLevel(Level.MIDDLE_CARGO),
            toTopCargo = new MoveElevatorToLevel(Level.TOP_CARGO);

        ToggleSwitch 
            toggleSpeedLock = new ToggleSwitch(
                Groot.getElevator().getName() + "/Commands/Toggle Speed Lock" , ElevatorDefault.getStallSwitch());
    }
}