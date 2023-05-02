package ecosystem;

import java.awt.Color;
import java.util.ArrayList;

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
    
	public String toString()
	{
		return nom ; 
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
