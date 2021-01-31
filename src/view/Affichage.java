package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import model.*;

/**
 * Class Affichage s'occupe de construire l'interface
 */
public class Affichage extends JPanel {
    /** Largeur du panel*/
    public static final int LARG = 600;
    /** Hauteur du panel*/
    public static final int HAUT = 400;
    /**
     * L'ovale est dessiné à l'intérieur d'un rectangle
     * dont le coin supérieur gauche est à (X, Y)
     */
    public static final int X = 50;
    public static final int Y = 300;
    /** Largeur du rectangle*/
    public static final int WIDTH = 20;
    /** Hauteur du rectangle */
    public static final int HEIGHT = 100;
    /** L’épaisseur des déssins*/
    public static final int EPAISSEUR = 3;

    private Etat etat;
    private VueOiseau vueOiseau;

    /**
     * Constructeur
     * @param etat
     */
    public Affichage(Etat etat, VueOiseau vueOiseau) {
        this.etat = etat;
        this.vueOiseau = vueOiseau;
        //Definit la dimension d’un JPanel
        setPreferredSize(new Dimension(LARG,HAUT));
    }

    /**
     * Dessine dans un JPanel
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.clearRect(0,0, LARG, HAUT);
        super.paint(g2);
        //afficher le score du joueur
        //g.drawString("Score: " + etat.getParcours().getPosition(), 10, 20);
        Stroke g2d = g2.getStroke();
        //dessiner un ovale et une ligne brisée plus épais
        g2.setStroke(new BasicStroke(EPAISSEUR));
        g2.setColor(Color.GRAY);
        paintOvale(g2);
        paintLine(g2);
        g2.setStroke(g2d);
        //dessiner des oiseaux en background
        vueOiseau.dessiner(g);
    }

    /**
     * Dessine un ovale
     * @param g
     */
    public void paintOvale(Graphics g) {
        g.drawOval(X, etat.getHauteur(), WIDTH, HEIGHT);
    }

    /**
     * Dessine la ligne brisé
     * @param g
     */
    public void paintLine(Graphics g) {
        ArrayList<Point> points = etat.getParcours().getParcours();
        g.setColor(Color.RED); //mettre la ligne en rouge
        for (int i = 0; i < points.size() - 1; i++) {
            g.drawLine(points.get(i).x,
                       points.get(i).y,
                       points.get(i+1).x,
                       points.get(i+1).y);
        }
    }

    /**
     * Redessine l'affichage dans la zone d’invalidité dans l’interface
     * @param x
     * @param y
     * @param width
     * @param height
     */
    @Override
    public void repaint(int x, int y, int width, int height) {
        // Appele la méthode repaint de la classe parent pour dessiner directement le composant
        super.repaint(x, y, width, height);
    }

}
