import java.util.ArrayList;

public class Aigle extends Oiseau implements Carnivore {
    public Aigle() {
    	this.proies = new ArrayList<>();
        this.predateurs = new ArrayList<>();
    }

    @Override
    public void seDeplacer() {
        // implémentation de la méthode seDeplacer pour l'aigle
        voler();
    }

    @Override
    public void mangerAnimal(Animal animal) {
        // implémentation de la méthode mangerAnimal pour l'aigle
    }

    @Override
    public void mourir() {
        // implémentation de la méthode mourir pour l'aigle
    }

    @Override
    public void seReproduire() {
        // implémentation de la méthode seReproduire pour l'aigle
    }

	@Override
	public void manger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boire() {
		// TODO Auto-generated method stub
		
	}
}