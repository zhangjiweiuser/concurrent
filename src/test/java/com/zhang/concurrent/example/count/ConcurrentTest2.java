package com.zhang.concurrent.example.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import com.zhang.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiwei.zhang
 * @DATE 2018-06-06 下午 16:19
 */
@SuppressWarnings("Duplicates")
@ThreadSafe
@Slf4j
public class ConcurrentTest2 {

    private static int CLIENT_TOTAL = 5000;

    private static int THREAD_TOTAL = 200;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        final CountDownLatch latch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        latch.await();
        exec.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count.incrementAndGet();
    }
}
