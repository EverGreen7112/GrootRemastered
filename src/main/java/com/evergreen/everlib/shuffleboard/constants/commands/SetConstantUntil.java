package com.evergreen.everlib.shuffleboard.constants.commands;

import java.util.function.Supplier;

import com.evergreen.everlib.CommandEG;
import com.evergreen.everlib.shuffleboard.constants.ConstantDouble;

/**
 * SetConstantUntil
 */
public class SetConstantUntil extends CommandEG {
    Supplier<Boolean> m_until;
    ConstantDouble m_constant;
    double m_initValue;
    double m_valueToSet;

    public SetConstantUntil(String name, 
        ConstantDouble constant, Supplier<Double> value, Supplier<Boolean> until) {
        super(name);
        m_constant = constant;
        m_initValue = constant.get();
        m_valueToSet = value.get();
    }

    public SetConstantUntil(String name, ConstantDouble constant, Supplier<Double> value) {
        this(name, constant, value, () -> false);
    }

    @Override
    public void initialize() {
        m_constant.setValue(m_valueToSet);
    }

    @Override
    public boolean isFinished() {
        return m_until.get();
    }

    @Override
    public void end(boolean interrupted) {
        m_constant.setValue(m_initValue);
    }
}