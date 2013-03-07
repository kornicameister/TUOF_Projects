package org.kornicameister.iad.core.exception;

import java.io.FileNotFoundException;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class IADMissingPropertiesException extends RuntimeException {
    public IADMissingPropertiesException(String pathName, FileNotFoundException exception) {
        super(String.format("No properties file under %s", pathName), exception);
    }
}
