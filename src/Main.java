import control.*;
import model.*;
import view.Affichage;
import view.VueOiseau;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Générer des oiseaux
        VueOiseau vueOiseau = new VueOiseau();
        //Générer aléatoirement les coordonnées de la ligne brisé
        Etat etat = new Etat(new Parcours());
        Affichage aff = new Affichage(etat,vueOiseau);
        Control control = new Control(etat, aff);
        aff.addMouseListener(control);

        // Un thread qui permet de redescendre progressivement la valeur de la hauteur
        new Thread(new Voler(etat, aff)).start();
        // Un thread qui permet d'avancer la ligne
        new Thread(new Avancer(etat,aff)).start();
        // Un thread qui permet de déplacer les oiseaux
        new Thread(new Oiseau(vueOiseau, aff, etat)).start();

        JFrame fenetre = new JFrame("Vole, vole, petit oiseau!");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(aff);
        //une fois que les composants ont été ajoutés, il faut l’assembler
        fenetre.pack();
        //rendre la fenetre visible
        fenetre.setVisible(true);
        //mettre la fenêtre au centre de l'écran
        fenetre.setLocationRelativeTo(null);
    }
}
