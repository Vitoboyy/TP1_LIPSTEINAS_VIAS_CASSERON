package com.atoudeft.tictactoe.classes;

import com.atoudeft.tictactoe.MethodeNonImplementeeException;

import java.util.Collections;

public final class Partie {
    private final Plateau plateau = new Plateau();
    private Symbole joueurCourant;
    private StatutPartie statut;

    public Plateau getPlateau()       {
        return plateau;
    }
    public Symbole getJoueurCourant() {
       return joueurCourant;
    }
    public StatutPartie getStatut()   {
        return statut;
    }

    public Partie(Symbole joueurCourant) {
        this.joueurCourant = joueurCourant;
        statut = StatutPartie.EN_COURS;
    }
    public Partie() {
        this(Symbole.X);
    }

    public boolean jouer(Symbole symbole, Position position) {
        if(!plateau.estVide(position)) return false;
        if(!isPartieEnCours()) return false;
        if(!symbole.equals(this.joueurCourant)) return false;
        plateau.placer(new Coup(position,symbole));
        mettreAJourStatutApresCoup();
        if(joueurCourant.equals(Symbole.X)) joueurCourant = Symbole.O;
        else joueurCourant = Symbole.X;
        return true;
    }
    public boolean isPartieEnCours() {
        if (statut != StatutPartie.EN_COURS) {
            return false;
        }
        return true;
    }
    private void mettreAJourStatutApresCoup() {
        if (!plateau.ligneGagnante().isEmpty() && joueurCourant == Symbole.X){
            statut = StatutPartie.X_GAGNE;
        }else if (!plateau.ligneGagnante().isEmpty() && joueurCourant == Symbole.O){
            statut = StatutPartie.O_GAGNE;
        }else if (plateau.estPlein()) {
            statut = StatutPartie.NULLE;
        }
    }

    @Override
    public String toString() {
        String str = "";
        str = plateau +"\n"
                +"Joueur Courant : " + joueurCourant +"\n"
                +"Etat : " + statut + "\n";
        return str;
    }
}