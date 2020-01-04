package com.evergreen.everlib.shuffleboard.constants;

import edu.wpi.first.wpilibj.Preferences;

/**
 * Loggable
 */
public abstract class Constant {
    private String m_path;
    private String m_name;

    public Constant(String name, Object value) {
        m_name = name;
        m_path = DashboardConstants.getInstance().pwd() + "/" + m_name;

        if (Preferences.getInstance().containsKey(m_path)) {
            throw new IllegalArgumentException(String.format(
                "Tried to add %s constant \"%s\" at %s, but it already exists!", 
                getType(), m_name, m_path));
        }

        addToDashboard();
        System.out.println(String.format("Added %s constant \"%s\" at %s - %s",
            getType(), m_name, m_path, value.toString()));

        DashboardConstants.getInstance().addConstant(this);
    }

    public String getPath() {
        return m_path;
    }

    public void setPath(String key) {
        m_path = key;
    }

    
    public abstract void addToDashboard();
    
    public void reset() {
        addToDashboard();
    }

    public void remove() {
        Preferences.getInstance().remove(getPath());        ;
    }


    public abstract String getType();
}
