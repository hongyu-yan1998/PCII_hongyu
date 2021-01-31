package control;

import model.Etat;
import view.Affichage;
import view.VueOiseau;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author: Hongyu YAN
 * @date: 30/01/2021$ $
 * @Version V1.1.0
 */
public class Oiseau implements Runnable{
    /** la valeur de l’incrément de la position */
    public static final int AVANCE = 30;
    // le temps (en millisecondes) entre chaque mise à jour de l’affichage pour l’oiseau
    private int delai;
    /* position de l’oiseau
     * Buffer contenant l'image à charger
     */
    private int etat;
    // la hauteur de l’oiseau
    private int hauteur;
    // l’abscisse de l’oiseau
    private int position;
    private int count = 0;

    private VueOiseau vueOiseau;
    private Affichage affichage;
    private Etat etatOiseau;

    public Oiseau(VueOiseau vueOiseau, Affichage affichage, Etat etatOiseau) {
        // générer aléatoirement le temps entre chaque affichage de l'oiseau
        this.delai = new Random().nextInt(300) + 200;
        this.etat = 0;
        // générer aléatoirement le hauteur de l'oiseau
        this.hauteur = new Random().nextInt(Affichage.HAUT - 200);
        this.position = Affichage.LARG;
        this.vueOiseau = vueOiseau;
        this.affichage = affichage;
        this.etatOiseau = etatOiseau;
        // si on cree un oiseau, on l'ajoute dans la liste
        this.vueOiseau.ajouterOiseau(this);
    }

    public int getEtat() {
        return etat;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getPosition() {
        return position;
    }

    public void setEtat() {
        if(this.etat == 7)
            this.etat = 0;
        else
            this.etat ++;
    }

    @Override
    public void run() {
        boolean flag = true; // pour savoir si l'oiseau sort de le fenetre
        while(flag && !etatOiseau.testPerdu()) {
            count++;
            try {
                Thread.sleep(delai);
                // met a jour la position de l'oiseau
                this.position -= AVANCE;
                // met a jour l'etat de l'oiseau
                this.setEtat();
                // si l'oiseau sort de la fenetre, on l'élimine
                if (this.position < 0) {
                    this.vueOiseau.eliminerOiseau();
                    flag = false;
                }
                affichage.revalidate(); // forcer le dessin
                affichage.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
