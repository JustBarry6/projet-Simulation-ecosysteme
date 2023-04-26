import java.awt.Color;
import java.util.LinkedList;

class CaseGrille {

    private Color couleur;
    public LinkedList<Disque> lDisques;

    public CaseGrille() {
        lDisques = new LinkedList<Disque>();
    }

    public void setCouleur(Color c) {
        this.couleur = c;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void addDisque(int rayon, Color c) {
        lDisques.add(new Disque(rayon, c));
    }

    public int getNbDisques() {
        return lDisques.size();
    }
}
