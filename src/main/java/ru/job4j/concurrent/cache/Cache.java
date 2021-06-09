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
        return memory.computeIfPresent(model.getId(), (id, base) -> {
            if (base.getVersion() != model.getVersion()) {
                throw new OptimisticException("Version are not equal");
            }
            base = new Base(id, base.getVersion() + 1);
            return base;
        }) != null;
    }

    public boolean delete(Base model) {
        return memory.remove(model.getId(), model);
    }

    public static void main(String[] args) {
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        Base base3 = new Base(3, 1);

        Cache cache = new Cache();
        cache.add(base1);
        cache.add(base2);
        cache.add(base3);

        for (Map.Entry<Integer, Base> integerBaseEntry : cache.memory.entrySet()) {
            System.out.println(integerBaseEntry);
        }

        System.out.println(System.lineSeparator());

        cache.update(cache.memory.get(base1.getId()));


        for (Map.Entry<Integer, Base> integerBaseEntry : cache.memory.entrySet()) {
            System.out.println(integerBaseEntry);
        }

        System.out.println(System.lineSeparator());

        cache.update(cache.memory.get(base1.getId()));

        for (Map.Entry<Integer, Base> integerBaseEntry : cache.memory.entrySet()) {
            System.out.println(integerBaseEntry);
        }
    }
}