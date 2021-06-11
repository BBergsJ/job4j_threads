package ru.job4j.concurrent.exec;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool;
    private final Map<String, String> templates = new HashMap<>();

    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors());
        this.templates.put("subject", "Notification %1$s to email %2$s");
        this.templates.put("body", "Add a new event to %1$s");
    }

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.printf("%s sending a new email! Receiver - %s, Destination - %s%n",
                        Thread.currentThread().getName(), user.getUsername(), user.getEmail());
                send(
                        applyTemplate(templates.get("subject"), user),
                        applyTemplate(templates.get("body"), user),
                        user.getEmail()
                );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println("Done!");
            }
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private static String applyTemplate(String template, User user) {
        return String.format(template, user.getUsername(), user.getEmail());
    }

    public void send(String subject, String body, String email) {
        System.out.println(
                "subject = " + subject + System.lineSeparator() +
                "body = " + body +  System.lineSeparator() +
                "email : " + email + System.lineSeparator()
        );
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        User user1 = new User("User1", "email_____1");
        User user2 = new User("User2", "email_____2");
        User user3 = new User("User3", "email_____3");
        emailNotification.emailTo(user1);
        emailNotification.emailTo(user2);
        emailNotification.emailTo(user3);
        emailNotification.close();
    }
}
