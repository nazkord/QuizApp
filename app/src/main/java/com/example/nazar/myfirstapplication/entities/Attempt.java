package com.example.nazar.myfirstapplication.entities;

/**
 * Created by nazar on 18.03.2017.
 */

public class Attempt {

    private static final Attempt INSTANCE = new Attempt();

    int result;

    public static Attempt getInstance() {
        return INSTANCE;
    }

    public int getResult() {
        return result;
    }

    public void mistake() {
        result++;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
