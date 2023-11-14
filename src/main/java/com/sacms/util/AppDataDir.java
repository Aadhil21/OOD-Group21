package com.sacms.util;

import java.io.IOException;
import java.nio.file.Files;

public class AppDataDir {
    private static String appDataDir = null;

    private AppDataDir() {}
    public static String getAppDataDir() {
        if (appDataDir == null) {
            setAppDataDir();
        }

        return appDataDir;
    }

    private static void setAppDataDir() {
        String OS = System.getProperty("os.name").toUpperCase();
        if (OS.contains("WIN")) {
            appDataDir = System.getenv("APPDATA");
        } else if (OS.contains("MAC")) {
            appDataDir = System.getProperty("user.home") + "/Library/Application Support";
        } else if (OS.contains("NUX")) {
            appDataDir = System.getProperty("user.home") + "/.local/share";
        } else {
            appDataDir = System.getProperty("user.dir");
        }

        appDataDir += "/scam-ood-cw";

        try {
            Files.createDirectories(java.nio.file.Path.of(appDataDir));
        } catch (IOException e) {
            System.out.println("Error creating app data directory: " + e);
            appDataDir = System.getProperty("user.dir");
        }
    }
}
