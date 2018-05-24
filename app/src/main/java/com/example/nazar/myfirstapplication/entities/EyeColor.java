package com.example.nazar.myfirstapplication.entities;

/**
 * Created by nazar on 16.03.2017.
 */

public enum EyeColor {
    Blue,
    Green,
    Gray,
    Brown;

    public static String[] names() {
        EyeColor[] eyeColors = values();
        String[] names = new String[eyeColors.length];

        for (int i = 0; i < eyeColors.length; i++) {
            names[i] = eyeColors[i].name();
        }

        return names;
    }
}
