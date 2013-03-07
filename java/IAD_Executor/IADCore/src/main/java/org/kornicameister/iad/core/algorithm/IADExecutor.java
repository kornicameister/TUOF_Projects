package org.kornicameister.iad.core.algorithm;

import org.apache.log4j.Logger;
import org.kornicameister.iad.core.algorithm.task.StatisticTask;
import org.kornicameister.iad.core.exception.AlreadyRunningTasksException;
import org.kornicameister.iad.core.exception.StatisticThreadCreationException;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Base class for any sort of algorithm that may be
 * executed in the end-defined context. For instance,
 * due to that this class persists the set of
 * all {@link org.kornicameister.iad.core.algorithm.task.StatisticTask} objects and implements
 * Runnable interface it is able to execute all of them
 * simultaneously and return results as soon as the last
 * task has been executed.
 *
 * @author kornicameister
 * @since 0.0.1
 */
public class IADExecutor {
    private final static Logger LOGGER = Logger.getLogger(IADExecutor.class);

    private final Set<IADTask> statisticTasks;
    private final IADExecutorService executorService;
    private Boolean isWorking;

    public IADExecutor() {
        this(new HashSet<IADTask>());
    }

    public IADExecutor(Collection<IADTask> statisticTasks) {
        this(statisticTasks, false);
    }

    private IADExecutor(Collection<IADTask> statisticTasks, boolean isWorking) {
        this.statisticTasks = new HashSet<>(statisticTasks);
        this.isWorking = isWorking;
        this.executorService = new IADExecutorService(10);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("IADExecutor \ntasks = %d\nstatus = %s\nservice = %s",
                    this.statisticTasks.size(),
                    this.isWorking,
                    this.executorService));
        }
    }

    /**
     * Main runner that executes tasks simultaneously
     *
     * @throws AlreadyRunningTasksException
     * @throws StatisticThreadCreationException
     *
     */
    public void run() throws AlreadyRunningTasksException,
            StatisticThreadCreationException {
        if (this.isWorking) {
            throw new AlreadyRunningTasksException(this.statisticTasks.size());
        }
        LOGGER.info(String.format("Execution started, tasks in queue = %d", this.statisticTasks.size()));
        long startTime = System.nanoTime();
        Queue<Thread> threadQueue = new ArrayDeque<>();
        Thread threadHolder;
        IADExecutionLock iadExecutionLock = new IADExecutionLock(new Random().nextInt());
        {
            // 1. create new threads
            for (StatisticTask task : this.statisticTasks) {
                task.setThreadLock(iadExecutionLock.increment());
                if (threadQueue.offer((threadHolder = new Thread(task)))) {
                    LOGGER.info(String.format("Created thread = [ %d / %s ]",
                            threadHolder.getId(),
                            threadHolder.getName()));
                } else {
                    try {
                        throw new StatisticThreadCreationException(threadHolder);
                    } catch (StatisticThreadCreationException exception) {
                        long endTime = System.nanoTime() - startTime;
                        this.isWorking = false;
                        LOGGER.fatal(String.format("Execution finished with error, took %d ms", TimeUnit.NANOSECONDS.toSeconds(endTime)), exception);
                        throw exception;
                    }
                }
            }

            // 2. execute threads and monitor them
            this.executorService.serve(threadQueue);
        }
        long endTime = System.nanoTime() - startTime;
        this.isWorking = false;
        LOGGER.info(String.format("Execution finished without errors, took %d ms", TimeUnit.NANOSECONDS.toSeconds(endTime)));
    }

    public boolean isWorking() {
        return isWorking;
    }

    /**
     * Delegated from {@link IADExecutor#statisticTasks} add method.
     *
     * @param statisticTask task to be added
     * @return true, if task was successfully added
     */
    public boolean addTask(IADTask statisticTask) {
        return statisticTasks.add(statisticTask);
    }

    /**
     * Delegated from {@link IADExecutor#statisticTasks} remove method.
     *
     * @param statisticTask task to be removed
     * @return true, if task was successfully removed
     */
    public boolean removeTask(IADTask statisticTask) {
        return statisticTasks.remove(statisticTask);
    }
}
