package frc.everlib.utils.ranges;

import java.util.function.Predicate;

/**
 * A functional interface used to limit movement or speed to a specific range. <p>
 *
 * It can be initialized directly like {@link Predicate Predicate<Double>}, or with
 * a subclass - {@link MinLimit}, {@link MaxLimit} or {@link DoubleLimit}
 */
public interface Range  {
    public boolean inRange(double value);
}
