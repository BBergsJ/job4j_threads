package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.block.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                while (!Thread.currentThread().isInterrupted() || tasks.getQueueForTest() != 0) {
                    try {
                        tasks.poll().run();
                        System.out.println(Thread.currentThread().getName() + " solved this task.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(new SimpleBlockingQueue<>(5));
        Thread thread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " now working...");
                Thread.sleep(1000);
                System.out.println("Done!");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });

        for (Thread thread1 : threadPool.threads) {
            thread1.start();
        }

        for (int i = 0; i < 100; i++) {
            threadPool.work(thread);
        }
    }
}