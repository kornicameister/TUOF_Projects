package org.kornicameister.iad.one.task;

import org.apache.log4j.Logger;
import org.kornicameister.iad.core.algorithm.IADTask;
import org.kornicameister.iad.core.exception.IADTaskExecutionException;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class ArithmeticMeanTask extends IADTask {
    private final static Logger LOGGER = Logger.getLogger(ArithmeticMeanTask.class);
    private List<Double> numbers;

    public ArithmeticMeanTask(IADTask statisticTask) {
        super(statisticTask);
    }

    @Override
    public void execute() throws IADTaskExecutionException {
        try {
            long startTime = System.nanoTime();

            Integer numbers = this.numbers.size();
            Double result = 0.0;

            LOGGER.info(String.format("Started Arithmetic mean\nElements = %d",
                    numbers));

            for (Double aDouble : this.numbers) {
                result += aDouble;
            }
            result = result / numbers;
            long endTime = System.nanoTime() - startTime;

            LOGGER.info(String.format("Finished Arithmetic mean\nResult = %f\nElements=%d\ntime = %d ms",
                    result,
                    numbers,
                    TimeUnit.NANOSECONDS.toSeconds(endTime))
            );
        } catch (Exception exception) {
            throw new IADTaskExecutionException(exception);
        }
    }

    public void init(List<Double> numbers) {
        this.numbers = numbers;
        this.statisticTask.init();
    }
}
