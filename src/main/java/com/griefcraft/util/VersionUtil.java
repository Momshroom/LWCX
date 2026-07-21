package com.griefcraft.util;

import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class VersionUtil {
    private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?");

    public static boolean isAtLeast(final int major, final int minor, final int patch) {
        final String[] versionParts = getVersionParts(Bukkit.getVersion());
        final int majorPart = Integer.parseInt(versionParts[0]);
        if (majorPart > major) {
            return true;
        }
        final int minorPart = Integer.parseInt(versionParts[1]);
        if (majorPart == major && minorPart > minor) {
            return true;
        }
        final int patchPart = Integer.parseInt(versionParts[2]);
        return majorPart == major && minorPart == minor && patchPart >= patch;
    }

    public static boolean isAtLeast(final int major, final int minor) {
        return isAtLeast(major, minor, -1);
    }

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
