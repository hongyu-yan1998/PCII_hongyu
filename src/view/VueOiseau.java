package view;

import control.Oiseau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * l’affichage des oiseaux
 * @author: Hongyu YAN
 * @date: 30/01/2021$ $
 * @Version V1.1.0
 */
public class VueOiseau extends JPanel {
    private ArrayList<Oiseau> oiseaux = new ArrayList<>();

    /**
     * Ajouter un oiseau dans la liste
     * @param oiseau
     */
    public void ajouterOiseau(Oiseau oiseau) {
        this.oiseaux.add(oiseau);
    }

    /**
     * Retirer un oiseau
     */
    public void eliminerOiseau() {
        this.oiseaux.remove(0);
    }

    /**
     * Dessine des oiseaux
     * @param g
     */
    public void dessiner(Graphics g) {
        for (Oiseau o: oiseaux) {
            Image img = new ImageIcon("src/Oiseau/"+ o.getEtat()+".png").getImage();
            g.drawImage(img, o.getPosition(), o.getHauteur(),80,80, null);
        }
    }
}
