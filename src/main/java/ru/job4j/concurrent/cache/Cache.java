package ru.job4j.concurrent.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base stored = memory.get(model.getId());
        if (stored.getVersion() != model.getVersion()) {
            throw new OptimisticException("Versions are not equal");
        }
        return true;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public static void main(String[] args) {
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);

        Cache cache = new Cache();
        BiFunction biFunction;
    }
}