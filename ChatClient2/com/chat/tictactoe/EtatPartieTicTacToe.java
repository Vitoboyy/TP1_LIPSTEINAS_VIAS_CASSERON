package com.chat.tictactoe;

import observer.Observable;

import javax.swing.*;

public class EtatPartieTicTacToe extends Observable{
    private char[][] etatPlateau = new char[3][3];

    public EtatPartieTicTacToe() {
        etatPlateau = new char[][]{
                {'.','.','.'},
                {'.','.','.'},
                {'.','.','.'}
        };
    }
    public boolean coup(String strCoup) {
        boolean res = false;
        String[] parties = strCoup.trim().split(" ");
        if (parties.length == 3) {
            try {
                char symbole = parties[0].charAt(0);
                int ligne = Integer.parseInt(parties[1]);
                int colonne = Integer.parseInt(parties[2]);

                if (ligne >= 0 && ligne <= 2 && colonne >= 0 && colonne <= 2) {
                    this.etatPlateau[ligne][colonne] = symbole;
                    setEtatPlateau(etatPlateau);
                    res = true;
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                res = false;

            }
        }
        return res;
    }
    @Override
    public String toString() {
        String s = "";
        for (byte i=0;i<etatPlateau.length;i++) {
            for (int j=0;j<etatPlateau[i].length;j++)
                s+=etatPlateau[i][j]+" ";
            s+="\n";
        }
        return s;
    }

    public char[][] getEtatPlateau() {
        return etatPlateau;
    }

    public void setEtatPlateau(char[][] etatPlateau) {
        this.etatPlateau = etatPlateau;
        notifierObservateurs();
    }


}
