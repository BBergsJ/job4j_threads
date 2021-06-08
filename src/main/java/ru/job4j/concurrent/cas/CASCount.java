package ru.job4j.concurrent.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        Integer temp;
        do {
            temp = count.get();
        } while (!count.compareAndSet(temp, temp + 1));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CASCount casCount = new CASCount();
        casCount.count.set(0);

         Thread thread1 = new Thread(() -> {
             while (!Thread.currentThread().isInterrupted()) {
                 casCount.increment();
             }
         });

         Thread thread2 = new Thread(() -> {
             while (!Thread.currentThread().isInterrupted()) {
                 casCount.increment();
             }
         });

         thread1.start();
         thread2.start();
         Thread.sleep(1000);
         thread1.interrupt();
         thread2.interrupt();
         thread1.join();
         thread2.join();

        System.out.println(casCount.get());
    }
}