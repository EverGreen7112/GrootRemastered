/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.everlib.shuffleboard.commands;

import java.util.List;

import frc.everlib.CommandEG;
import frc.everlib.shuffleboard.handlers.Switch;
import frc.everlib.shuffleboard.handlers.SwitchHandler;
import frc.everlib.utils.loggables.LoggableData;
import frc.everlib.utils.loggables.LoggableString;

/**A command that sets a {@link Switch shuffleboard switch} an input value. <p>
 * This command has a shuffleboard switch (set at constucion), which, if diabled,
 * will stop this command from running.
*/
public class SetSwitch extends CommandEG {

  private Switch[] m_switches;
  private boolean m_value;

  /**
   * Constructs a {@link CommandEG} which when ran, sets the input switches at input value
   * @param name - The name of this command and its shuffleboard switch.
   * @param value - The value to set the switches
   * @param switches - The switches to swt.
   */
  public SetSwitch(String name, boolean value, Switch... switches) {
    this(name, value, false, switches);
  }

  /**
   * Constructs a {@link CommandEG} which when ran, sets the input switches at input value
   * @param name - The name of this command and its shuffleboard switch.
   * @param value - The value to set the switches
   * @param switches - The switches to swt.
   */
  public SetSwitch(String name, boolean value, boolean log, Switch... switches) {
    super(name, log);
    this.m_switches = switches;
  }

  /**Runs once when this command is called: if this command is enabled, 
   * sets the switches given at construction to the value given at construction.*/
  @Override
  public void execute() 
  {
    if(getSwitch().get())
    {
      SwitchHandler.set(m_value, m_switches);
    }
  }

  /**Always returns true - this command should run only once.*/
  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public List<LoggableData> getLoggableData() {
    List<LoggableData> loggables = super.getLoggableData();

    for (int i = 0; i < m_switches.length; i++) {
      new LoggableString(
        getName() + "switch to set" + (m_switches.length > 1 ? "#" + i : ""), //Number key if there is more than one
        m_switches[i]::getKey);
    }

    return loggables;
  }

}
