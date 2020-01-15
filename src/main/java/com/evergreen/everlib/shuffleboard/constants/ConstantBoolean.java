package com.evergreen.everlib.shuffleboard.constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Preferences;

/**
 * A {@link Constant} of the boolean data type, implementing {@link Supplier Supplier<Boolean>}
 */
public class ConstantBoolean extends Constant implements Supplier<Boolean> {
    
    /**The value last set by the program (calling {@link #reset()} will change the value to this.)*/
    private boolean m_defaultVal;

    /**
     * Constructs a new {@link ConstantBoolean} with input name and adds it to the shuffleboard under the current 
     * {@link DashboardConstants} path.
     * 
     * @param name - the constant's name
     * @param initValue - the initial value when putting the constant on the shuffleboard.
     */
    public ConstantBoolean(String name, boolean initValue) {
        super(name, initValue);
        m_defaultVal = initValue;
    }

    /**
     * Constructs a new {@link ConstantBoolean} with input name and adds it to the shuffleboard under the current 
     * {@link DashboardConstants} path with an initial value of true. .
     * @param name - The constant's name.
     */
    public ConstantBoolean(String name) {
        this(name, true);
    }
    
    /**
     * Sets the constant's default value the input boolean, and then resets the shuffleboard box into that value.
     * @param value - the value to set the constant to.
     */
    public void set(boolean value) {
        m_defaultVal = value;
        reset();
    }

    /**Toggles the shuffleboard switch - if it's false, sets it true, and if it's true, sets it false.
     * <p>
     * This sets not onlt the current value on the shuflleboard, but also the default value returned to with {@link #reset()}. 
     */
    public void toggle() {
        set(!m_defaultVal);
    }

    /**
     * Sets the shuffleboard switch false.
     */
    public void disable()
    {
        set(false);
    }

    /**
     * Sets the shuffleboard switch true.
     */
    public void enable() 
    {
        set(true);
    }

    @Override
    public String getType() {
        return "Boolean";
    }

    /**
     * The current value of the constant on the shuffleboard..
     */
    @Override
    public Boolean get() {
        return Preferences.getInstance().getBoolean(getPath(), m_defaultVal);
    }

    @Override
    public void addToDashboard() {
        Preferences.getInstance().putBoolean(getPath(), m_defaultVal);
    }


}