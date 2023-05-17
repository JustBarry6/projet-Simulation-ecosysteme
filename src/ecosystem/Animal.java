package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import view.Ecosystem;

public abstract class Animal {

	protected String nom;
	protected int age = 1;
	protected final int esperanceDeVie = 40;
	private int rayon;
	private Color c;
	protected double qteEauConsommee;
	protected double maxEauConsommable;

	protected ArrayList<Vegetal> proiesV;
	protected ArrayList<Animal> proiesA;
	protected ArrayList<Vegetal> predateursV;
	protected ArrayList<Animal> predateursA;

	public Animal(int rayon, Color c) {
		this.rayon = rayon;
		this.c = c;
	}

	public String toString() {
		return "Animal : " + nom;
	}

	public String getNom() {
		return nom;
	}

	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

	public Color getCouleur() {
		return c;
	}

	public void setCouleur(Color c) {
		this.c = c;
	}
	public int getEsperanceDeVie() {
		return esperanceDeVie;
	}
	public int getAge() {
		return age;
	}
	public abstract void seDeplacer(Ecosystem ecosystem, int i, int j);

	public void vieillir() {
		age += 1;
	}

	// Dans le Main, l'une des conditions pour mourir est d'atteindre l'esperance de
	// Vie
	public void mourir(Zone Z) {
		System.out.println("L'animal <" + this.nom + "> est mort\n"); 
		Z.removeAnimal(this.getClass());
	}

	public abstract void seReproduire();

	public abstract void manger(Ecosystem eco, int i, int j); 

	public void moveAnimaux(Ecosystem ecosystem, int i, int j, int p, Class<? extends Animal> animalClass) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();
		Random r = new Random();
		int nbAnimaux = ecosystem.getNbAnimal(i, j, animalClass);
		for (int k = 0; k < nbAnimaux; k++) {
			if (r.nextInt(100) < p) {
				int newI = (i + r.nextInt(3) - 1 + nbCasesL) % nbCasesL;
				int newJ = (j + r.nextInt(3) - 1 + nbCasesH) % nbCasesH;
				if (ecosystem.getNbAnimal(newI, newJ, animalClass) < 3) {
					moveAnimal(ecosystem, i, j, newI, newJ, animalClass);
				}
			}
		}
		// les appeller ici ? à reflechir !
//        this.manger(ecosystem, i, j);
//        ecosystem.update(i, j);
	}

	public void moveAnimal(Ecosystem ecosystem, int x, int y, int newX, int newY, Class<? extends Animal> animalClass) {
		Zone currentZone = ecosystem.getZone(x, y);
		Zone newZone = ecosystem.getZone(newX, newY);
		Animal animalToMove = currentZone.removeAnimal(animalClass);
		newZone.addAnimal(animalToMove);
	}

	// Penser a creer une exception si une zone ne contient plus d'eau
	public void boire(Zone Z) {
		double qteDisponible = Z.getNiveauEau();

		if (qteDisponible != 0) {
			if (qteEauConsommee < maxEauConsommable) {
				double qteEauConsommable = maxEauConsommable - qteEauConsommee;
				if (qteEauConsommable < qteDisponible) // Cas où la qte disponible est suffisante pour "rassasier" le
														// vivace
				{
					qteEauConsommee += qteEauConsommable;
					Z.setNiveauEau(qteDisponible - qteEauConsommable);
				} else // Cas où il ne reste pas assez d'eau pour que le vivace soit "rassasié"
				{
					qteEauConsommee += qteDisponible;
					Z.setNiveauEau(0);
				}
			}
		} else
			System.out.println("Plus D'eau Disponible\n");
	}

	// Apres avoir bu de l'eau, l'animal l'utilise pour ses cellules
	public void utiliserEau(){
		qteEauConsommee -= 10 ; 
	}


}