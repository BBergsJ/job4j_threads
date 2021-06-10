package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.block.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(SimpleBlockingQueue<Runnable> tasks) {
        this. =
    }

    public void work(Runnable job) {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add((Thread) job);
        }
    }

    public void shutdown() {
        Thread.currentThread().interrupt();
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(thread);
        threadPool.threads.forEach(System.out::println);
        Runnable task1 = new Thread();
        threadPool.work(task1);
    }
}