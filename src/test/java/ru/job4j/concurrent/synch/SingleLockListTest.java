package ru.job4j.concurrent.synch;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }

    @Test
    public void get() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        AtomicInteger intRslFirst = new AtomicInteger();
        AtomicInteger intRslSecond = new AtomicInteger();
        Thread first = new Thread(() -> {
                list.add(1);
                intRslFirst.set(list.get(0));
            }
        );
        Thread second = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                list.add(2);
                intRslSecond.set(list.get(1));
            }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(intRslFirst.get(), is(1));
        assertThat(intRslSecond.get(), is(2));
    }

    @Test
    public void linkedToList() {
        SingleLockList<Integer> linked = new SingleLockList<Integer>(new LinkedList<>());
        linked.add(1);
        List<Integer> expected = new LinkedList<>();
        linked.iterator().forEachRemaining(expected::add);
        assertThat(expected, is(List.of(1)));
    }
}