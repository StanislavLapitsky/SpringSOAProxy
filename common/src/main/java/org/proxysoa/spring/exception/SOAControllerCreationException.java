package org.proxysoa.spring.exception;

/**
 * The exception to handle errors during Proxy controllers creation
 * @author stanislav.lapitsky created 4/14/2017.
 */
public class SOAControllerCreationException extends RuntimeException {
    /**
     * Default constructor
     */
    public SOAControllerCreationException() {
    }

    /**
     * Constructor with a message
     * @param message error message
     */
    public SOAControllerCreationException(String message) {
        super(message);
    }
}
