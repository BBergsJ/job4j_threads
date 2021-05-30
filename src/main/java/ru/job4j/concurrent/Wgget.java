package ru.job4j.concurrent;

public class Wgget implements Runnable {
    private final String url;
    private final int speed;

    public Wgget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        /* Скачать файл*/
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wwget = new Thread(new Wgget(url, speed));
        wwget.start();
        wwget.join();
    }
}