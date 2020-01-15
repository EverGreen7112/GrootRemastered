package com.evergreen.everlib.shuffleboard.constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Preferences;

/**
 * A constant of the string data type, implementing {@link Supplier Supplier<String>}
 */
public class ConstantString extends Constant implements Supplier<String> {
    
    /**The value last set by the program (calling {@link #reset()} will change the value to this.)*/
    private String m_defaultVal;


    /**
     * Constructs a new {@link ConstantString} with input name and adds it to the shuffleboard under the current 
     * {@link DashboardConstants} path.
     * 
     * @param name - the constant's name
     * @param initValue - the initial value when putting the constant on the shuffleboard.
     */
    public ConstantString(String name, String initValue) {
        super(name, initValue);
        m_defaultVal = initValue;
    }


    /**
     * Sets the constant's default value the input string, and then resets the shuffleboard box into that value.
     * @param value - the value to set the constant to.
     */
    public void setValue(String newValue) {
        m_defaultVal = newValue;
        reset();
    }
    
    @Override
    public String getType() {
        return "String";
    }

    @Override
    public void addToDashboard() {
        Preferences.getInstance().putString(getPath(), m_defaultVal);
    }


    /**
     * The current value of the constant on the shuffleboard..
     */
    @Override
    public String get() {
        return Preferences.getInstance().getString(getPath(), m_defaultVal);
    }


}