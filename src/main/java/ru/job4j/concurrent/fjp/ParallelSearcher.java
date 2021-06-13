package ru.job4j.concurrent.fjp;

import ru.job4j.concurrent.buffer.ParallelSearch;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearcher extends RecursiveTask<Integer> {
    private final int[] array;
    private final int from;
    private final int to;
    private final int element;

    public ParallelSearcher(int[] array, int from, int to, int element) {
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
        ParallelSearcher leftPS = new ParallelSearcher(array, from, mid, element);
        ParallelSearcher rightPS = new ParallelSearcher(array, mid + 1, to, element);
        leftPS.fork();
        rightPS.fork();
        int left = leftPS.join();
        int right = rightPS.join();
        return left != -1 ? left : right;
    }

    private int serialSearch() {
        for (int i = from; i < to; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public static int find(int[] array, int index) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearcher(array, 0, array.length - 1, index));
    }

    public static void main(String[] args) {
        ParallelSearch parallelSearch = new ParallelSearch();
        int index = 55;
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }
        System.out.println(find(array, index));
    }
}