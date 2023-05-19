package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import view.Ecosystem;

/**
 * Classe abstraite représentant un animal.
 */
public abstract class Animal {

    protected String nom;
    protected int age = 1;
    protected int esperanceDeVie = 50; //Valeur initiale 40
    private int rayon;
    private Color couleur;
    protected double qteEauConsommee;
    protected double maxEauConsommable;

    protected ArrayList<Vegetal> proiesVegetales;
    protected ArrayList<Animal> proiesAnimales;
    protected ArrayList<Vegetal> predateursVegetaux;
    protected ArrayList<Animal> predateursAnimaux;
    
    protected static final int POURCENTAGE_DEPLACEMENT_PROIE = 25;
    protected static final int POURCENTAGE_DEPLACEMENT_PREDATEUR = 25;

    /**
     * Constructeur de la classe Animal.
     * 
     * @param rayon   Le rayon de l'animal.
     * @param couleur La couleur de l'animal.
     */
    public Animal(int rayon, Color couleur) {
        this.rayon = rayon;
        this.couleur = couleur;
        this.maxEauConsommable = 20;
    }

    /**
     * Renvoie le nom de l'animal.
     * 
     * @return Le nom de l'animal.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le rayon de l'animal.
     * 
     * @return Le rayon de l'animal.
     */
    public int getRayon() {
        return rayon;
    }

    /**
     * Définit le rayon de l'animal.
     * 
     * @param rayon Le nouveau rayon de l'animal.
     */
    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    /**
     * Renvoie la couleur de l'animal.
     * 
     * @return La couleur de l'animal.
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * Définit la couleur de l'animal.
     * 
     * @param couleur La nouvelle couleur de l'animal.
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    /**
     * Renvoie l'espérance de vie de l'animal.
     * 
     * @return L'espérance de vie de l'animal.
     */
    public int getEsperanceDeVie() {
        return esperanceDeVie;
    }

    /**
     * Renvoie l'âge de l'animal.
     * 
     * @return L'âge de l'animal.
     */
    public int getAge() {
        return age;
    }

    /**
     * Incrémente l'âge de l'animal.
     */
    public void vieillir() {
        age += 1;
    }

    /**
     * Fait mourir l'animal dans la zone donnée.
     * 
     * @param zone La zone où l'animal se trouve.
     */
    public void mourir(Zone zone) {
        System.out.println("\nL'animal <" + this.nom + "> est mort");
        zone.removeAnimal(this.getClass());
    }

    /**
     * Méthode abstraite pour le déplacement de l'animal.
     * 
     * @param ecosystem L'écosystème dans lequel l'animal se déplace.
     * @param i         La position x de la zone dans laquelle se trouve l'animal.
     * @param j         La position y de la zone dans laquelle se trouve l'animal.
     */
    public abstract void seDeplacer(Ecosystem ecosystem, int i, int j) throws ZonePleineException;

    /**
     * Méthode abstraite pour la reproduction de l'animal.
     */
    // public abstract void seReproduire();

    /**
     * Méthode abstraite pour l'alimentation de l'animal.
     * 
     * @param eco L'écosystème dans lequel l'animal se nourrit.
     * @param i   La position x de la zone dans laquelle se trouve l'animal.
     * @param j   La position y de la zone dans laquelle se trouve l'animal.
     */
    public abstract void manger(Ecosystem eco, int i, int j);

    /**
     * Déplace les animaux de la zone donnée vers une nouvelle zone aléatoire.
     * 
     * @param ecosystem    L'écosystème dans lequel se trouvent les animaux.
     * @param i            La position x de la zone d'origine.
     * @param j            La position y de la zone d'origine.
     * @param p            Le pourcentage de chance de déplacement.
     * @param animalClass  La classe des animaux à déplacer.
     */
    public void moveAnimaux(Ecosystem ecosystem, int i, int j, int p, Class<? extends Animal> animalClass) throws ZonePleineException{
        int nbCasesL = ecosystem.getNbCasesL();
        int nbCasesH = ecosystem.getNbCasesH();
        Random random = new Random();
        int nbAnimaux = ecosystem.getNbAnimal(i, j, animalClass);
        for (int k = 0; k < nbAnimaux; k++) {
            if (random.nextInt(100) < p) {
                int newI = (i + random.nextInt(3) - 1 + nbCasesL) % nbCasesL;
                int newJ = (j + random.nextInt(3) - 1 + nbCasesH) % nbCasesH;
                if (ecosystem.getNbAnimal(newI, newJ, animalClass) < 3) {
                    moveAnimal(ecosystem, i, j, newI, newJ, animalClass);
                }
            }
        }
    }

    /**
     * Déplace un animal d'une zone d'origine vers une nouvelle zone.
     * Si la zone est un desert, alors l'esperance de vie de l'animal diminue
     * 
     * @param ecosystem    L'écosystème dans lequel se trouvent les zones.
     * @param x            La position x de la zone d'origine.
     * @param y            La position y de la zone d'origine.
     * @param newX         La position x de la nouvelle zone.
     * @param newY         La position y de la nouvelle zone.
     * @param animalClass  La classe de l'animal à déplacer.
     */
    public void moveAnimal(Ecosystem ecosystem, int x, int y, int newX, int newY,
            Class<? extends Animal> animalClass) throws ZonePleineException{
        Zone currentZone = ecosystem.getZone(x, y);
        Zone newZone = ecosystem.getZone(newX, newY);
        Animal animalToMove = currentZone.removeAnimal(animalClass);
        newZone.addAnimal(animalToMove);
        
        if (newZone.getType() == TypeZone.DESERT)
//        	animalToMove.mourir(newZone);
        	esperanceDeVie -= 10 ;
    }

    /**
     * Permet à l'animal de boire de l'eau dans la zone donnée.
     * 
     * @param zone La zone dans laquelle l'animal boit.
     */
    public void boire(Zone zone) throws NoWaterException {
        double qteDisponible = zone.getNiveauEau();

        if (qteDisponible != 0) {
            if (qteEauConsommee < maxEauConsommable) {
                double qteEauConsommable = maxEauConsommable - qteEauConsommee;
                if (qteEauConsommable < qteDisponible) {
                    qteEauConsommee += qteEauConsommable;
                    zone.setNiveauEau(qteDisponible - qteEauConsommable);
                } else {
                    qteEauConsommee += qteDisponible;
                    zone.setNiveauEau(0);
                }
            }
        } else {
            throw new NoWaterException("Plus d'eau disponible dans la zone !");
        }
    }


    /**
     * Utilise l'eau consommée par l'animal.
     */
    public void utiliserEau() {
        qteEauConsommee -= 1;
    }
}
