package ru.job4j.concurrent.io;

import java.io.*;

public class ParseFileWrite {

    private final File file;

    public ParseFileWrite(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for (int i = 0; i < content.length(); i += 1) {
                bw.append(content.charAt(i));
                bw.flush();
            }
        }
    }
}
