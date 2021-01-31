package model;

import view.Affichage;
import java.awt.*;
import java.util.ArrayList;

/**
 * La classe Etat définit le hauteur qui caractérise l’état de l’interface.
 * La modification de hauteur correspond à un changement de l’affichage dans l’interface graphique.
 */
public class Etat {
    /** La valeur de la hauteur de quelques pixels vers le haut */
    public static final int SAUT = 20;
    /** La valeur de la hauteur de quelques pixels vers le bas*/
    public static final int DESC = 5;

    private Parcours parcours;
    private int hauteur;

    /**
     * Constructeur
     * @param parcours
     */
    public Etat(Parcours parcours) {
        this.hauteur = Affichage.Y;
        this.parcours = parcours;
    }

    /**
     * Obtenir le hauteur actuel de l'ovale
     * @return
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Getter
     * @return
     */
    public Parcours getParcours() {
        return this.parcours;
    }

    /**
     * Changer l'hauteur de l'ovale
     */
    public void jump() {
        if (this.hauteur > 0) { //on arrête de monter l'ovale si il arrive en haut de la fenêtre
            this.hauteur -= SAUT;
        }
    }

    /**
     * Modifier la valeur de la hauteur de quelques pixels vers le bas
     */
    public void moveDown() {
        if (this.hauteur < Affichage.HAUT - Affichage.HEIGHT) {  //sans sortir de la zone de dessin
            this.hauteur += DESC;
        }
    }

    /**
     * tester si l'ovale sort de la ligne
     * @return
     */
    public boolean testPerdu() {
        Point p0 = null;
        Point p1 = null;
        double x = Affichage.X + Affichage.WIDTH/2;
        double haut = this.hauteur - Affichage.EPAISSEUR/2;
        ArrayList<Point> points = this.getParcours().getParcours();
        for (int i = 0; i < points.size()-1; i++) {
            if (points.get(i).x <= x && points.get(i+1).x >= x) {
                p0 = points.get(i);
                p1 = points.get(i+1);
            }
        }


        // pente de la ligne
        double pente = (p1.y - p0.y)*1.0/(p1.x - p0.x);
        double b = (p1.x*p0.y - p0.x*p1.y)*1.0/(p1.x-p0.x);
        // la valeur de l’ordonnée sur la ligne brisée au point d’abscisse correspondant à la position 0 de l’ovale
        double y = pente * x + b;

        if (y <= (haut + Affichage.HEIGHT + Affichage.EPAISSEUR) && haut <= y) {
            return false;
        }
        return true;

    }

}

