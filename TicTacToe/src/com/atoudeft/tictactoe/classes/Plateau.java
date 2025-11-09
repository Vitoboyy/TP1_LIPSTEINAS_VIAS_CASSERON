package com.atoudeft.tictactoe.classes;

import com.atoudeft.tictactoe.MethodeNonImplementeeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class Plateau {
    private final Symbole[][] grille = new Symbole[3][3];
    private int casesRemplies = 0;
    public Symbole get(int ligne, int colonne) { return grille[ligne][colonne]; }
    public boolean estVide(Position p) { return grille[p.getLigne()][p.getColonne()] == null; }
    public int getNombreCasesRemplies() { return casesRemplies; }
    public boolean estPlein() { return casesRemplies == 9; }
    public boolean placer(Coup coup) {
        Position p = coup.getPosition();
        if(!estVide(p)) return false;
        grille[p.getLigne()][p.getColonne()] = coup.getSymbole();
        casesRemplies++;
        return true;
    }
    public List<Position> ligneGagnante() {
        int[][][] lignes = {
            {{0,0},{0,1},{0,2}}, {{1,0},{1,1},{1,2}}, {{2,0},{2,1},{2,2}},
            {{0,0},{1,0},{2,0}}, {{0,1},{1,1},{2,1}}, {{0,2},{1,2},{2,2}},
            {{0,0},{1,1},{2,2}}, {{0,2},{1,1},{2,0}}
        };
        boolean gagnanteX;
        ArrayList<Position> posX = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            posX.clear();
            gagnanteX = true;
            for (int j = 0; j < 3; j++) {
                if (grille[lignes[i][j][0]][lignes[i][j][1]] == Symbole.X) {
                    posX.add(new Position(lignes[i][j][0],lignes[i][j][1]));
                }
                else{
                    gagnanteX = false;
                    break;
                }
            }
            if(gagnanteX){
                return posX;
            }
        }

        boolean gagnanteO;
        ArrayList<Position> posO = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            posO.clear();
            gagnanteO = true;
            for (int j = 0; j < 3; j++) {
                if (grille[lignes[i][j][0]][lignes[i][j][1]] == Symbole.O) {
                    posO.add(new Position(lignes[i][j][0],lignes[i][j][1]));
                }
                else{
                    gagnanteO = false;
                    break;
                }
            }
            if(gagnanteO){
                return posO;
            }
        }

        return Collections.emptyList();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Symbole s = grille[i][j];
                if (s == null) {
                    sb.append(".");
                }
                else {
                    sb.append(s.toString());
                }
                if (j < 2) {
                    sb.append(" ");
                }
            }
            if (i < 2) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}