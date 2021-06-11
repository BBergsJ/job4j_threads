package ru.job4j.concurrent.exec;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool;

    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors());
    }

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute " + Thread.currentThread().getName());
            }
        });
    }

    public void close(){
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Execute " + Thread.currentThread().getName());
    }

    public void send(String subject, String body, String email) {

    }
}
