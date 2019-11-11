package frc.everlib.subsystems.motors.commands;



import java.util.List;
import java.util.function.Supplier;

import frc.everlib.subsystems.motors.subsystems.MotorSubsystem;
import frc.everlib.utils.loggables.LoggableBoolean;
import frc.everlib.utils.loggables.LoggableData;
import frc.everlib.utils.loggables.LoggableDouble;
import edu.wpi.first.wpilibj.command.Command;



/**
 * A {@link Command} that moves the a motor subsystem in a straight line to a target by input supplier,
 *  using a <a href=https://whatis.techtarget.com/definition/bang-bang-control> bang-bang control 
 * algoritm</a>.
 */
public class MotorSystemBangBang extends MoveMotorSystem {
  
  /**Supplier of the target's ditance from the same point a the distance supplier mesures the 
   * subsystem's distance.*/ 
  public Supplier<Double> m_target;

  private boolean m_startedInFront;

  private final Supplier<Boolean> IN_FRONT_SUPPLIER = () -> m_subsystem.getDistance() > m_target.get();


  /**
  * The constructor for this class, which sets its speed and target. 
  * @param subsystem - The subsystem to be moved to target.
  * @param motorSpeed - The speed modifier of the subsystem as it moves forward to target. If the movemennt
  * will be backwards, this modifier will be inverted.
  * @param target -Supplier of the target's ditance from the same point the distanceSupplier mesures from.
  * @param targetName - The name of the target to move the subsystem to, to be used for this command's switch../
  */
  public MotorSystemBangBang(
    MotorSubsystem subsystem,
    Supplier<Double> motorSpeed, 
    Supplier<Double> target,
    String targetName,
    boolean log) {
        super(subsystem.getName() + " - Move to " + targetName, subsystem, motorSpeed);
        
        m_target = target;
        m_startedInFront = IN_FRONT_SUPPLIER.get();

        if (m_startedInFront) m_speedModifier = () -> -1.0; //If the subsystem should move backwards, invert the speed
  }

  /**
  * The constructor for this class, which sets its speed and target. 
  * @param subsystem - The subsystem to be moved to target.
  * @param motorSpeed - The speed modifier of the subsystem as it moves forward to target. If the movemennt
  * will be backwards, this modifier will be inverted.
  * @param target -Supplier of the target's ditance from the same point the distanceSupplier mesures from.
  * @param targetName - The name of the target to move the subsystem to, to be used for this command's switch../
  */
  public MotorSystemBangBang(
    MotorSubsystem subsystem,
    Supplier<Double> motorSpeed, 
    Supplier<Double> target,
    String targetName) {
      this(subsystem, motorSpeed, target, targetName, false);
  }
 
  /**If the subsystem passed the target, finish*/
  @Override
  public boolean isFinished() {
    return m_startedInFront != IN_FRONT_SUPPLIER.get() || super.isFinished();
  }



  @Override
  public List<LoggableData> getLoggableData() {
    List<LoggableData> loggables = super.getLoggableData();
    
    loggables.addAll(List.of(
      new LoggableDouble(getName() + " - Target", m_target),
      new LoggableDouble(getName() + " - distance from target", () -> m_target.get() - m_subsystem.getDistance()),
      new LoggableBoolean(getName() + " - In Front of Target", IN_FRONT_SUPPLIER)
    ));
    
    return loggables;
  }
}