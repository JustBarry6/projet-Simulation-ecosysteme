package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import view.Ecosystem;

public abstract class Animal{

	protected String nom ; 
    private int rayon;
    private Color c;
    

    protected ArrayList <Vegetal> proiesV;
    protected ArrayList <Animal> proiesA;
    protected ArrayList <Vegetal> predateursV;
    protected ArrayList <Animal> predateursA;
    
	
    public Animal(int rayon, Color c){
	this.rayon=rayon;
	this.c=c;
    }
    
	public String toString(){return "Animal : " + nom ;}
	public String getNom() {return nom;}
    public int getRayon(){ return rayon;}
    public void setRayon(int rayon){this.rayon=rayon;}
    public Color getCouleur(){ return c;}
    public void setCouleur(Color c){this.c=c;}

    public abstract void seDeplacer(Ecosystem ecosystem, int i, int j); 
  	public abstract void boire() ;  
  	public abstract void mourir() ;  
  	public abstract void seReproduire() ;
  	public abstract void manger(Ecosystem eco, int i, int j); // supprimer dans tous les autres interface
  	
  	public void moveAnimaux(Ecosystem ecosystem, int i, int j, int p, Class<? extends Animal> animalClass) {
        int nbCasesL = ecosystem.getNbCasesL();
        int nbCasesH = ecosystem.getNbCasesH();
        Random r = new Random();
        int nbAnimaux = ecosystem.getNbAnimal(i, j, animalClass);
        for (int k = 0; k < nbAnimaux; k++) {
            if (r.nextInt(100) < p) {
                int newI = (i + r.nextInt(3) - 1 + nbCasesL) % nbCasesL;
                int newJ = (j + r.nextInt(3) - 1 + nbCasesH) % nbCasesH;
                if (ecosystem.getNbAnimal(newI, newJ, animalClass) == 0) {
                    moveAnimal(ecosystem, i, j, newI, newJ, animalClass);
                }
            }
        }
    }

    public void moveAnimal(Ecosystem ecosystem, int x, int y, int newX, int newY, Class<? extends Animal> animalClass) {
        Zone currentZone = ecosystem.getZone(x, y);
        Zone newZone = ecosystem.getZone(newX, newY);
        Animal animalToMove = currentZone.removeAnimal(animalClass);
        newZone.addAnimal(animalToMove);
    }
    
    
  
    

}