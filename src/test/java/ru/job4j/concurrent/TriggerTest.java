package ru.job4j.concurrent;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.concurrent.exmpl.Trigger;

public class TriggerTest {

    @Test
    public void test() {
        Assert.assertEquals(1, new Trigger().someLogic());
    }

}