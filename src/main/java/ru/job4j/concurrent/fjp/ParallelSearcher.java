package ru.job4j.concurrent.fjp;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearcher<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T element;

    public ParallelSearcher(T[] array, int from, int to, T element) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if (from == to) {
            return -1;
        }
        if (from - to <= 10) {
            return serialSearch();
        }
        int mid = (from + to) / 2;
        ParallelSearcher<T> leftPS = new ParallelSearcher<>(array, from, mid, element);
        ParallelSearcher<T> rightPS = new ParallelSearcher<>(array, mid + 1, to, element);
        leftPS.fork();
        rightPS.fork();
        int right = rightPS.join();
        int left = leftPS.join();
        return Math.max(left, right);
    }

    private int serialSearch() {
        for (int i = from; i <= to; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

        public static <T> Integer find(T[] array, T index) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (Integer) forkJoinPool.invoke(new ParallelSearcher(array, 0, array.length - 1, index));
    }
}