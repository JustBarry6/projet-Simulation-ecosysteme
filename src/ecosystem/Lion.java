package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Lion extends Mammifere implements Carnivore {
	
    public Lion(int rayon) {
        super(rayon, Color.BLUE);
        this.nom = "Lion";
        this.proiesVegetales = null;
        this.predateursVegetaux = null;
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
            if (animal instanceof Herbivore) {
                proieTrouvee = animal;
                break;
            }
        }

        // Si une proie a été trouvée, effectuer la prédation
        if (proieTrouvee != null) {
            proieTrouvee.mourir(eco.getZone(i, j)); // Tuer la proie
//            eco.getZone(i, j).removeAnimal(proieTrouvee.getClass()); // Retirer la proie de la zone
            ajouterProieAnimale(proieTrouvee); // Ajouter la proie à la liste des proies du lion

            System.out.println("Le lion a capturé une proie : " + proieTrouvee.getNom());
        }
    }
    
    
    private void ajouterProieAnimale(Animal proie) {
        proiesAnimales.add(proie);
    }
	
    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) throws ZonePleineException{
        moveAnimaux(eco, i, j, POURCENTAGE_DEPLACEMENT_PREDATEUR, Lion.class);
    }

    @Override
    public Lion seReproduire(Carnivore partenaire) {
        return new Lion(30);
    }
}
