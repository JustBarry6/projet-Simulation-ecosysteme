import java.awt.Color;

public class CercleAnime extends Thread {
    private int x;
    private int y;
    private int rayon;
    private Color couleur;
    private GrilleNature grille;

    public CercleAnime(int x, int y, int rayon, Color couleur) {
        this.x = x;
        this.y = y;
        this.rayon = rayon;
        this.couleur = couleur;
    }

    public void setGrille(GrilleNature grille) {
        this.grille = grille;
    }

    @Override
    public void run() {
        while (true) {
            seDeplacer();
            grille.redessine();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void seDeplacer() {
        // Implémentation du déplacement du cercle animé
        // ...
    }
}
