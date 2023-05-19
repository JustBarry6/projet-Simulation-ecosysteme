package ecosystem;

/**
 * L'interface Carnivore définit le comportement d'un animal carnivore dans l'écosystème.
 */
public interface Carnivore {
    
    // Attribut définissant la quantité maximale d'eau consommable pour un carnivore
    public static final double maxEauConsommable = 100;
    
    /**
     * Méthode permettant à un carnivore de se reproduire avec un partenaire.
     * 
     * @param partenaire Le carnivore partenaire avec lequel se reproduire.
     * @return Un nouvel individu carnivore issu de la reproduction.
     */
    public Carnivore seReproduire(Carnivore partenaire);
}
