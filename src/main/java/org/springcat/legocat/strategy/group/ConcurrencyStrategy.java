package org.springcat.legocat.strategy.group;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadFactoryBuilder;

import org.springcat.legocat.strategy.BaseStrategyI;
import org.springcat.legocat.common.ConcurrentContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description ConcurrencyStrategy
 * @Author springCat
 * @Date 2021-7-29 8:55
 */
public class ConcurrencyStrategy extends GroupStrategyA {

    private long timeoutMillis = 5000;

    private ExecutorService pool = ExecutorBuilder
            .create()
            .setThreadFactory(ThreadFactoryBuilder.create().setNamePrefix("ConcurrencyStrategy").build())
            .setCorePoolSize(1)
            .setMaxPoolSize(Runtime.getRuntime().availableProcessors())
            .build();

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    public void setTimeoutMillis(long timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }

    public ExecutorService getPool() {
        return pool;
    }

    public void setPool(ExecutorService pool) {
        this.pool = pool;
    }

    @Override
    public boolean invoke(ConcurrentContext context) {
        BaseStrategyI[] strategies = getStrategies();
        CountDownLatch countDownLatch = new CountDownLatch(strategies.length);
        for (BaseStrategyI strategy : strategies) {
            pool.submit(() -> {
                try {
                    strategy.execute(context);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }
}
