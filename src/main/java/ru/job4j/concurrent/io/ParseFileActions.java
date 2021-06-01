package ru.job4j.concurrent.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFileActions {

    public synchronized String content(Predicate<Character> filter, File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = br.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append(data);
                }
            }
            return output.toString();
        }
    }

    public synchronized void saveContent(String content, File file) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                bw.write(content.charAt(i));
            }
        }
    }
}