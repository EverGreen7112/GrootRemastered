package com.evergreen.everlib.shuffleboard.constants;

import com.evergreen.everlib.shuffleboard.handlers.Explorer;

import edu.wpi.first.wpilibj.Preferences;

/**
 * A representation of a shuffleboard constant - set with a value at RobotInit,
 * and tuned by drivers and users in the shuffleboard.
 * 
 * @author Atai Ambus
 */
public abstract class Constant {

    /**A name for this constant (the lastpart of its path) */
    private String m_name;

    /**The "folder" this constant is in*/
    private String m_folder;

    /**
     * Construct a super {@link Constant} object with input name, and adds it at the current
     * {@link DashboardConstants} working directory. 
     * <p>
     * Trying to add a constant at an already used path will cause an {@link IllegalArgumentException}.
     * 
     * @param name - The constant's name 
     * @param value - The constant's value (parsed to string for logging purposes)
     */
    public Constant(String name, Object value) {
        m_name = name;
        m_folder = DashboardConstants.getInstance().pwd();

        if (wasAdded()) {
            throw new IllegalArgumentException(String.format(
                "Tried to add %s constant \"%s\" at %s, but a constant already"
                + " exists in that path!", 
                getType(), m_name, m_folder));
        }

        addToDashboard();
        System.out.println(String.format("Added %s constant \"%s\" at %s - %s",
            getType(), m_name, m_folder, value.toString()));

        DashboardConstants.getInstance().addConstant(this);
    }

    /**
     * @return the shuffleboard path to this constant (under Preferences).
     */
    public String getPath() {
        return m_folder + "/" + m_name;
    }

    /**
     * Relocates the constant to the given path
     * (This also renames the constant. if you want to move it)
     * @param path - the constant's new path
     */
    public void setPath(String path) {
        remove();
        m_folder = path.substring(0, path.lastIndexOf('/'));
        m_name = path.substring(path.lastIndexOf('/'), path.length());
        addToDashboard();
    }


    /**
     * Moves the constant to a folder - as per unix
     * @param folder
     */
    public void move(String folder) {
        remove();
        
        Explorer explorer = new Explorer(m_folder);
        explorer.cd(folder);

        m_folder = explorer.pwd();
        
        addToDashboard();
    }

    /**
     * Renames the constant.
     * @param newName - The new name
     */
    public void rename(String newName) {
        remove();
        m_name = newName;
        addToDashboard();   
    }

    /**Adds the constant to the shuffleboard under its last set value and path */
    public abstract void addToDashboard();
    
    /**Sets the constant's value to the value last set in the program, cancelling any changes made manually.*/
    public void reset() {
        addToDashboard();
    }

    /**Removes this constant from the shuffleboard. 
     * <p>
     * If it was not yet added there, a mesage explaining so will be printed instead.
    */
    public void remove() {
        if (wasAdded())
            Preferences.getInstance().remove(getPath());
        else
            System.out.println(String.format(
                "Tried to remove constant \"%s\" from %s, but it was never added to the"
                + " Shuffleboard in the first place! ", 
                m_name, m_folder));
    }

    /**
     * @return
     * <i>True</i> if the constant is currently on the shuffleboard 
     * <li><i>False</i> if the constant was not yet put there. </li>
     */
    public boolean wasAdded() {
        return Preferences.getInstance().containsKey(getPath());
    }

    /**
     * @return A string describing the constant data type (e.g "Boolean", "Integer", etc.)
     */
    public abstract String getType();
}
