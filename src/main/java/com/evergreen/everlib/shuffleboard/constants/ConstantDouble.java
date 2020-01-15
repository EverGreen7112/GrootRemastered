package com.evergreen.everlib.shuffleboard.constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Preferences;


/**
 * A {@link Constant} of the double data type, implementing {@link Supplier Supplier<Double>}
 */
public class ConstantDouble extends Constant implements Supplier<Double> {
    
    /**The value last set by the program (calling {@link #reset()} will change the value to this.)*/
    private double m_defaultVal;

    
    /**
     * Constructs a new {@link ConstantDouble} with input name and adds it to the shuffleboard under the current 
     * {@link DashboardConstants} path.
     * 
     * @param name - the constant's name
     * @param initValue - the initial value when putting the constant on the shuffleboard.
     */
    public ConstantDouble(String name, double initValue) {
        super(name, initValue);
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

    /**
     * 
     * Sets the constant's default value the input number, and then resets the shuffleboard box into that value.
     * @param value - the value to set the constant to.
     */
    public void setValue(double value) {
        m_defaultVal = value;
        addToDashboard();
    }


    /**
     * The current value of the constant on the shuffleboard..
     */
    @Override
    public Double get() {
        return Preferences.getInstance().getDouble(getPath(), m_defaultVal);
    }

}