package ru.job4j.concurrent.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFileRead {

    private final File file;

    public ParseFileRead(File file) {
        this.file = file;
    }

    public synchronized String content(Predicate<Character> filter) throws IOException {
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
}