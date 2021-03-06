package ru.job4j.concurrent.exmpl;

public class WgetOld {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int index = 0; index <= 100; index++) {
                            System.out.print("\rLoading : " + index  + "%");
                            Thread.sleep(300);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        thread.start();
    }
}