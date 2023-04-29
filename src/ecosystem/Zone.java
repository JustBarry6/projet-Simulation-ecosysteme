package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Zone {
    private int niveau_eau;
    private int temperature;
    private TypeZone type;
    private List<Animal> animaux;
    private Color c;

    public Zone(int niveau_eau, int temperature, TypeZone type) {
        this.niveau_eau = niveau_eau;
        this.temperature = temperature;
        this.type = type;
        this.animaux = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animaux.add(animal);
    }

    public Animal removeAnimal(Animal animal) {
        Animal animalToRemove = null;
        for (Animal anim : animaux) {
            if (anim.getCouleur().equals(animal.getCouleur())) {
                animalToRemove = anim;
                break;
            }
        }
        if (animalToRemove != null) {
            animaux.remove(animalToRemove);
        }
        return animalToRemove;
    }

    // Cette fonction arenvoie l'adresse d'un tableau qui permet donc la modification des données, mieux vaut éviter ça et faire une copie des données par exemple
    public List<Animal> getAnimaux() {
        return animaux;
    }

    //! Pourquoi cette méthode ?
//    public int getNbAnimal(TypeAnimal type) {
//        int nbAnimaux = 0;
//        for (Animal animal : animaux) {
//            if (animal != null && animal.getType() == type) {
//                nbAnimaux++;
//            }
//        }
//        return nbAnimaux;
//    }

    public void setCouleur(Color c) {
        this.c = c;    
    }

    public Color getCouleur() {
        return c;
    }
}
