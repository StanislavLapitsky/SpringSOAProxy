package org.proxysoa.spring.exception;

/**
 * The exception to handle errors during Proxy controllers invocation
 *
 * @author stanislav.lapitsky created 4/14/2017.
 */
public class SOAControllerInvocationException extends RuntimeException {
    /**
     * Default constructor
     */
    public SOAControllerInvocationException() {
    }

    /**
     * Constructor with a message
     *
     * @param message error message
     */
    public SOAControllerInvocationException(String message) {
        super(message);
    }

    /**
     * Constructor with a message
     *
     * @param message error message
     * @param ex      parent exception to be rethrown
     */
    public SOAControllerInvocationException(String message, Exception ex) {
        super(message, ex);
    }
}
