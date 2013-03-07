package org.kornicameister.iad.core.exception;

import java.io.IOException;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class IADUnableToLoadPropertiesException extends RuntimeException {
    public IADUnableToLoadPropertiesException(IOException e) {
        super("Unable to read properties file", e);
    }
}
