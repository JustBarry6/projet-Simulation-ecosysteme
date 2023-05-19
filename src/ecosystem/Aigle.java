package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Aigle extends Oiseau implements Carnivore, AnimalVolant {

    public Aigle(int rayon) {
        super(rayon, Color.PINK);
        this.nom = "Aigle";
        this.predateursVegetaux = null;
        this.proiesVegetales = null;
        this.proiesAnimales = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }

    @Override
    public void manger(Ecosystem eco, int i, int j) {
        // Récupérer la liste des animaux dans la zone
        List<Animal> animaux = eco.getZone(i, j).getAnimaux();

        // Rechercher une proie
        Animal proieTrouvee = null;
        for (Animal animal : animaux) {
            if (animal instanceof Carnivore) { // Ignorer les carnivores
                continue;
            }
            if (animal instanceof Herbivore) { // Vérifier si l'animal est un herbivore
                proieTrouvee = animal;
                break;
            }
        }

        // Si une proie a été trouvée, effectuer la prédation
        if (proieTrouvee != null) {
            proieTrouvee.mourir(eco.getZone(i, j)); // Tuer la proie
            ajouterProieAnimale(proieTrouvee); // Ajouter la proie à la liste des proies de l'aigle

            System.out.println("L'aigle a capturé une proie : " + proieTrouvee.getNom());
        }
    }

    private void ajouterProieAnimale(Animal proie) {
        proiesAnimales.add(proie);
    }

    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) throws ZonePleineException{
        moveAnimaux(eco, i, j, POURCENTAGE_DEPLACEMENT_PREDATEUR, Aigle.class);
    }

    @Override
    public Aigle seReproduire(Carnivore partenaire) {
        return new Aigle(30);
    }

	@Override
	public void voler() {
		System.out.println("L'aigle vole dans les airs !\"");
		
	}
}
