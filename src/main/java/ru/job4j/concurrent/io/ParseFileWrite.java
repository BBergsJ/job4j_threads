package ru.job4j.concurrent.io;

import java.io.*;

public class ParseFileWrite {

    public synchronized void saveContent(String content, File file) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                bw.write(content.charAt(i));
            }
        }
    }
}
