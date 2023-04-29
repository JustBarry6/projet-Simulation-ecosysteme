package ecosystem;


import java.awt.Color;

public abstract class Animal{

    private int rayon;
    private Color c;
	
    public Animal(int rayon, Color c){
	this.rayon=rayon;
	this.c=c;
    }

    public int getRayon(){ return rayon;}
    public void setRayon(int rayon){this.rayon=rayon;}
    public Color getCouleur(){ return c;}
    public void setCouleur(Color c){this.c=c;}

    public abstract void seDeplacer(); 
  	public abstract void boire() ;  
  	public abstract void mourir() ;  
  	public abstract void seReproduire() ;  
}
