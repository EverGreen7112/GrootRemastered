/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.evergreen.everlib.oi;

/**
 * The class containing all exceptions of the Output-Input (<i>oi</i>) scope 
 */
public class OIExceptions {

    /**An exception to throw when trying to access the values of a non-existing joystick axis*/
    public static class InvalidAxisException extends RuntimeException
    {
        private static final String DEFAULT_MESSAGE = "The requested axis does not exist in input joystick!";

        private static final long serialVersionUID = 1L;

        /**Constructs a new {@link InvalidAxisException} which when throen, outputs the message  
         * "The requested axis does not exist in input joystick!"*/
        public InvalidAxisException() {
            super(DEFAULT_MESSAGE);
        }

        /**
         * Constructs a new {@link InvalidAxisException}, setting its cause to the input throwable, 
         * and its message into the default "The requested axis does not exist in input joystick!"
         * 
         * @param err - the cause for this exception
         */
        public InvalidAxisException(Throwable err) {
            super(DEFAULT_MESSAGE, err);
        }

        /**
         * Constructs a new {@link InvalidAxisException} which when thrown, outputs the input message 
         * @param message - the message to print when thrown.
         */
        public InvalidAxisException(String message) {
            super(message);
        }
        
        /**
         * Constructs a new {@link InvalidAxisException}, setting its message an input string and its cause
         * an input {@link Throwable}
         * @param message - the message to print when the exception is thrown
         * @param err - the cause of the error.
         */
        public InvalidAxisException(String message, Throwable err) {
            super(message, err);

        }
    }

}
