package com.zhang.concurrent.example.atomicreference;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiwei.zhang
 * @DATE 2018-06-07 下午 15:12
 */
@Slf4j
public class AtomicReferenceFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<AtomicReferenceFieldUpdaterTest> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterTest.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicReferenceFieldUpdaterTest test = new AtomicReferenceFieldUpdaterTest();
        if (updater.compareAndSet(test, 100, 120)) {
            log.info("update success 1,{}", test.getCount());
        }
        if (updater.compareAndSet(test, 100, 120)) {
            log.info("update success 2,{}", test.getCount());
        } else {
            log.info("update failed {}", test.getCount());
        }
    }
}
