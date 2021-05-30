package ru.job4j.concurrent;

public class TestMills {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }
}
