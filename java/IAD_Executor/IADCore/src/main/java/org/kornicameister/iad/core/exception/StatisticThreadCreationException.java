package org.kornicameister.iad.core.exception;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class StatisticThreadCreationException extends Exception {
    private static final String FORMAT = "Thread %s was not placed in the queue";

    public StatisticThreadCreationException(Thread threadHolder) {
        super(String.format(FORMAT, threadHolder));
    }
}
