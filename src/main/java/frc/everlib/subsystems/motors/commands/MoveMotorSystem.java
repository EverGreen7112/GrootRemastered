package frc.everlib.subsystems.motors.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


import java.util.function.Supplier;

import frc.everlib.shuffleboard.handlers.Switch;
import frc.everlib.subsystems.motors.subsystems.MotorSubsystem;
import frc.everlib.utils.loggables.LoggableDouble;
import frc.everlib.utils.ranges.Range;

/**
 * Moves a {@link MotorSubsystem} according to iput speed and range.
 */
public class MoveMotorSystem extends SetMotorSystem {


  /**
   * Constructs a {@link MoveMotorSystem Move1ControlSystem Command} according to input parameters.
   * 
   * @param name - The name of the command, corresponding to its {@link #getName()} method and to its
   * {@link Switch shuffleboard switch}
   * @param subsystem - The subsystem to move.
   * @param speed - The speed supplier ywhich will supply the speed to move the subsystem.
   * @param speedRange - The range in which to move the subsystem.
   * @param log - whether to log this command on the shuffleboard 
   */
  public MoveMotorSystem(String name, MotorSubsystem subsystem, Supplier<Double> speed, Range speedRange,
  boolean log) {
    super(name, subsystem, speedRange,  getMap(speed, subsystem), log);
  }

  /**
   * Constructs a {@link MoveMotorSystem Move1ControlSystem Command} according to input parameters.
   * 
   * @param name - The name of the command, corresponding to its {@link #getName()} method and to its
   * {@link Switch shuffleboard switch}
   * @param subsystem - The subsystem to move.
   * @param speed - The speed supplier ywhich will supply the speed to move the subsystem.
   * @param speedRange - The range in which to move the subsystem.
   */
  public MoveMotorSystem(String name, MotorSubsystem subsystem, Supplier<Double> speed, Range speedRange ) {
    super(name, subsystem, speedRange,  getMap(speed, subsystem));
  }

  /**
   * Constructs a {@link MoveMotorSystem Move1ControlSystem Command} according to input parameters.
   * 
   * @param name - The name of the command, corresponding to its {@link #getName()} method and to its
   * {@link Switch shuffleboard switch}
   * @param subsystem - The subsystem to move.
   * @param speed - The speed supplier ywhich will supply the speed to move the subsystem.
   * @param speedModifier - the value modifier for the speed supplier fo this movement
   * @param speedRange - The range in which to move the subsystem.
   */
  public MoveMotorSystem(String name, MotorSubsystem subsystem, Supplier<Double> speed,
    Supplier<Double> speedModifier, Range speedRange ) {
    super(name, subsystem, speedRange, speedModifier, getMap(speed, subsystem));
  }


  /**
   * Constructs a {@link MoveMotorSystem Move1ControlSystem Command} according to input parameters.
   * 
   * @param name - The name of the command, corresponding to its {@link #getName()} method and to its
   * {@link Switch shuffleboard switch}
   * @param subsystem - The subsystem to move.
   * @param speed - The speed supplier ywhich will supply the speed to move the subsystem.
   * @param speedModifier - the value modifier for the speed supplier fo this movement
   * @param speedRange - The range in which to move the subsystem.
   * @param log - whether to log this command on the shuffleboard 
   */
  public MoveMotorSystem(String name, MotorSubsystem subsystem, Supplier<Double> speed,
    Supplier<Double> speedModifier, Range speedRange, boolean log) {
    super(name, subsystem, speedRange, speedModifier, getMap(speed, subsystem), log);
  }


  /**
  * Constructs a {@link MoveMotorSystem Move1ControlSystem Command} according to input parameters.
  * 
  * @param name - The name of the command, corresponding to its {@link #getName()} method and to its
  * {@link Switch shuffleboard switch}
  * @param subsystem - The subsystem to move.
  * @param speed - The speed supplier which will supply the speed to move the subsystem.
  * @param log - whether to log this command on the shuffleboard 
  */
  public MoveMotorSystem(String name, MotorSubsystem subsystem, Supplier<Double> speed, boolean log) {
    super(name, subsystem, getMap(speed, subsystem), log);
  }

  /**
  * Constructs a {@link MoveMotorSystem Move1ControlSystem Command} according to input parameters.
  * 
  * @param name - The name of the command, corresponding to its {@link #getName()} method and to its
  * {@link Switch shuffleboard switch}
  * @param subsystem - The subsystem to move.
  * @param speed - The speed supplier which will supply the speed to move the subsystem.
  */
  public MoveMotorSystem(String name, MotorSubsystem subsystem, Supplier<Double> speed) {
    super(name, subsystem, getMap(speed, subsystem));
  }


  /**
  * Constructs a {@link MoveMotorSystem Move1ControlSystem Command} according to input parameters.
  * 
  * @param name - The name of the command, corresponding to its {@link #getName()} method and to its
  * {@link Switch shuffleboard switch}
  * @param subsystem - The subsystem to move.
  * @param speed - The speed supplier ywhich will supply the speed to move the subsystem.
  * @param speedModifier - the value modifier for the speed supplier fo this movement
  * @param log - whether to log this command on the shuffleboard 
  */
  public MoveMotorSystem(String name, MotorSubsystem subsystem, Supplier<Double> speed, 
    Supplier<Double> speedModifier, boolean log) {
    super(name, subsystem, speedModifier, getMap(speed, subsystem), log);
  }

  /**
  * Constructs a {@link MoveMotorSystem Move1ControlSystem Command} according to input parameters.
  * 
  * @param name - The name of the command, corresponding to its {@link #getName()} method and to its
  * {@link Switch shuffleboard switch}
  * @param subsystem - The subsystem to move.
  * @param speed - The speed supplier ywhich will supply the speed to move the subsystem.
  * @param speedModifier - the value modifier for the speed supplier fo this movement
  */
  public MoveMotorSystem(String name, MotorSubsystem subsystem, Supplier<Double> speed, 
    Supplier<Double> speedModifier) {
    super(name, subsystem, speedModifier, getMap(speed, subsystem));
  }

  private static Map<Integer, Supplier<Double>> getMap(Supplier<Double> sup, MotorSubsystem subsystem)
  {
    Map<Integer, Supplier<Double>> map = new HashMap<>();

    for (int i = 0; i < subsystem.getMotorControllers().length; i++) {
      map.put(i, sup);
    }

    return map;
  }

  @Override
  protected List<LoggableDouble> getSpeedLoggables() {
    return List.of(new LoggableDouble(getName() + " - Speed", m_speedMap.get(0)));
  }
}
