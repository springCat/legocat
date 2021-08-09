package org.springcat.legocat.rule.group;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadFactoryBuilder;

import org.springcat.legocat.rule.BaseRuleA;
import org.springcat.legocat.rule.RuleI;
import org.springcat.legocat.common.ConcurrentContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description ConcurrencyRule
 * @Author springCat
 * @Date 2021-7-29 8:55
 */
public class ConcurrencyRule extends BaseRuleA {

    private long timeoutMillis = 5000;

    private ExecutorService pool = ExecutorBuilder
            .create()
            .setThreadFactory(ThreadFactoryBuilder.create().setNamePrefix("ConcurrencyRule").build())
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
        RuleI[] strategies = getStrategies();
        CountDownLatch countDownLatch = new CountDownLatch(strategies.length);
        for (RuleI strategy : strategies) {
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
