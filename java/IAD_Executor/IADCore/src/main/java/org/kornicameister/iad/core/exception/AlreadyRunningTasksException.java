package org.kornicameister.iad.core.exception;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class AlreadyRunningTasksException extends Exception {
    private static final String EXC_MSG = "There is already %d pending executions...";

    public AlreadyRunningTasksException(Integer taskCount) {
        super(String.format(EXC_MSG, taskCount));
    }
}
