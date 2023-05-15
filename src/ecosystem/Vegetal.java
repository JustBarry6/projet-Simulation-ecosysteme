package ecosystem;

import java.awt.Color;

public abstract class Vegetal {
    protected String nom;
    protected int rayon;
    protected int seuilEauCritique;   //Pour les seuils faudrait un seuil minimal et un seuil maximal
    protected int seuilTempCritique;
    protected Color couleur;

    public Vegetal(String nom, int rayon, int seuilEauCritique, int seuilTempCritique, Color couleur) {
        this.nom = nom;
        this.rayon = rayon;
        this.seuilEauCritique = seuilEauCritique;
        this.seuilTempCritique = seuilTempCritique;
        this.couleur = couleur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public int getSeuilEauCritique() {
        return seuilEauCritique;
    }

    public void setSeuilEauCritique(int seuilEauCritique) {
        this.seuilEauCritique = seuilEauCritique;
    }

    public int getSeuilTempCritique() {
        return seuilTempCritique;
    }

    public void setSeuilTempCritique(int seuilTempCritique) {
        this.seuilTempCritique = seuilTempCritique;
    }


    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

	public void consommerEau() {
		// TODO Auto-generated method stub
		
	}
}
