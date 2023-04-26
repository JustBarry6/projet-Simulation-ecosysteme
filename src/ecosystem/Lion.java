import java.util.ArrayList;

public class Lion extends Mammifere implements Carnivore {
    public Lion() {
        this.proies = new ArrayList<>();
        this.predateurs = new ArrayList<>();
    }

    @Override
    public void seDeplacer() {
        // implémentation de la méthode seDeplacer pour le lion
    }

    @Override
    public void mangerAnimal(Animal animal) {
        // implémentation de la méthode mangerAnimal pour le lion
    }

    @Override
    public void mourir() {
        // implémentation de la méthode mourir pour le lion
    }

    @Override
    public void seReproduire() {
        // implémentation de la méthode seReproduire pour le lion
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
