package org.mwolff.generator.xml;



/**
 * Exception which works with this framework.
 *
 */
@SuppressWarnings("serial")
public class XMLException extends Exception {

    public XMLException() {
        super();
    }

    public XMLException(final String message) {
        super(message);
    }

    public XMLException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLException(Throwable cause) {
        super(cause);
    }
}
