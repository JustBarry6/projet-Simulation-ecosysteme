package ecosystem;

/**
 * La classe ZonePleineException est une exception personnalisée qui est levée lorsqu'une zone est pleine dans l'écosystème.
 * Elle hérite de la classe Exception.
 */
public class ZonePleineException extends Exception {
    
    /**
     * Constructeur de la classe ZonePleineException.
     * @param message le message d'erreur associé à l'exception
     */
    public ZonePleineException(String message) {
        super(message);
    }
}
