package com.evergreen.everlib.shuffleboard.constants;

import java.util.ArrayList;
import java.util.List;

import com.evergreen.everlib.shuffleboard.handlers.Explorer;
import com.evergreen.everlib.subsystems.SubsystemEG;

import edu.wpi.first.wpilibj.Preferences;

/**
 * A singleton handler of the {@link Constant} objects' 
 * shuffleboard nabigation and collective actiions.
 */
public class DashboardConstants extends Explorer {
    
    /**A list of every constant created - they are added here during {@link Constant#Constant(String, Object) construction.} */
    private final List<Constant> m_constants = new ArrayList<>();

    /**The single {@link DashboardConstants} instance */
    private static final DashboardConstants m_instance = new DashboardConstants();

    /**Singleton Constructor - private empty/*/
    private DashboardConstants() {}

    
    /**
     * Moves specified constants into the specified folder.
     * @param folder - the folder to move the constants to. 
     * @param constants - the constants to move.
     */
    public void move(String folder, Constant... constants) {
        List.of(constants).forEach((c) -> c.move(folder));
    }

    /**
     * {@link Constant#reset() Resets} all shuffleboard constants.
     */
    public void resetValues()
    {
        for (Constant constant : m_constants) {
            constant.reset();
        }
    }

    /**
     * {@link Constant#remove() Removes} all shuffleboard constants from the shufflebaord.
     */
    public void resetBoard()
    {
        for (int i = 0; i < m_constants.size(); i++) {
            m_constants.get(i).remove();
            m_constants.remove(i);
        }
    }

    /**Deletes all constants from previous runs, and resets the rest.*/
    public void cleanBoard() {
        for (String key : Preferences.getInstance().getKeys()) {
            Preferences.getInstance().remove(key); 
        }

        for (Constant constant : m_constants) {
            constant.addToDashboard();
        }
    }


    /**
     * 
     * @return The single shuffleboard  instance
     */
    public static DashboardConstants getInstance() {
        return m_instance;
    }

    /**Adds a constant to the shufflebord */
    void addConstant(Constant constant) {
        m_constants.add(constant);
    }


    public void startConstantsOf(SubsystemEG subsystem) {
        cd("/" + subsystem.getName() + "/Constants");
    }

    public void startConstantsOf(String subsystemName) {
        cd("/" + subsystemName + "/Constants");
    }

    
    
}