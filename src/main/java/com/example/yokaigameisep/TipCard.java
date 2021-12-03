package com.example.yokaigameisep;

public class TipCard {
    private int nbColors;

    public void setColorValue1(int colorValue1) {
        this.colorValue1 = colorValue1;
    }

    private boolean prepared;

    public int getNbColors() {
        return nbColors;
    }

    public void setNbColors(int nbColors) {
        this.nbColors = nbColors;
    }

    public boolean isPrepared() {
        return prepared;
    }

    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }

    public int getColorValue1() {
        return colorValue1;
    }

    public int getColorValue2() {
        return colorValue2;
    }

    public void setColorValue2(int colorValue2) {
        this.colorValue2 = colorValue2;
    }

    public int getColorValue3() {
        return colorValue3;
    }

    public void setColorValue3(int colorValue3) {
        this.colorValue3 = colorValue3;
    }

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
