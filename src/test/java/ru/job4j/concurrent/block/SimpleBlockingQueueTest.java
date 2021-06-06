package ru.job4j.concurrent.block;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void whenOK() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(3);
        Thread first = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    sbq.offer(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }, "Producer"
        );

        Thread second = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " " + sbq.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            }, "Consumer"
        );

        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(sbq.getQueueForTest(), is(0));
    }
}