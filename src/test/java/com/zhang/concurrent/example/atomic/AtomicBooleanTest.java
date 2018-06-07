package com.zhang.concurrent.example.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiwei.zhang
 * @DATE 2018-06-07 下午 15:24
 */
@Slf4j
public class AtomicBooleanTest {

    private static AtomicBoolean isHappen = new AtomicBoolean(false);

    public static int CLIENT_TOTAL = 5000;

    private static int THREAD_TOTAL = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        CountDownLatch latch = new CountDownLatch(CLIENT_TOTAL);

        for (int i = 0; i < CLIENT_TOTAL; i++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();
        exec.shutdown();
        log.info("isHappen:{}", isHappen.get());
    }

    private static void test() {

        if (isHappen.compareAndSet(false, true)) {
            log.info("isHappen:{}", isHappen.get());
        }
    }
}
