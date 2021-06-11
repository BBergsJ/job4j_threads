package ru.job4j.concurrent;

public class Teststrinn {
    public static void main(String[] args) {
        String patternSubj = "Notification {username} to email {email} {username} {email} {username} {email} ";

        String rsl = patternSubj.replaceAll("\\{username}", "NORMUSER").replaceAll("\\{email}", "NORMEMAIL");
        System.out.println(rsl);
    }
}
