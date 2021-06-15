package ru.job4j.concurrent.fjp;

import org.junit.Test;
import ru.job4j.concurrent.buffer.ParallelSearch;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParallelSearcherTest {
    @Test
    public void whenInteger() {
        Integer[] intArray = {1, 2, 3, 4, 5};
        int element = 4;
        int rsl = ParallelSearcher.find(intArray, element);
        assertThat(rsl, is(3));
    }

    @Test
    public void whenString() {
        String[] strArray = {"a", "b", "c", "d", "e"};
        String c = "c";
        int rsl = ParallelSearcher.find(strArray, c);
        assertThat(rsl, is(2));
    }

    @Test
    public void whenHavnt() {
        Integer[] intArray = {1, 2, 3, 4, 5};
        int element = 6;
        int rsl = ParallelSearcher.find(intArray, element);
        assertThat(rsl, is(-1));
    }
}