package com.evergreen.robot;

import com.evergreen.everlib.shuffleboard.constants.ConstantBoolean;

/**
 * Utilities
 */
public class Utilities implements SubsystemConstants {
    
    public static ConstantBoolean smartP = new ConstantBoolean("Chassis Smart P");
    
    /**
     * Calculates the error of the chassis from the center of a cell using the {@link Groot#imageProccesing},
     * and then uses the Smart P fucntion to find the required fix.
     * 
     * The smart p function is built with two given parameters 
     */
    public static double getChassisFix() {
         double error;
         if (Groot.imageProccesing.getEntry("isUpdate0").getBoolean(false)
            && Groot.imageProccesing.getEntry("isUpdated1").getBoolean(false)) {

                double center = Groot.imageProccesing.getEntry("x0").getDouble(0)
                            + Groot.imageProccesing.getEntry("x1").getDouble(0)
                            / 2;
                error = 320 - center;
         }

         else error = 0;


         //Function from the form ax^2 + bx + c, but b = 0
        double a = ChassisConstants.smartPMaxFix.get() - ChassisConstants.maxStaticFriction.get()
            / Math.pow(320, 2);

        double b = 0;

        double c = ChassisConstants.maxStaticFriction.get();

        return a * error + b * error + c;
    }

}
