package com.evergreen.everlib.shuffleboard.constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Preferences;

/**
 * A constant of the integer data type, implementing {@link Supplier Supplier<Integer>}.
 */
public class ConstantInt extends Constant implements Supplier<Integer> {
    
    /**The value last set by the program (calling {@link #reset()} will change the value to this.)*/
    private int m_defaultVal;


    /**
     * Constructs a new {@link ConstantInt} with input name and adds it to the shuffleboard under the current 
     * {@link DashboardConstants} path.
     * 
     * @param name - the constant's name
     * @param initValue - the initial value when putting the constant on the shuffleboard.
     */
    public ConstantInt(String name, int initValue) {
        super(name, initValue);
        m_defaultVal = initValue;
    }
    
    @Override
    public void addToDashboard() {
        Preferences.getInstance().putInt(getPath(), m_defaultVal);
    }

    @Override
    public String getType() {
        return "Integer";
    }
    public void increaseAbsolute(int increaseBy) {
        m_defaultVal += increaseBy;
        addToDashboard();
    }

    /**
     * Increases the constants value in percentage, according to an input 100%.
     * @param percentage - The percentage to rise
     * @param maxValue - The 100% the percentage are a part of
     */
    public void increasePercentage(int percentage, int maxValue) {
        m_defaultVal += maxValue * (percentage / 100);
        addToDashboard();
    } 


    /**
     * Sets the constant's default value the input integer, and then resets the shuffleboard box into that value.
     * @param value - the value to set the constant to.
     */
    public void setValue(int newValue) {
        m_defaultVal = newValue;
        reset();
    }


    /**
     * The current value of the constant on the shuffleboard..
     */
    @Override
    public Integer get() {
        return Preferences.getInstance().getInt(getPath(), m_defaultVal);
    }
}