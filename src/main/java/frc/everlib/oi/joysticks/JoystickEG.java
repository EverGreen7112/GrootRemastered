

package frc.everlib.oi.joysticks;
import java.util.Arrays;

import frc.everlib.utils.Adjuster;
import frc.everlib.oi.OIExceptions;
import frc.everlib.subsystems.motors.commands.MoveMotorSystem;
import frc.everlib.subsystems.motors.subsystems.MotorSubsystem;

import edu.wpi.first.wpilibj.Joystick;


/**
 * The base class for joystick in the Evergreen Framework.
 * 
 * A Joystick class to extend the WPILib joystick  and add it more automatic adjusting capabilities.
 * The class contains an {@link Adjuster adjuster} for each of the axes, which can be set using its
 * {@link #setAxisAdjuster(int, Adjuster)} method, as well  
 */
public class JoystickEG extends Joystick {
    
    private static final int AXES_NUM = 5;
    private static final Adjuster<Double> USELESS_ADJUSTER = (val) -> val;

    private boolean m_exponential = false;
    private boolean m_inverted = false;

    @SuppressWarnings("unchecked")
    private Adjuster<Double>[] m_adjusters = (Adjuster<Double>[]) new Adjuster[AXES_NUM]; 
    
    private Adjuster<Double> m_defaultAdjuster;
    
    public JoystickEG(int port)
    {
        super(port);
        m_defaultAdjuster = USELESS_ADJUSTER;
        Arrays.fill(m_adjusters, USELESS_ADJUSTER);
    }

    public JoystickEG(int port, Adjuster<Double> adjuster)
    {
        super(port);
        this.m_defaultAdjuster = adjuster;
        Arrays.fill(m_adjusters, m_defaultAdjuster);
    }
    
    public void setMovementByAxis(int axNum, MotorSubsystem subsystem)
    {
        subsystem.setDefaultCommand(new MoveMotorSystem(
            String.format("{0} Default Command (move by joytsick's {1})", subsystem.getName(), axNum),
             subsystem, 
             () ->  getRawAxis(axNum)));
    }

    public void setAxisAdjuster(Joystick.AxisType axis, Adjuster<Double> adjuster)
    {
        m_adjusters[axis.value] = adjuster;
    }

    @Override
    public double getRawAxis(int axis) throws OIExceptions.AxisDoesNotExistException
    {
        if (axis > AXES_NUM) throw new OIExceptions.AxisDoesNotExistException();

        double value = super.getRawAxis(axis);

        value = m_adjusters[axis].adjust(value);
        
        if (m_exponential) value *= Math.abs(value);
        if (m_inverted) value *= -1;

        return value;
    }

    public double getRawAxis(Joystick.AxisType axis) throws OIExceptions.AxisDoesNotExistException
    {
        return getRawAxis(axis.value);
    }

    public void setDefaultAdjuster(Adjuster<Double> adjuster)
    {
        m_defaultAdjuster = adjuster;
    }

    public void setExponential() {
        m_exponential = true;
    }

    public void setInverted() {
        m_inverted = true;
    }

    public void kill() {
        m_defaultAdjuster = (v) -> 0.0;
    }
}
