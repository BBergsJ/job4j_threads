package ru.job4j.concurrent.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int ref;
        int refInc;
        do {
            ref = count.get();
            refInc = ref++;
        } while (count.compareAndSet(ref, refInc));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) {
        CASCount casCount = new CASCount();
        casCount.count.set(10);
        casCount.increment();
        System.out.println(casCount.get());
    }
}