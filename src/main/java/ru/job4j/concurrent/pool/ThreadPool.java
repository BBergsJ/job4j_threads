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
                    System.out.println(Thread.currentThread().getName() + " " + );
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
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        SimpleBlockingQueue<Runnable> sbq = new SimpleBlockingQueue<>(5);
        ThreadPool threadPool = new ThreadPool(sbq);
        threadPool.work(thread);
        threadPool.threads.forEach(x -> System.out.println(x.getId()));
        Runnable task1 = new Thread();
        threadPool.work(task1);
    }
}