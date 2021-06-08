package ru.job4j.concurrent.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        Integer tmp;
        do {
            tmp = count.get();
        } while (!count.compareAndSet(tmp, tmp + 1));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CASCount casCount = new CASCount();
        casCount.count.set(0);

         Thread thread1 = new Thread(() -> {
             for (int i = 0; i < 10; i++) {
                 casCount.increment();
             }
         });

         Thread thread2 = new Thread(() -> {
             for (int i = 0; i < 10; i++) {
                 casCount.increment();
             }
         });

         thread1.start();
         thread2.start();
         thread1.join();
         thread2.join();

        System.out.println(casCount.get() + " must be 20");
    }
}