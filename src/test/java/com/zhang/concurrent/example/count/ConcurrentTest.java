package com.zhang.concurrent.example.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.zhang.concurrent.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiwei.zhang
 * @DATE 2018-06-06 下午 16:19
 */
@SuppressWarnings("Duplicates")
@NotThreadSafe
@Slf4j
public class ConcurrentTest {

    public static int CLIENT_TOTAL = 5000;

    private static int THREAD_TOTAL = 200;

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        CountDownLatch latch = new CountDownLatch(CLIENT_TOTAL);

        for (int i = 0; i < CLIENT_TOTAL; i++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();
        exec.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count++;
    }
}
