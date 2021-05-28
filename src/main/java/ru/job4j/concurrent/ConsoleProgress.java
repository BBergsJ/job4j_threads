package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(6000); /* симулируем выполнение параллельной задачи в течении 6 секунд. */
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("First process interrupted!!!");
        }
        progress.interrupt();
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        int count = 0;
        String[] process = {"-", "\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process[count++]);
            if (count == 4) {
                count = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Second process interrupted!!!");
            }
        }
    }
}