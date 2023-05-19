package ecosystem;

import java.awt.Color;

/**
 * La classe abstraite Vegetal représente un végétal dans notre écosystème simulé.
 */
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

    /**
     * Constructeur de la classe Vegetal.
     * @param nom le nom du végétal
     * @param rayon le rayon du végétal
     * @param seuilEauCritique le seuil critique d'eau pour le végétal
     * @param seuilTempCritiqueMin la température minimale critique pour le végétal
     * @param seuilTempCritiqueMax la température maximale critique pour le végétal
     * @param couleur la couleur du végétal
     */
    public Vegetal(String nom, int rayon, int seuilEauCritique, int seuilTempCritiqueMin, int seuilTempCritiqueMax, Color couleur) {
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
        return qteEauConsommee;
    }

    public int getSeuilEauCritique() {
        return seuilEauCritique;
    }

    public void setSeuilEauCritique(int seuilEauCritique) {
        this.seuilEauCritique = seuilEauCritique;
    }

    public int getSeuilTempCritiqueMin() {
        return seuilTempCritiqueMin;
    }

    public void setSeuilTempCritiqueMin(int seuilTempCritiqueMin) {
        this.seuilTempCritiqueMin = seuilTempCritiqueMin;
    }

    public int getSeuilTempCritiqueMax() {
        return seuilTempCritiqueMax;
    }

    public void setSeuilTempCritiqueMax(int seuilTempCritiqueMax) {
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

    public void setEsperanceDeVie(int esperanceDeVie) {
        this.esperanceDeVie = esperanceDeVie;
    }

    public int getAge() {
        return age;
    }

    /**
     * Méthode permettant au végétal de consommer de l'eau dans une zone.
     * @param Z la zone contenant l'eau
     * @throws NoWaterException si la zone ne contient plus d'eau
     */
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

    /**
     * Après avoir consommé de l'eau, le végétal l'utilise pour ses cellules.
     */
    public void utiliserEau() {
        qteEauConsommee -= 1;
    }

    /**
     * Méthode permettant au végétal de vieillir.
     */
    public void vieillir() {
        age += 1;
    }

    /**
     * Méthode permettant au végétal de mourir et de quitter la zone.
     * @param Z la zone actuelle du végétal
     */
    public void mourir(Zone Z) {
        Z.removeVegetal(this.getClass());
        System.out.println("Mort d'un Végétal : " + nom + "\n");
    }
}
