package control;

import view.Affichage;
import model.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * La classe controller effectue les changements dans l'état et informe la vue d’un changement.
 * Lorsqu' on clique sur l'écran, c’est lui qui gère les événements.
 */
public class Control implements MouseListener {
    private Etat etat;
    private Affichage affichage;

    /**
     * Constructeur
     * @param etat
     * @param affichage
     */
    public Control(Etat etat, Affichage affichage) {
        this.etat = etat;
        this.affichage = affichage;
    }

    /**
     * Chaque fois que l’utilisateur clique avec la souris, monter l'ovale de quelques pixels.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (etat.testPerdu()) { //si l’ovale sort de la ligne brisée
            affichage.removeMouseListener(this); //l’interface ne réagisse plus aux clics souris
        }
        else {
            etat.jump();
            affichage.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
