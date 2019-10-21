package frc.everlib.subsystems;

import frc.everlib.Exceptions;
import frc.everlib.shuffleboard.handlers.Switch;
import frc.everlib.shuffleboard.handlers.SwitchHandler;
import frc.everlib.subsystems.sensors.DistanceSensor;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsys
 */
public abstract class SubsystemEG extends Subsystem implements Exceptions {

    protected Switch m_subsystemSwitch;
    private Command m_defaultCommand;
    private DistanceSensor m_distanceSensor;

    public SubsystemEG(String name) {
        super(name);
        m_subsystemSwitch  = SwitchHandler.addSwitch(name);
    }

    public SubsystemEG(String name, Command defaultCommand) {
        
        this(name);
        m_defaultCommand = defaultCommand;
    }

    public SubsystemEG(String name, Command defaultCommand, DistanceSensor distanceSesnsor) {
        this(name, defaultCommand);
        m_distanceSensor = distanceSesnsor;
    }

    public Switch getSwitch() {
        return m_subsystemSwitch;
    }

    public boolean getSwitchState() {
        return m_subsystemSwitch.get();
    }

    @Override
    protected void initDefaultCommand() {
        if (m_defaultCommand != null) {
            setDefaultCommand(m_defaultCommand);
        }
    }

    public double getDistance() throws SensorDoesNotExistException {
        try {
            return m_distanceSensor.getDistance();
        } 

        catch (NullPointerException e) {
            throw new SensorDoesNotExistException(getName() + " does not have a distance sensor!");
        }
    }
}