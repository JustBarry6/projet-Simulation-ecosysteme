package ecosystem;

/**
 * L'interface Herbivore définit le comportement des herbivores dans l'écosystème.
 */
public interface Herbivore {
	
	// Méthodes

	/**
	 * Méthode de reproduction des herbivores.
	 * @param partenaire le partenaire herbivore avec lequel se reproduire
	 * @return un nouvel herbivore résultant de la reproduction
	 */
	public Herbivore seReproduire(Herbivore partenaire); 
}
