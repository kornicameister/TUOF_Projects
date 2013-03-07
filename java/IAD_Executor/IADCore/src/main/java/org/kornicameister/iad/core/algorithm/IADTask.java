package org.kornicameister.iad.core.algorithm;

import org.kornicameister.iad.core.algorithm.task.StatisticTask;
import org.kornicameister.iad.core.algorithm.task.StatisticTaskDecorator;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class IADTask extends StatisticTaskDecorator {
    protected final StatisticTask statisticTask;

    public IADTask(StatisticTask statisticTask) {
        this.statisticTask = statisticTask;
    }

    @Override
    public void init() {
        this.statisticTask.init();
    }
}
