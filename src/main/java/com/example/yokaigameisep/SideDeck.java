package com.example.yokaigameisep;

import java.io.File;
import java.util.*;

public class SideDeck {

    private ArrayList<TipCard>tipPrepared;
    private Queue<TipCard> drawFile;
    public SideDeck(){
        //lien pour le queueus : http://tutorials.jenkov.com/java-collections/queue.html
        drawFile = new LinkedList<TipCard>();
        ArrayList piocheNonMelangee = new ArrayList();//liste temporaire pour stocker les cartes créees

        //carte 1 couleurs
        for (int i=1; i<2;i++) {//on porcour deux fois la boucle
            TipCard indiceCardOneColour = new TipCard();
            indiceCardOneColour.setNbColors(1);
            indiceCardOneColour.setPrepared(false);
            List listCouleur = Arrays.asList(1, 2, 3, 4);//liste de valeur des couleurs des cartes
            int indiceAuHasard = (int) (Math.random() * (listCouleur.size() - 1));
            listCouleur.remove(indiceAuHasard);
            indiceCardOneColour.setColorValue1(indiceAuHasard);

            piocheNonMelangee.add(indiceCardOneColour); //on ajoute la carte à la file
        }

        //carte 2 couleurs
        for (int i=1; i<3;i++) {//on parcour trois fois la boucle
            TipCard indiceCardTwoColours = new TipCard();
            indiceCardTwoColours.setNbColors(2);
            indiceCardTwoColours.setPrepared(false);

            List listCouleur = Arrays.asList(1, 2, 3, 4);//liste de valeur des couleurs des cartes
            int indiceAuHasard = (int) (Math.random() * (listCouleur.size() - 1));
            listCouleur.remove(indiceAuHasard);
            indiceCardTwoColours.setColorValue1(indiceAuHasard);

            int indiceAuHasard1 = (int) (Math.random() * (listCouleur.size() - 1));
            listCouleur.remove(indiceAuHasard1);
            indiceCardTwoColours.setColorValue2(indiceAuHasard1);

            piocheNonMelangee.add(indiceCardTwoColours);//on ajoute la carte à la file
        }
        //carte 3 couleurs
        for (int i=1; i<2;i++) {//on parcour deux fois la boucle
            TipCard indiceCardThreeColours = new TipCard();
            indiceCardThreeColours.setNbColors(3);
            indiceCardThreeColours.setPrepared(false);

            List listCouleur = Arrays.asList(1, 2, 3, 4);//liste de valeur des couleurs des cartes
            int indiceAuHasard = (int) (Math.random() * (listCouleur.size() - 1));
            listCouleur.remove(indiceAuHasard);
            indiceCardThreeColours.setColorValue1(indiceAuHasard);
            int indiceAuHasard1 = (int) (Math.random() * (listCouleur.size() - 1));
            listCouleur.remove(indiceAuHasard1);
            indiceCardThreeColours.setColorValue2(indiceAuHasard1);
            int indiceAuHasard2 = (int) (Math.random() * (listCouleur.size() - 1));
            listCouleur.remove(indiceAuHasard2);
            indiceCardThreeColours.setColorValue3(indiceAuHasard2);

            piocheNonMelangee.add(indiceCardThreeColours);//on ajoute la carte à la file
        }
        for (int i=1; i<7;i++) {
            int indiceAuHasard = (int) (Math.random() * (piocheNonMelangee.size() - 1));
            valeurAjouter =piocheNonMelangee.get(indiceAuHasard);
            drawFile.add();
            piocheNonMelangee.remove(indiceAuHasard);
        }


        
        tipPrepared;

    }


    // Methods ..........................................
    public pickCardsDraw(){

    }
    public pickCardPrepared(){

    }

}
