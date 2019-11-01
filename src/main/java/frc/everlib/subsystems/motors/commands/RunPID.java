package frc.everlib.subsystems.motors.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.PIDController;
import frc.everlib.CommandEG;
import frc.everlib.utils.PIDSettings;

/**
 * MotorSubsystemPID
 */
public class RunPID extends CommandEG {
    PIDController m_controller;

    PIDSettings m_settings;

    Supplier<Double> m_target;
    
    public RunPID(String name, PIDSettings pidSettings, Supplier<Double> target) {
        super(name);

        m_settings = pidSettings;
        m_target = target;

        m_controller = new PIDController(pidSettings.kP.get(), pidSettings.kI.get(), pidSettings.kP.get(), 
        pidSettings.kF.get(),  pidSettings.source, pidSettings.output);

        m_controller.setSetpoint(target.get());
        m_controller.setAbsoluteTolerance(pidSettings.tolerance.get());
    }

    @Override
    protected void initialize() {
        m_controller.enable();
    }

    @Override
    protected void execute() {
        m_controller.setP(m_settings.kP.get());
        m_controller.setI(m_settings.kI.get());
        m_controller.setD(m_settings.kD.get());
        m_controller.setF(m_settings.kF.get());
        m_controller.setAbsoluteTolerance(m_settings.tolerance.get());
        m_controller.setSetpoint(m_target.get());
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished() || m_controller.onTarget();
    }

    @Override
    protected void end() {
        m_controller.disable();
        m_settings.output.pidWrite(0);
    }


}