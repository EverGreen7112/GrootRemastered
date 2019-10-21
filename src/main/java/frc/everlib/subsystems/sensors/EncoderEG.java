package frc.everlib.subsystems.sensors;

import edu.wpi.first.wpilibj.Encoder;
import frc.everlib.utils.ranges.Range;

/**
 * A wrapper class for {@link Encoder} which extends {@link DistanceSensor},
 * and as such allowes for easier calibration and error-proofing.
 * 
 * @author Atai Ambus
 */
public class EncoderEG extends DistanceSensor {

    /**
     * The wrapped encoder object.
     */
    private Encoder m_encoder;

    /**
     * Constructs an {@link EncoderEG} object according to input sources, encoder offset and 
     * limits.
     * @param portA - The first source of the encoder.
     * @param portB - The second source fo the encoder.
     * @param absoluteLimit - The absolute limits that it should  be able to mesure. for example, an
     * elevator's height.
     * @param offset - The offset from the point of mesurement. For example, its height on an elevator.
     */
    //All other constructors call this one, fillinng the gaps with default values.
    public EncoderEG(int portA, int portB, Range absoluteLimit, double offset) {
        super(absoluteLimit, offset);
        m_encoder = new Encoder(portA, portB);
    }

    /**
     * Constructs an {@link EncoderEG} object according to input sources, encoder offset and 
     * limits.
     * @param portA - The first source of the encoder.
     * @param portB - The second source fo the encoder.
     * @param absoluteLimit - The absolute limits that it should  be able to mesure. for example, an
     * elevator's height.
     * @param offset - The offset from the point of mesurement. For example, its height on an elevator.
     * @param distancePerPulse - the distance the subsystem passes with each encoder tick.
     */
    public EncoderEG(int portA, int portB, Range absoluteLimit, double offset, double distancePerPulse) {
        this(portA, portB, absoluteLimit, offset);
        m_encoder.setDistancePerPulse(distancePerPulse);
    }

    
    /**
     * Constructs an {@link EncoderEG} object according to input sources.
     * limits.
     * @param portA - The first source of the encoder.
     * @param portB - The second source fo the encoder.
     */
    public EncoderEG(int portA, int portB) {  
        this(portA, portB, (v) -> true, 0);
    }


    /**
     * Constructs an {@link EncoderEG} object according to input sources and encoder offset.
     * @param portA - The first source of the encoder.
     * @param portB - The second source fo the encoder.
     * @param offset - The offset from the point of mesurement. For example, its height on ann elevator.
     */
    public EncoderEG(int portA, int portB, double offset) {
        this(portA, portB, (v) -> true, offset);        
    }


    /**
     * Constructs an {@link EncoderEG} object according to input sources and limits.
     * @param portA - The first source of the encoder.
     * @param portB - The second source fo the encoder.
     * @param absoluteLimit - The absolute limits that it should  be able to mesure. for example, an
     * elevator's height.
     */
    public EncoderEG(int portA, int portB, Range absoluteLimit) {
        this(portA, portB, absoluteLimit, 0);
    }

    /**
     * Sets the distance per pulse according to an input value. 
     * @param value - the value to set.
     */
    public void setDistancePerPulse(double value) {
        m_encoder.setDistancePerPulse(value);
    }

    /** 
     * Sets the distance per pulse according to the encoder's ticks per mototr rotation and
     * the diameter of wheels.
     * @param ticksPerRevolution - the amount of ticks per motor revolutoion.
     * @param diameter - The amount of ticks per
    */
    public void setDistancePerPulse(double ticksPerRevolution, double diameter) {
        m_encoder.setDistancePerPulse(1/ticksPerRevolution * diameter * Math.PI);
    }

    public void reset() {
        m_encoder.reset();
    }

    public Encoder getEncoder() {
        return m_encoder;
    }

    @Override
    protected double _getDistance() {
        return m_encoder.getDistance();
    }
}