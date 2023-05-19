package ecosystem;

/**
 * La classe NoWaterException est une exception personnalisée qui est levée lorsqu'il n'y a plus d'eau disponible dans une zone de l'écosystème.
 * Elle hérite de la classe Exception.
 */
public class NoWaterException extends Exception {
    
    /**
     * Constructeur de la classe NoWaterException.
     * @param message le message d'erreur associé à l'exception
     */
    public NoWaterException(String message) {
        super(message);
    }
}
