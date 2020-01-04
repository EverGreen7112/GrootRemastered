/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.evergreen.everlib.shuffleboard.constants.commands;
import com.evergreen.everlib.shuffleboard.constants.ConstantDouble;
import com.evergreen.everlib.shuffleboard.constants.ConstantInt;
import com.evergreen.everlib.utils.InstantCommandEG;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IncreaseConstantAbsolute extends InstantCommandEG {
  public IncreaseConstantAbsolute(String name, ConstantDouble constant, double change) {
    super(name, () -> constant.increaseAbsolute(change));
  }

  public IncreaseConstantAbsolute(String name, ConstantInt constant, int change) {
    super(name, () -> constant.increaseAbsolute(change));
  }
}
