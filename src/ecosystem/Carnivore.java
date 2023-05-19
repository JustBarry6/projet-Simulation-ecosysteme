package ecosystem;

public interface Carnivore {
//	public abstract void mangerAnimal(Ecosystem eco, int i, int j); 
	 
	public static final double maxEauConsommable = 100;

	public Carnivore seReproduire(Carnivore partenaire); 
}
