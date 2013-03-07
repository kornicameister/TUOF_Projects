package org.kornicameister.iad.one.task;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.kornicameister.iad.core.algorithm.IADExecutor;
import org.kornicameister.iad.core.algorithm.IADTask;
import org.kornicameister.iad.core.algorithm.task.StatisticTask;
import org.kornicameister.iad.core.exception.AlreadyRunningTasksException;
import org.kornicameister.iad.core.exception.StatisticThreadCreationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class ArithmeticMeanTaskTest {
    private static final Logger LOGGER = Logger.getLogger(ArithmeticMeanTaskTest.class);
    private IADExecutor iadExecutor;

    @Before
    public void setUp() throws Exception {
        this.iadExecutor = new IADExecutor();
        List<Double> numbers = new ArrayList<>();

        for (int i = 0; i < Math.abs(new Random().nextInt()); i++) {
            numbers.add(new Random().nextDouble());
        }

        ArithmeticMeanTask task = new ArithmeticMeanTask(new IADTask(new StatisticTask()));
        task.init(numbers);

        this.iadExecutor.addTask(task);
    }

    @Test
    public void testRun() {
        try {
            this.iadExecutor.run();
        } catch (AlreadyRunningTasksException e) {
            LOGGER.warn("Failure on AlreadyRunningTasksException", e);
        } catch (StatisticThreadCreationException e) {
            LOGGER.warn("Failure on StatisticThreadCreationException", e);
        }
    }
}
