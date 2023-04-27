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

	public Animal removeAnimal(Color couleur) {
		Animal animalToRemove = null;
		for (Animal animal : animaux) {
			if (animal.getCouleur().equals(couleur)) {
				animalToRemove = animal;
				break;
			}
		}
		if (animalToRemove != null) {
			animaux.remove(animalToRemove);
		}
		return animalToRemove;
	}

	public List<Animal> getAnimaux() {
		return animaux;
	}

	public int getNbAnimaux(Color couleur) {
		int nbAnimaux = 0;
		for (Animal animal : animaux) {
			if (animal.getCouleur().equals(couleur)) {
				nbAnimaux++;
			}
		}
		return nbAnimaux;
	}

	public void setCouleur(Color c) {
		this.c = c;	
	}

	public Color getCouleur() {
		return c;
	}

}
