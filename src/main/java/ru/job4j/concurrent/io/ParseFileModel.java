package ru.job4j.concurrent.io;

import java.io.*;

public class ParseFileModel { //Не удачное имя класса, забыл поменять.

    public static void main(String[] args) throws IOException {

        File file = new File("test.txt");
        ParseFileRead parseFileRead = new ParseFileRead(file);
        System.out.println(parseFileRead.content(x -> true));

        ParseFileWrite parseFileWrite = new ParseFileWrite((file));
        parseFileWrite.saveContent(" Testing");
        System.out.println(parseFileRead.content(x -> x < 0x80));
    }
}