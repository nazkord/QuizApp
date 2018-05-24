package com.example.nazar.myfirstapplication.entities;

/**
 * Created by nazar on 16.03.2017.
 */

public enum Temperament {
    Sanquine,
    Phlegmatic,
    Choleric,
    Melancholic;

    public static String[] names() {
        Temperament[] temperaments = values();
        String[] names = new String[temperaments.length];

        for (int i = 0; i < temperaments.length; i++) {
            names[i] = temperaments[i].name();
        }

        return names;
    }
}
