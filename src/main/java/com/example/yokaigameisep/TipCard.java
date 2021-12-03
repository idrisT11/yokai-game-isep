package com.example.yokaigameisep;

public class TipCard {
    private int nbColors;
    private boolean prepared;
    private int colorValue1, colorValue2, colorValue3;

    @Override
    public String toString() {
        return nbColors +
                ":" + prepared +
                ":" + colorValue1 +
                ":" + colorValue2 +
                ":" + colorValue3;
    }
}
