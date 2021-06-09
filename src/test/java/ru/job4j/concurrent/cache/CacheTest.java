package ru.job4j.concurrent.cache;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class CacheTest {
    @Test
    public void whenAddExist() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        boolean firstTry = cache.add(base);
        boolean secondTry = cache.add(base);
        assertThat(firstTry, is(true));
        assertThat(secondTry, is(false));
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        boolean firstTry = cache.delete(base);
        boolean secondTry = cache.delete(base);
        assertThat(firstTry, is(true));
        assertThat(secondTry, is(false));
    }

    @Test
    public void whenUpdateOk() {

    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateException() {

    }
}