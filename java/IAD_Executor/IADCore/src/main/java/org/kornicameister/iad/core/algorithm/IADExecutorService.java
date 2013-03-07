package org.kornicameister.iad.core.algorithm;

import org.apache.log4j.Logger;
import org.kornicameister.iad.core.util.data.IAData;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author kornicameister
 * @since 0.0.1
 */
class IADExecutorService {
    private final static Logger LOGGER = Logger.getLogger(IADExecutorService.class);
    private final ExecutorService pool;

    public IADExecutorService(Integer poolSize) {
        this.pool = Executors.newScheduledThreadPool(poolSize);
    }

    public void serve(Queue<Thread> threadQueue) {
        LOGGER.info("Service started");
        Thread thread;
        List<Future<IAData>> results = new ArrayList<>();
        while ((thread = threadQueue.poll()) != null) {
            LOGGER.info(String.format("Executing %s", thread));
            results.add((Future<IAData>) pool.submit(thread));
            pool.execute(thread);
        }
        pool.shutdown();
        LOGGER.info("Service shutdown");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IADExecutorService{");
        sb.append("pool=").append(pool);
        sb.append('}');
        return sb.toString();
    }
}
