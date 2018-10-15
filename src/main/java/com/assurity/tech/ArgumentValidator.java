package com.assurity.tech;

public class ArgumentValidator {

    /**
     * Validates if object is null or empty in order to prevent nullpointer exceptions in the code.
     * @param obj
     */
    public static void checkArgumentNullOrEmpty (Object obj) {
        try {
            if (null == obj || obj == "") {
                throw new IllegalArgumentException ("Argument passed is either null or empty.");
            }
        } catch (IllegalArgumentException il) {
            System.exit (1);
        }
    }
}