package com.zhang.concurrent.example.atomicreference;

import java.util.concurrent.atomic.AtomicReference;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiwei.zhang
 * @DATE 2018-06-07 下午 15:08
 */
@Slf4j
public class AtomicReferenceTest {

    private static AtomicReference<Integer> reference = new AtomicReference<>(0);

    public static void main(String[] args) {
        reference.compareAndSet(0, 2); // 2
        reference.compareAndSet(0, 3); // no
        reference.compareAndSet(2, 4); // 4
        reference.compareAndSet(3, 5); // no
        log.info("count:{}", reference.get()); // 4
    }
}
