/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.everlib.shuffleboard.commands;

import frc.everlib.CommandEG;
import frc.everlib.shuffleboard.handlers.Switch;
import frc.everlib.shuffleboard.handlers.SwitchHandler;
import frc.everlib.subsystems.SubsystemEG;

public class ToggleSwitch extends CommandEG {
  private Switch[] switches;

  public ToggleSwitch(String name, Switch... switches) {
    super(name);
    this.switches = switches;
  }
  
  public ToggleSwitch(String name, CommandEG... commands) {
    super(name);
    
    switches = new Switch[commands.length];

    for (int i = 0; i < commands.length; i++) {
      switches[i] = commands[i].getSwitch();
    }
  }

  public ToggleSwitch(String name, SubsystemEG... subsystems) {
      super(name);
      
      switches = new Switch[subsystems.length];

      for (int i = 0; i < subsystems.length; i++) {
        switches[i] = subsystems[i].getSwitch();
      }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SwitchHandler.toggle(switches);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }
}
