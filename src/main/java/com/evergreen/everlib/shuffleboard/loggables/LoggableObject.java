package com.evergreen.everlib.shuffleboard.loggables;

import java.util.List;

/**
 * LoggableObject
 */
public interface LoggableObject {

    public String getName();

    public List<LoggableData> getLoggableData();
}