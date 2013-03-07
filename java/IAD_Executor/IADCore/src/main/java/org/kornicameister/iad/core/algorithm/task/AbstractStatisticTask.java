package org.kornicameister.iad.core.algorithm.task;

import org.apache.log4j.Logger;
import org.kornicameister.iad.core.algorithm.IADExecutionLock;
import org.kornicameister.iad.core.exception.IADTaskExecutionException;

import java.util.concurrent.TimeUnit;

/**
 * @author kornicameister
 * @since 0.0.1
 */
abstract class AbstractStatisticTask implements StatisticallyTaskable {
    private final static Logger LOGGER = Logger.getLogger(AbstractStatisticTask.class);
    protected IADExecutionLock threadLock;

    public void init() {

    }

    @Override
    public void run() {
        LOGGER.info(String.format("Task started execution, threadLock = %s",
                this.threadLock));
        long startTime = System.nanoTime();
        try {
            synchronized (this.threadLock) {
                this.execute();
                this.threadLock.notifyAll();
            }
        } catch (IADTaskExecutionException exception) {
            LOGGER.fatal(String.format("Task finished with errors, threadLock = %s, time = %d ms",
                    this.threadLock,
                    TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime)), exception);
        }
        LOGGER.info(String.format("Task finished without errors, threadLock = %s, time = %d ms",
                this.threadLock,
                TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime)));
    }

    public IADExecutionLock getThreadLock() {
        return threadLock;
    }

    public void setThreadLock(IADExecutionLock threadLock) {
        this.threadLock = threadLock;
    }
}
