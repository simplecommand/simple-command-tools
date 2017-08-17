package org.mwolff.toolbox.velocitytools;

/**
 * Exception which works with this framework.
 *
 */
@SuppressWarnings("serial")
public class VelocityException extends Exception {

    public VelocityException() {
        super();
    }

    public VelocityException(final String message) {
        super(message);
    }

    public VelocityException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public VelocityException(final Throwable cause) {
        super(cause);
    }
}
