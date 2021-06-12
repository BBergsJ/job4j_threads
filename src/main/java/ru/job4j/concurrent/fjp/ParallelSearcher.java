package ru.job4j.concurrent.fjp;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ParallelSearcher extends RecursiveTask<Integer> {
    private final int[] array;
    private final int from;
    private final int to;
    private final int index;

    public ParallelSearcher(int[] array, int from, int to, int index) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.index = index;
    }

    @Override
    protected Integer compute() {

        return -1;
    }

    private int serialSearch(int[] array, int index) {
        return IntStream.range(0, array.length - 1).filter(i -> array[i] == index).findFirst().orElse(-1);
    }

    public static int find(int[] array, int index) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearcher(array, 0, array.length - 1, index));
    }
}
