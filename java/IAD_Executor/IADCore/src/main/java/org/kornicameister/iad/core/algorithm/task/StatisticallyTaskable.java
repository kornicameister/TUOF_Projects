package org.kornicameister.iad.core.algorithm.task;

import org.kornicameister.iad.core.exception.IADTaskExecutionException;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public interface StatisticallyTaskable extends Runnable {
    void execute() throws IADTaskExecutionException;
}
