package ru.job4j.concurrent.block;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    @GuardedBy("this")
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() >= size) {
                this.wait();
            }
            queue.offer(value);
            this.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                this.wait();
            }
            this.notifyAll();
            return queue.poll();
        }
    }

    public int getQueueForTest() {
        synchronized (this) {
            return queue.size();
        }
    }
}