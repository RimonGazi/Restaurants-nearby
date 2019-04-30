package com.rimon.restaurantsnearby.base;

public class CrashLibrary {
    public static void log(int priority, String tag, String message) {
        // Todo: 04/30/2019  GAZI RIMON) add log entry to circular buffer.
    }

    public static void logWarning(Throwable t) {
        // Todo: 04/30/2019 GAZI RIMON) report non-fatal warning.
    }

    public static void logError(Throwable t) {
        // Todo: 04/30/2019  report non-fatal error.
    }

    private CrashLibrary() {
        throw new AssertionError("No instances.");
    }
}
