package frc.everlib.shuffleboard.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Preferences;
import frc.everlib.subsystems.SubsystemEG;

/**
 * DashboardConstants
 */
public class DashboardConstants {
    private static Map<String, String> strings = new HashMap<>();
    private static Map<String, Boolean> booleans = new HashMap<>();
    private static Map<String, Double> doubles = new HashMap<>();
    private static Map<String, Integer> integers = new HashMap<>();

    public static Supplier<Double> addDouble(String name, Double value)
    {
        Preferences.getInstance().putDouble(name, value);
            
        System.out.println("Added \"" + name +"\" double constant: " + value);

        return () -> Preferences.getInstance().getDouble(name, 0);
    }


    public static Supplier<Integer> addInt(String name, int value, boolean printVerbose)
    {
        Preferences.getInstance().putInt(name, value);
        System.out.println("Added \"" + name + "\" integer constant:" + value);
        return () -> Preferences.getInstance().getInt(name, 0);
    }


    public static Supplier<String> addString(String name, String value)
    {
        Preferences.getInstance().putString(name, value);
        System.out.println("Added \"" + name + "\" string constant: " + value);
        return () -> Preferences.getInstance().getString(name, "Value not found");
    }


    public static Supplier<Boolean> addBoolean(String name, boolean value)
    {
        Preferences.getInstance().putBoolean(name, value);
        System.out.println("Added \"" + name + "\" boolean constant: " + value);
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


}