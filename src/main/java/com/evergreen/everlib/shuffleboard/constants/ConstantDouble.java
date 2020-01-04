package com.evergreen.everlib.shuffleboard.constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Preferences;


/**
 * LoggableNumber
 */
public class ConstantDouble extends Constant implements Supplier<Double> {
    
    double m_defaultVal;

    public ConstantDouble(String key, double initValue) {
        super(key, initValue);
        m_defaultVal = initValue;
    }

    @Override
    public void addToDashboard() {
        Preferences.getInstance().putDouble(getPath(), m_defaultVal);
    }

    @Override
    public String getType() {
        return "Double";
    }

    public void setValue(double value) {
        m_defaultVal = value;
        addToDashboard();
    }

    public void increaseAbsolute(double increaseBy) {
        setValue(m_defaultVal + increaseBy);
    }

    /**
     * Increases the constants value in percentage, according to an input 100%.
     * @param percentage - The percentage to rise
     * @param maxValue - The 100% the percentage are a part of
     */
    public void increasePercentage(double percentage, double maxValue) {
        increaseAbsolute(maxValue * (percentage / 100) );
    }

    public void increasePercentage(double percentage) {
        increasePercentage(percentage, m_defaultVal);
    }

    public void lowerAbsolute(double lowerBy) {
        increaseAbsolute(-lowerBy);
    }

    public void lowerPercentage(double percentage, double maxValue) {
        increasePercentage(-percentage, maxValue);
    }

    public void lowerPercentage(double percentage) {
        increasePercentage(-percentage);
    }

    @Override
    public Double get() {
        return Preferences.getInstance().getDouble(getPath(), m_defaultVal);
    }

}