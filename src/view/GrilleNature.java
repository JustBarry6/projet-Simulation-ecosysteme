import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

class GrilleNature {

    private CaseGrille[][] grille;
    private int caseSize;

    private JPanel zoneGraphique;

    GrilleNature(int nbCasesL, int nbCasesH, int caseSize) {
        grille = new CaseGrille[nbCasesL][nbCasesH];
        for (int i = 0; i < nbCasesL; i++) {
            for (int j = 0; j < nbCasesH; j++) {
                grille[i][j] = new CaseGrille();
            }
        }

        this.caseSize = caseSize;

        // Création de la zone graphique
        zoneGraphique = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessine(g);
            }
        };
        zoneGraphique.setPreferredSize(new java.awt.Dimension(nbCasesL * caseSize, nbCasesH * caseSize));
    }

    public void addDisque(int x, int y, int rayon, Color c) {
        grille[x][y].addDisque(rayon, c);
    }

    public void clear(int x, int y) {
        grille[x][y].lDisques.clear();
    }

    public int getNbDisques(int x, int y, Color c) {
        int nbDisques = 0;
        for (Disque d : grille[x][y].lDisques) {
            if (d.getCouleur().equals(c)) {
                nbDisques++;
            }
        }
        return nbDisques;
    }

    public void redessine() {
        zoneGraphique.repaint();
    }

    public JPanel getZoneGraphique() {
        return zoneGraphique;
    }

    public void dessine(Graphics g) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                dessineCase(g, grille[i][j], i, j);
            }
        }
    }

    public void dessineCase(Graphics g, CaseGrille c, int x, int y) {
        g.setColor(c.getCouleur());
        g.fillRect(x * caseSize, y * caseSize, caseSize, caseSize);

        for (Disque d : c.lDisques) {
            dessineDisque(g, d, x, y);
        }
    }

    public void dessineDisque(Graphics g, Disque d, int x, int y) {
        g.setColor(d.getCouleur());
        Point centre = new Point(x * caseSize + caseSize / 2, y * caseSize + caseSize / 2);
        g.fillOval(centre.x - d.getRayon(), centre.y - d.getRayon(), d.getRayon() * 2, d.getRayon() * 2);
    }
}
