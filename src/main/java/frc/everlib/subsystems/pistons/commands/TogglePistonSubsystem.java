package frc.everlib.subsystems.pistons.commands;

import frc.everlib.CommandEG;
import frc.everlib.subsystems.pistons.subsystems.PistonSubsystem;

/**
 * TogglePistonSubsystem
 */
public class TogglePistonSubsystem extends CommandEG {

   PistonSubsystem m_pistons;
  
   public TogglePistonSubsystem(String name, PistonSubsystem pistons) {
       super(name, pistons);
       m_pistons = pistons;
   }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      m_pistons.toggle();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }
}
