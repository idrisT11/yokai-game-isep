package com.example.yokaigameisep;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SideDeck {
    private ArrayList<IndiceCard> indicePrepared;
    private Queue<IndiceCard> indicePioche;

    public SideDeck(){
        indicePrepared = new ArrayList<IndiceCard>();
        indicePioche = new LinkedList<>();

    }


}
