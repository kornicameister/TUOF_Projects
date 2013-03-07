package org.kornicameister.iad.core.exception;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class IADTaskExecutionException extends Exception {
    public IADTaskExecutionException(Throwable cause) {
        super("Task execution interrupted", cause);
    }
}
