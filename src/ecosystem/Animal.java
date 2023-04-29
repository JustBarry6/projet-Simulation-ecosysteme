package ecosystem;


import java.awt.Color;

public abstract class Animal{

    private int rayon;
    private Color c;
	private TypeAnimal type;

    public Animal(){}
    
    public Animal(int rayon, Color c, TypeAnimal type){
	this.rayon=rayon;
	this.c=c;
	this.type = type;
    }

    public int getRayon(){ return rayon;}
    public void setRayon(int rayon){this.rayon=rayon;}
    public Color getCouleur(){ return c;}
    public void setCouleur(Color c){this.c=c;}

    public TypeAnimal getType() {
		return type;
	}
	


}
