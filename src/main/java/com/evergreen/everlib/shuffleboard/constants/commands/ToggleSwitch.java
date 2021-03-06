package com.evergreen.everlib.shuffleboard.constants.commands;

import java.util.List;

import com.evergreen.everlib.shuffleboard.constants.ConstantBoolean;
import com.evergreen.everlib.shuffleboard.loggables.LoggableData;
import com.evergreen.everlib.shuffleboard.loggables.LoggableString;
import com.evergreen.everlib.utils.InstantCommandEG;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


public class ToggleSwitch extends InstantCommandEG {
  private String m_switchPath;

  public ToggleSwitch(String name, ConstantBoolean booleanSwitch) {
    super(name, () -> booleanSwitch.toggle());
    m_switchPath = booleanSwitch.getPath();
  }

  @Override
  public List<LoggableData> getLoggableData() {
    return
      List.of(new LoggableString(
        getName() + "switch to toggle", //Number key if there is more than one
        () -> m_switchPath));
  }
}
