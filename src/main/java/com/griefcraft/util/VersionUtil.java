package com.griefcraft.util;

import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class VersionUtil {
    private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?");

    public static int getMajorVersion() {
        return Integer.parseInt(getVersionParts(Bukkit.getVersion())[0]);
    }

    public static int getMinorVersion() {
        return Integer.parseInt(getVersionParts(Bukkit.getVersion())[1]);
    }

    public static int getPatchVersion() {
        return Integer.parseInt(getVersionParts(Bukkit.getVersion())[2]);
    }

    private static String[] getVersionParts(final String input) {
        final Matcher versionMatcher = VERSION_PATTERN.matcher(input);
        final String[] version = new String[]{"-1", "-1", "-1"};
        if (!versionMatcher.find()) {
            return version;
        }
        final String[] versionParts = versionMatcher.group().split("\\.");
        System.arraycopy(versionParts, 0, version, 0, versionParts.length);
        return version;
    }
}
