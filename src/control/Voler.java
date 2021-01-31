package control;

import model.Etat;
import view.Affichage;

/**
 * Fait redescendre progressivement (linéairement) la valeur de la hauteur.
 * @author: Hongyu YAN
 * @date: 18/01/2021
 * @Version V1.1.0
 */
public class Voler implements Runnable{
    private Etat etat;
    private Affichage affichage;

    /**
     * Constructeur
     * @param etat
     * @param affichage
     */
    public Voler(Etat etat, Affichage affichage) {
        this.etat = etat;
        this.affichage = affichage;
    }

    /**
     * Exécuter les instructions, c'est à dire, faire redescendre l'ovale
     */
    @Override
    public void run() {
        while (!etat.testPerdu()) { //dès que l’ovale sort de la ligne brisée, l'ovale arrête de descendre
            try {
                Thread.sleep(120); // Mettre une pause de quelques millisecondes entre chaque chute.
                etat.moveDown();
                affichage.revalidate(); //forcer le dessin
                affichage.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
