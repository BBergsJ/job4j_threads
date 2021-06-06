package ru.job4j.concurrent.exmpl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.io.FilenameUtils;

public class TestMills {
    public static void main(String[] args) throws InterruptedException, MalformedURLException, URISyntaxException {
//        long start = System.currentTimeMillis();
//        Thread.sleep(1000);
//        long finish = System.currentTimeMillis();
//        System.out.println(finish - start);
//
//        URL url = new URL("https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml");
//        System.out.println(FilenameUtils.getName(url.getPath()));
//
//        String testUrl = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
//        String filename = Paths.get(new URI(testUrl).getPath()).getFileName().toString();
//        System.out.println(filename);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        for (int i = 0; i < queue.size(); i++) {
            System.out.println(queue.size());
            queue.poll();
        }
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
    }
}
