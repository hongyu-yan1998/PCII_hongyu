package model;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import view.Affichage;

/**
 * Générer aléatoirement les coordonnées de la ligne brisé
 * @author: Hongyu YAN
 * @date: 18/1/2021$ $
 * @Version V1.1.0
 */
public class Parcours {
    /** la valeur de l’incrément de la position */
    public static final int AVANCE = 1;

    private int position;
    private ArrayList<Point> points = new ArrayList<>();
    /**
     * Constructeur
     */
    public Parcours() {
        this.position = 0;
        // Déterminez la position initiale de la ligne
        int x = Affichage.WIDTH/2 + Affichage.X;
        int y = Affichage.HEIGHT/2 + Affichage.Y;
        this.points.add(new Point(x, y));
        // Générez la ligne
        ajouterPoint();
    }

    /**
     * getter pour récupérer la valeur courante de la position
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     * setter pour faire avancer la position de quelques pixels
     */
    public void setPosition() {
        this.position += AVANCE;
    }

    /**
     * Ajouter un point dans la liste
     */
    public void ajouterPoint() {
        int x, y;
        do {
            //x est plus grand que l'ancien point
            x = new Random().nextInt(300) + points.get((points.size() - 1)).x + 60;
            y = new Random().nextInt(Affichage.HAUT - 150) + 100;
            Point p = new Point(x,y);
            this.points.add(p);
        } while (x <= Affichage.LARG);

    }


    /**
     * Liste de points
     * @return
     */
    public ArrayList<Point> getParcours() {
        // les coordonnées ont été calculées en retirant la valeur de position à leur valeur d’abscisse.
        this.points.forEach(e->e.x -= this.position);

        // générer un point supplémentaire Lorsque le dernier point rentre dans la fenêtre visible
        if (points.get((points.size() - 1)).x < Affichage.LARG) {
            int x = new Random().nextInt(300) + points.get((points.size() - 1)).x + 60;
            int y = new Random().nextInt(Affichage.HAUT - 150) + 100;
            Point p = new Point(x,y);
            this.points.add(p);
        }

        // retirer le premier point lorsque le deuxième point sur de la zone visible
        if (points.get(1).x < 0) {
            points.remove(0);
        }

        return this.points;
    }

}
