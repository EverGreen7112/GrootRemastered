package com.evergreen.everlib.shuffleboard.constants.commands;

import com.evergreen.everlib.shuffleboard.constants.ConstantDouble;
import com.evergreen.everlib.shuffleboard.constants.ConstantInt;
import com.evergreen.everlib.utils.InstantCommandEG;

/**
 * IncreaseConstantPercentage
 */
public class IncreaseConstantPercentage extends InstantCommandEG {

    public IncreaseConstantPercentage(String name, ConstantDouble constant, double percentage) {
        super(name, () -> constant.increasePercentage(percentage));
    }

    public IncreaseConstantPercentage(String name, ConstantDouble constant, double percentage, double maxValue) {
        super(name, () -> constant.increasePercentage(percentage, maxValue));
    }

    public IncreaseConstantPercentage(String name, ConstantInt constant, int percentage) {
        super(name, () -> constant.increasePercentage(percentage));
    }

    public IncreaseConstantPercentage(String name, ConstantInt constant, int percentage, int maxValue) {
        super(name, () -> constant.increasePercentage(percentage, maxValue));
    }
}