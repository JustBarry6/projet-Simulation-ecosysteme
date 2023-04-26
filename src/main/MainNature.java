import javax.swing.JFrame;
import java.util.Random;
import java.awt.Color;
import javax.swing.JPanel;

public class MainNature {
    public static void main(String[] args) {
        int nbCasesL = 5, nbCasesH = 6;
        // On crée une Grille "Logique" objet ChampGraphique de 50 cases de large, et 60
        // de haut de 20 pixels de côté
        GrilleNature grille = new GrilleNature(nbCasesL, nbCasesH, 100);

        Random r = new Random();

        int i, j;
        int p1 = 30; // Pourcentage de chance qu'un lapin soit placé dans une case
        int p2 = 10; // Pourcentage de chance qu'un aigle soit placé dans une case
        int nbLapins, nbAigles;

        for (i = 0; i < nbCasesL; i++)
            for (j = 0; j < nbCasesH; j++) {
                if (r.nextInt(100) < p1) { // Place un lapin aléatoirement dans la case avec probabilité p1
                    grille.addDisque(i, j, 5, Color.GREEN); // Couleur des lapins : vert
                }
                if (r.nextInt(100) < p2) { // Place un aigle aléatoirement dans la case avec probabilité p2
                    grille.addDisque(i, j, 5, Color.RED); // Couleur des aigles : rouge
                }
            }

        grille.redessine();

        // Puis, pause de 2s
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        for (i = 0; i < nbCasesL; i++) {
            for (j = 0; j < nbCasesH; j++) {
                nbLapins = grille.getNbDisques(i, j, Color.GREEN); // Compte le nombre de lapins dans la case
                nbAigles = grille.getNbDisques(i, j, Color.RED); // Compte le nombre d'aigles dans la case
                int rayonLapins = nbLapins * 2; // Rayon des disques de lapins proportionnel au nombre de lapins
                int rayonAigles = nbAigles * 2; // Rayon des disques d'aigles proportionnel au nombre d'aigles
                grille.clear(i, j); // Efface tous les disques de la case
                for (int k = 0; k < nbLapins; k++) {
                    grille.addDisque(i, j, rayonLapins, Color.GREEN); // Ajoute les disques de lapins
                }
                for (int k = 0; k < nbAigles; k++) {
                    grille.addDisque(i, j, rayonAigles, Color.RED); // Ajoute les disques d'aigles
                }
            }
        }

        grille.redessine();

        // Création et affichage de la fenêtre
        JFrame f = new JFrame("Simulation d'écosystème");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(grille.getZoneGraphique());
        f.pack();
        f.setVisible(true);

    }
}
