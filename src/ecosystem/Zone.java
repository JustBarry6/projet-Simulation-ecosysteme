package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Zone {
    private int rayon;
    private int capacite;
    private TypeZone type;
    private List<Animal> animals;
    private Color couleur;

    public Zone(int rayon, int capacite, TypeZone type) {
        this.rayon = rayon;
        this.capacite = capacite;
        this.type = type;
        this.animals = new ArrayList<>();
        
        if (type == TypeZone.FORET) {
        	couleur = Color.GREEN ; 
        }
        else if (type == TypeZone.PLAINE) {
        	couleur = Color.YELLOW ; 
        }
        else 
        	couleur = Color.ORANGE ; 
        	
        	
    }

    public int getRadius() {
        return rayon;
    }

    public int getCapacity() {
        return capacite;
    }

    public TypeZone getType() {
        return type;
    }

    public List<Animal> getAnimaux() {
        return new ArrayList<>(animals);
    }

    public void addAnimal(Animal animal) {
        if (animals.size() < capacite) {
            animals.add(animal);
        } else {
            System.out.println("La zone est déjà pleine. Impossible d'ajouter un nouvel animal.");
        }
    }

    public Animal removeAnimal(Class<? extends Animal> animalClass) {
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (animal.getClass().equals(animalClass)) {
                animals.remove(i);
                return animal;
            }
        }
        return null;
    }

    public int getNbAnimal(Class<? extends Animal> animalClass) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal.getClass().equals(animalClass)) {
                count++;
            }
        }
        return count;
    }

    public Color getCouleur() {
        return this.couleur ; 
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}
