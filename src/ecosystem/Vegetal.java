package ecosystem;

import java.awt.Color;

public abstract class Vegetal {
    protected String nom;
    protected int rayon;
    protected int seuilEauCritique;   
    protected int seuilTempCritique; //Pour la temperature il faudrait un seuil minimal et un seuil maximal
    protected double qteEauConsommee ; 
    protected double maxEauConsommable ; 
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
    
    //! Il faudra aussi décrementer le niveau d'eau des vegetaux au cours de la simulation

    // Penser a creer une exception si une zone ne contient plus d'eau 
	public void consommerEau(Zone Z) {
		double qteDisponible = Z.getNiveauEau();
		
		if (qteDisponible != 0)
		{
			if (qteEauConsommee < maxEauConsommable ) {
				double qteEauConsommable = maxEauConsommable  - qteEauConsommee ; 
				if (qteEauConsommable < qteDisponible) // Cas où la qte disponible est suffisante pour "rassasier" le vivace
				{
					qteEauConsommee += qteEauConsommable ; 
					Z.setNiveauEau(qteDisponible - qteEauConsommable);
				}
				else // Cas où il ne reste pas assez d'eau pour que le vivace soit "rassasié"
				{
					qteEauConsommee += qteDisponible ; 
					Z.setNiveauEau(0);
				}	
			}
		}
		else
			System.out.println("Plus D'eau Disponible\n") ; 
	}
}
