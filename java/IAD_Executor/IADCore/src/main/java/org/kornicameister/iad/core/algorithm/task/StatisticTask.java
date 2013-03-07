package org.kornicameister.iad.core.algorithm.task;

import org.kornicameister.iad.core.algorithm.IADResult;
import org.kornicameister.iad.core.exception.IADTaskExecutionException;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class StatisticTask extends AbstractStatisticTask {
    protected IADResult result;

    @Override
    public void init() {
        this.result = new IADResult();
    }

    @Override
    public void execute() throws IADTaskExecutionException {
    }

    public IADResult getResult() {
        return result;
    }
}
