package frc.everlib.subsystems.motors.commands;



import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.everlib.subsystems.motors.subsystems.MotorSubsystem;



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
  * @param speedModifier - The speed modifier of the subsystem as it moves forward to target. If the movemennt
  * will be backwards, this modifier will be inverted.
  * @param target -Supplier of the target's ditance from the same point the distanceSupplier mesures from.
  * @param targetName - The name of the target to move the subsystem to, to be used for this command's switch../
  */
  public MotorSystemBangBang(
    MotorSubsystem subsystem,
    Supplier<Double> speedModifier, 
    Supplier<Double> target,
    String targetName) {
        super(subsystem.getName() + " - Move to " + targetName, subsystem, speedModifier);
        
        m_target = target;
        m_startedInFront = IN_FRONT_SUPPLIER.get();

        if (m_startedInFront) m_speedModifier = () -> -1.0; //If the subsystem should move backwards, invert the speed
  }
 
  /**If the subsystem passed the target, finish*/
  @Override
  protected boolean isFinished() {
    return m_startedInFront != IN_FRONT_SUPPLIER.get() || super.isFinished();
  }
}