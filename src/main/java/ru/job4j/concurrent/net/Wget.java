package ru.job4j.concurrent.net;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.Date;
import java.nio.file.Paths;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(getFileNameFromURL(url))) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                long startTime = System.currentTimeMillis();
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long finishTime = System.currentTimeMillis();
                if ((finishTime - startTime) < speed) {
                    Thread.sleep(speed - (finishTime - startTime));
                }
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            throw new IllegalArgumentException("URL or speed is not entered.");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

    public static String getFileNameFromURL(String url) throws URISyntaxException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_");
        Date date = new Date();
        return dateFormat.format(date) + Paths.get(new URI(url).getPath()).getFileName().toString();
    }
}