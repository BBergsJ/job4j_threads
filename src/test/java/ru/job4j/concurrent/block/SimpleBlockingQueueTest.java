package ru.job4j.concurrent.block;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void whenOK() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(3);
        Thread first = new Thread(() -> {
                sbq.offer(1);
                sbq.offer(2);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
                sbq.offer(5);
            }, "First"
        );

        Thread second = new Thread(() -> {
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
                sbq.offer(3);
                sbq.offer(4);

            System.out.println(sbq.poll());
            System.out.println(sbq.poll());
            System.out.println(sbq.poll());
            System.out.println(sbq.poll());
            System.out.println(sbq.poll());

            System.out.println(sbq.poll());
            }, "Second"
        );

        first.start();
        second.start();
        first.join();
        second.join();
    }
}