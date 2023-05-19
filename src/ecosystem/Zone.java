package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Zone {
    private int rayon;
    private int capacite;
    private double niveauEau;
    private double temperature;
    private TypeZone type;
    private List<Animal> animaux;
    private List<Vegetal> vegetaux;
    private Color couleur;

    public Zone(int rayon, int capacite, TypeZone type) {
        this.rayon = rayon;
        this.capacite = capacite;
        this.type = type;
        this.animaux = new ArrayList<>();
        this.vegetaux = new ArrayList<>();

        Random rand = new Random();
        if (type == TypeZone.FORET) {
            couleur = Color.GREEN;

            // Définition des niveaux d'eau et de temperature
            niveauEau = rand.nextInt(1000) + 500; // nivEau entre 500 et 1000mm (precipitations par an)
            temperature = rand.nextInt(45) - 9; // temperature entre -10 et 35° (max-min)*r + 1 + min
        } else if (type == TypeZone.PLAINE) {
            couleur = Color.YELLOW;

            // Définition des niveaux d'eau et de temperature
            niveauEau = rand.nextInt(500) + 250; // nivEau entre 250 et 500mm
            temperature = rand.nextInt(40); // temperature entre 0 et 40°
        } else { 
        	//TypeZone = DESERT
            couleur = Color.ORANGE;

            // Définition des niveaux d'eau et de temperature pour un desert
            niveauEau = rand.nextInt(250); // nivEau entre 0 et 250mm
            temperature = rand.nextInt(70) + 40; // temperature entre 40 et 70°
        }
    }

    public void changementZone() {
        // Attention, nombre d'arbres et température à prendre en compte
        if (type != TypeZone.DESERT) {
            if (niveauEau < 250) {
                type = TypeZone.DESERT;
                couleur = Color.ORANGE;
            } else if (type == TypeZone.FORET && niveauEau >= 250 && niveauEau <= 500) // foret devient-> plaine
            {
                type = TypeZone.PLAINE;
                couleur = Color.YELLOW;
            } else if (type == TypeZone.PLAINE && niveauEau >= 500 && niveauEau <= 1000) // plaine devient-> foret
            {
                type = TypeZone.FORET;
                couleur = Color.GREEN;
            }
        }
    }

    public int getRadius() {
        return rayon;
    }

    public int getCapaciteMax() {
        return capacite;
    }

    public TypeZone getType() {
        return type;
    }

    public List<Animal> getAnimaux() {
        return new ArrayList<>(animaux);
    }

    public void addAnimal(Animal animal) {
        if (animaux.size() < capacite) {
            animaux.add(animal);
        } else {
            System.out.println("La zone est déjà pleine. Impossible d'ajouter un nouvel animal.");
        }
    }

    public Animal removeAnimal(Class<? extends Animal> animalClass) {
        for (int i = 0; i < animaux.size(); i++) {
            Animal animal = animaux.get(i);
            if (animal.getClass().equals(animalClass)) {
                animaux.remove(i);
                return animal;
            }
        }
        return null;
    }

    public void removeVegetal(Class<? extends Vegetal> vegetalTrouve) {
        for (Vegetal vegetal : new ArrayList<>(vegetaux)) {
            if (vegetal.getClass().equals(vegetalTrouve)) {
                vegetaux.remove(vegetal);
                return;
            }
        }
    }

    public int getNbAnimal(Class<? extends Animal> animalClass) {
        int count = 0;
        for (Animal animal : animaux) {
            if (animal.getClass().equals(animalClass)) {
                count++;
            }
        }
        return count;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public double getTemperarure() {
        return temperature;
    }

    public double getNiveauEau() {
        return niveauEau;
    }

    public void setNiveauEau(double niveauActuel) {
        niveauEau = niveauActuel;
    }

    public void addVegetal(Vegetal vegetal) {
        if (vegetaux.size() < capacite) {
            vegetaux.add(vegetal);
        } else {
            System.out.println("La zone est déjà pleine. Impossible d'ajouter un nouvel végétal.");
        }
    }

    public List<Vegetal> getVegetaux() {
        return new ArrayList<>(vegetaux);
    }

	public String getImagePath() {
    if (this.type == TypeZone.FORET) {
        return "src/view/images/foret.png";
    } else if (this.type == TypeZone.PLAINE) {
        return "src/view/images/plaine.png";
    } else if(this.type == TypeZone.DESERT) {
        return "src/view/images/desert2.png";
    }
	return null;
}

}
