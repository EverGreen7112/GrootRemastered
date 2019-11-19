package frc.everlib.subsystems;

import java.util.ArrayList;
import java.util.List;

import frc.everlib.Exceptions;
import frc.everlib.shuffleboard.handlers.Switch;
import frc.everlib.shuffleboard.handlers.SwitchHandler;
import frc.everlib.subsystems.sensors.DistanceSensor;
import frc.everlib.subsystems.sensors.DistanceSensorGroup;
import frc.everlib.utils.loggables.LoggableData;
import frc.everlib.utils.loggables.LoggableDouble;
import frc.everlib.utils.loggables.LoggableObject;
import frc.wpilib2020.framework.Command;
import frc.wpilib2020.framework.SubsystemBase;


/**
 * The subsys
 */
public abstract class SubsystemEG extends SubsystemBase implements Exceptions, LoggableObject {

    protected Switch m_subsystemSwitch;
    private DistanceSensor m_distanceSensor;

    public SubsystemEG(String name) {
        setName(name);
        m_subsystemSwitch  = SwitchHandler.addSwitch(name);
    }

    public SubsystemEG(String name, Command defaultCommand) {
        this(name);
        setDefaultCommand(defaultCommand);
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


    public double getDistance() throws SensorDoesNotExistException {
        try {
            return m_distanceSensor.getDistance();
        } 

        catch (NullPointerException e) {
            return 0; //throw new SensorDoesNotExistException(getName() + " does not have a distance sensor!");
            //TODO find issue
        }
    }

    @Override
    public List<LoggableData> getLoggableData() {
        List<LoggableData> loggables = new ArrayList<>();
        
        if (m_distanceSensor instanceof DistanceSensorGroup) {
            DistanceSensorGroup sensorGroup = (DistanceSensorGroup)m_distanceSensor;
            loggables.addAll(sensorGroup.getLoggableData());
        }

        loggables.add(new LoggableDouble(getName() + " - distance", () -> getDistance()));

        return loggables;
    }


}