package com.zhang.concurrent.example.sync;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiwei.zhang
 * @DATE 2018-06-07 下午 16:42
 */
@Slf4j
public class SynchronizedTest {

    // 修饰代码块
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    // 修改方法
    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2 -> {}", i);
        }
    }



    public static void main(String[] args) {
//        SynchronizedTest test = new SynchronizedTest();
//        SynchronizedTest test2 = new SynchronizedTest();
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(test::test2);
//        exec.execute(test2::test2);
        long start = System.currentTimeMillis();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse("2018-01-01 00:02", formatter);
        System.out.println(time);
        LocalDateTime last = time.plusMinutes(10);
        LocalDateTime before = time.minusMinutes(10);
        LocalDateTime time00 = LocalDateTime.parse("2018-01-01 00:00",formatter );
        LocalDateTime time59 = LocalDateTime.parse("2018-01-01 23:59", formatter);
        System.out.println(last);
        System.out.println(before);
        if(last.isAfter(time59)){
            last = time59;
        }
        if(before.isBefore(time00)){
            before = time00;
        }
        System.out.println(before.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println(last.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println(System.currentTimeMillis() - start);
    }
}
