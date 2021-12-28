package com.example.yokaigameisep;

import java.io.File;
import java.util.*;

public class SideDeck {

    private ArrayList<TipCard> tipPrepared;
    private Queue<TipCard> drawFile;

    public SideDeck(){
        //TipPrepared
        tipPrepared = new ArrayList<>();


        //lien pour le queueus : http://tutorials.jenkov.com/java-collections/queue.html
        drawFile = new LinkedList<TipCard>();
        ArrayList piocheNonMelangee = new ArrayList();//liste temporaire pour stocker les cartes créees

        //carte 1 couleurs
        for (int i=0; i<2;i++) {//on porcour deux fois la boucle
            TipCard indiceCardOneColour = new TipCard();
            indiceCardOneColour.setNbColors(1);
            indiceCardOneColour.setPrepared(false);
            ArrayList listCouleur = new ArrayList();//liste de valeur des couleurs des cartes
            listCouleur.add(1);
            listCouleur.add(2);
            listCouleur.add(3);
            listCouleur.add(4);
            int indiceAuHasard = (int) (Math.random() * (listCouleur.size() ));
            int couleur = (int) listCouleur.get(indiceAuHasard);
            listCouleur.remove(indiceAuHasard);
            indiceCardOneColour.setColorValue1(couleur);

            piocheNonMelangee.add(indiceCardOneColour); //on ajoute la carte à la file
        }

        //carte 2 couleurs
        for (int i=0; i<3;i++) {//on parcour trois fois la boucle
            TipCard indiceCardTwoColours = new TipCard();
            indiceCardTwoColours.setNbColors(2);
            indiceCardTwoColours.setPrepared(false);

            ArrayList listCouleur = new ArrayList();//liste de valeur des couleurs des cartes
            listCouleur.add(1);
            listCouleur.add(2);
            listCouleur.add(3);
            listCouleur.add(4);
            int indiceAuHasard = (int) (Math.random() * (listCouleur.size() ));
            int couleur = (int) listCouleur.get(indiceAuHasard);
            listCouleur.remove(indiceAuHasard);
            indiceCardTwoColours.setColorValue1(couleur);

            int indiceAuHasard1 = (int) (Math.random() * (listCouleur.size() ));
            int couleur1 = (int) listCouleur.get(indiceAuHasard1);
            listCouleur.remove(indiceAuHasard1);
            indiceCardTwoColours.setColorValue2(couleur1);

            piocheNonMelangee.add(indiceCardTwoColours);//on ajoute la carte à la file
        }
        //carte 3 couleurs
        for (int i=0; i<2;i++) {//on parcour deux fois la boucle
            TipCard indiceCardThreeColours = new TipCard();
            indiceCardThreeColours.setNbColors(3);
            indiceCardThreeColours.setPrepared(false);

            ArrayList listCouleur = new ArrayList();//liste de valeur des couleurs des cartes
            listCouleur.add(1);
            listCouleur.add(2);
            listCouleur.add(3);
            listCouleur.add(4);
            int indiceAuHasard = (int) (Math.random() * (listCouleur.size() ));
            int couleur = (int) listCouleur.get(indiceAuHasard);
            listCouleur.remove(indiceAuHasard);
            indiceCardThreeColours.setColorValue1(couleur);

            int indiceAuHasard1 = (int) (Math.random() * (listCouleur.size() ));
            int couleur1 = (int) listCouleur.get(indiceAuHasard1);
            listCouleur.remove(indiceAuHasard1);
            indiceCardThreeColours.setColorValue2(couleur1);

            int indiceAuHasard2 = (int) (Math.random() * (listCouleur.size() ));
            int couleur2 = (int) listCouleur.get(indiceAuHasard2);
            listCouleur.remove(indiceAuHasard2);
            indiceCardThreeColours.setColorValue3(couleur2);

            piocheNonMelangee.add(indiceCardThreeColours);//on ajoute la carte à la file
        }
        for (int i=0; i<7;i++) {
            int indiceAuHasard = (int) (Math.random() * (piocheNonMelangee.size() ));
            drawFile.add((TipCard) piocheNonMelangee.get(indiceAuHasard));
            piocheNonMelangee.remove(indiceAuHasard);

        }


    }
    public TipCard revealCard(){
        TipCard card = drawFile.remove();
        tipPrepared.add(card);

        return card;
    }
    public ArrayList<TipCard> getTipPrepared() {
        return tipPrepared;
    }
}





