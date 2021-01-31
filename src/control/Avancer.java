package control;

import model.Etat;
import view.Affichage;

import javax.swing.*;

/**
 * Un thread pour avancer la position de la ligne brisée
 * @author: Hongyu YAN
 * @date: 20/01/2021
 * @Version V1.1.0
 */
public class Avancer implements Runnable{
    private Etat etat;
    private Affichage affichage;

    /**
     * Constructeur
     * @param etat
     * @param affichage
     */
    public Avancer(Etat etat, Affichage affichage) {
        this.etat = etat;
        this.affichage = affichage;
    }

    /**
     * Avancer la ligne brisée
     */
    @Override
    public void run() {
        while (!etat.testPerdu()) { //dès que l’ovale sort de la ligne brisée, l'avancement de la ligne s’arrête
            try {
                Thread.sleep(1000); // Mettre une pause de quelques millisecondes entre chaque avance
                etat.getParcours().setPosition();
                affichage.revalidate(); //forcer le dessin
                affichage.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Afficher un message de perdu
        JOptionPane.showMessageDialog(null,
                "Vous avez perdu!\nVotre score: " + etat.getParcours().getPosition(),
                "", JOptionPane.PLAIN_MESSAGE);
    }

}