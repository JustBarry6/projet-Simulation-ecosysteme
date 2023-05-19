package ecosystem;

import java.awt.Color;

public abstract class Vegetal {
	protected String nom;
	protected int age = 1;
	protected int esperanceDeVie;
	protected int rayon;
	protected int seuilEauCritique;
	protected int seuilTempCritiqueMin; 
	protected int seuilTempCritiqueMax; 
	protected double qteEauConsommee;
	protected double maxEauConsommable;
	protected Color couleur;

	public Vegetal(String nom, int rayon, int seuilEauCritique, int seuilTempCritiqueMin,int seuilTempCritiqueMax, Color couleur) {
		this.nom = nom;
		this.rayon = rayon;
		this.seuilEauCritique = seuilEauCritique;
		this.seuilTempCritiqueMin = seuilTempCritiqueMin;
		this.seuilTempCritiqueMax = seuilTempCritiqueMax;
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

	public double getQteEauConsommee() {
		return qteEauConsommee ; 
	}
	
	public int getSeuilEauCritique() {
		return seuilEauCritique;
	}

	public void setSeuilEauCritique(int seuilEauCritique) {
		this.seuilEauCritique = seuilEauCritique;
	}

	public int getseuilTempCritiqueMin() {
		return seuilTempCritiqueMin;
	}
	
	public void setseuilTempCritiqueMin(int seuilTempCritiqueMin) {
		this.seuilTempCritiqueMin = seuilTempCritiqueMin ;
	}
	public int getseuilTempCritiqueMax() {
		return seuilTempCritiqueMax;
	}
	public void setseuilTempCritiqueMax(int seuilTempCritiqueMax) {
		this.seuilTempCritiqueMax = seuilTempCritiqueMax;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	public int getEsperanceDeVie() {
		return esperanceDeVie;
	}
	public int getAge() {
		return age;
	}
	
	// ! Il faudra aussi décrementer le niveau d'eau des vegetaux au cours de la
	// simulation

	// Penser a creer une exception si une zone ne contient plus d'eau
	public void consommerEau(Zone Z) throws NoWaterException {
	    double qteDisponible = Z.getNiveauEau();

	    if (qteDisponible != 0) {
	        if (qteEauConsommee < maxEauConsommable) {
	            double qteEauConsommable = maxEauConsommable - qteEauConsommee;
	            if (qteEauConsommable < qteDisponible) {
	                qteEauConsommee += qteEauConsommable;
	                Z.setNiveauEau(qteDisponible - qteEauConsommable);
	            } else {
	                qteEauConsommee += qteDisponible;
	                Z.setNiveauEau(0);
	            }
	        }
	    } else {
	        throw new NoWaterException("Plus d'eau disponible dans la zone !");
	    }
	}

	// Apres avoir consomme de l'eau, le vegetal l'utilise pour ses cellules
	public void utiliserEau()
	{
		qteEauConsommee -= 10 ; 
	}

	public void vieillir() {
		age += 1;
	}

	public void mourir(Zone Z) {
		Z.removeVegetal(this.getClass());
		System.out.println("Mort d'un Végétal\n") ; 
	}
}
