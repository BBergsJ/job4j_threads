package ru.job4j.concurrent.block;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

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

    @Test
    public void whenTestFromExercise() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Producer"
        );

        Thread consumer = new Thread(
                () -> {
                    while (queue.getQueueForTest() != 0 || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Consumer"
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }
}