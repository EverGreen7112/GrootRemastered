/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.everlib.subsystems.sensors;

import frc.everlib.subsystems.SubsystemEG;
import frc.everlib.utils.ranges.Range;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public abstract class DistanceSensor implements PIDSource {
    
    private Range m_absoluteLimits;
    private double m_offset;
    private String m_subsystemName;
    private PIDSourceType m_pidType;

    private boolean m_killSwitch;


    public DistanceSensor() {
        this( (v) -> true, 0);
    }

    public DistanceSensor(double offset) {
        this( (v) -> true, offset );
    }

    public DistanceSensor(Range absoluteLimit) {
        this(absoluteLimit, 0);
    }

    public DistanceSensor(Range absoluteLimit, double offset) {
        m_absoluteLimits = absoluteLimit;
        m_offset = offset;
    }

    protected abstract double _getDistance();
    
    public final double getDistance() 
    {
        double distance = _getDistance() + m_offset;

        if (!m_killSwitch && !m_absoluteLimits.inRange(distance)) {
            m_killSwitch = true;
        }

        return distance;

    }    

    public boolean inRange(double minDistance, double maxDistance)
    {
        return getDistance() > minDistance && getDistance() < maxDistance;
    }

    public boolean atLeast(double minDistance)
    {
        return getDistance() > minDistance;
    }

    public boolean atMost(double maxDistance)
    {
        return getDistance() < maxDistance;
    }

    public void setSubsystem(SubsystemEG subsystem) {
        m_subsystemName = subsystem.getName();
    }

    public String getSubsystem() {
        return m_subsystemName;
    }

    public boolean killed() {
        return m_killSwitch;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSourceType) {
        m_pidType = pidSourceType;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return m_pidType;
    }

    @Override
    public double pidGet() {
        return getDistance();
    }
}
