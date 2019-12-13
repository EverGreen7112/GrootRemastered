package com.evergreen.everlib.shuffleboard.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Preferences;
import com.evergreen.everlib.subsystems.SubsystemEG;
import com.wpilib2020.framework.SubsystemBase;

/**
 * DashboardConstants
 */
public class DashboardConstants {
    private static Map<String, String> strings = new HashMap<>();
    private static Map<String, Boolean> booleans = new HashMap<>();
    private static Map<String, Double> doubles = new HashMap<>();
    private static Map<String, Integer> integers = new HashMap<>();

    static {
        clean();
    }

    public static Supplier<Double> addDouble(String name, Double value)
    {
        Preferences.getInstance().putDouble(name, value);
            
        System.out.println(String.format("Added %s double constant: %n", name, value));

        return () -> Preferences.getInstance().getDouble(name, 0);
    }

    public static Supplier<Integer> addInt(String name, int value, boolean printVerbose)
    {
        Preferences.getInstance().putInt(name, value);

        if(printVerbose)
        {
            System.out.println(String.format("Added %s integer constant: %n", name, value));
        }

        return () -> Preferences.getInstance().getInt(name, 0);
    }


    public static Supplier<String> addString(String name, String value, boolean printVerbose)
    {
        Preferences.getInstance().putString(name, value);

        if(printVerbose)
        {
            System.out.println(String.format("Added %s string constant: %s", name, value));
        }

        return () -> Preferences.getInstance().getString(name, "Value not found");
    }


    public static Supplier<Boolean> addBoolean(String name, boolean value, boolean printVerbose)
    {
        Preferences.getInstance().putBoolean(name, value);

        if(printVerbose)
        {
            System.out.println(String.format("Added %s boolean constant: %s", name, value));
        }

        return () -> Preferences.getInstance().getBoolean(name, false);
    }

    
    public static Supplier<Boolean> addBoolean(String name, boolean value)
    {
        if (!Preferences.getInstance().containsKey(name))
            Preferences.getInstance().putBoolean(name, value);

        return () -> Preferences.getInstance().getBoolean(name, false);
    }


    public static void remove(String key)
    {
        strings.remove(key);
        integers.remove(key);
        doubles.remove(key);
        booleans.remove(key);
        Preferences.getInstance().remove(key);
    }

    public static void reset() {

        for (String key : strings.keySet()) {
            Preferences.getInstance().putString(key, strings.get(key));
        }

        for (String key : doubles.keySet()) {
            Preferences.getInstance().putDouble(key, doubles.get(key));
        }

        for (String key : integers.keySet()) {
            Preferences.getInstance().putInt(key, integers.get(key));
        }

        for (String key : booleans.keySet()) {
            Preferences.getInstance().putBoolean(key, booleans.get(key));
        }
    }

    public static void clean() {
        for (String entry : Preferences.getInstance().getKeys()) {
          Preferences.getInstance().remove(entry);
          System.out.println("entry " + entry + " deleted");
        }
    }


}