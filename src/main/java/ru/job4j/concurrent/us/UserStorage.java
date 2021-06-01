package ru.job4j.concurrent.us;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {

    private final List<User> users = new ArrayList<>();

    public boolean add(User user) {
        for (User u : users) {
            if (user.getId() != u.getId()) {
                users.add(user);
                return true;
            }
        }
        return false;
    }

    public boolean update(User user) {
        for (User u : users) {
            if (user.getId() == u.getId()) {
                users.set(u.getId() - 1, user);
                return true;
            }
        }
        return false;
    }

    public boolean delete(User user) {
        for (User u : users) {
            if (user.getId() == u.getId()) {
                users.remove(u);
                return true;
            }
        }
        return false;
    }

    public User findId(int id) {
        for (User u : users) {
            if (id == u.getId()) {
                return u;
            }
        }
        return null;
    }

    public boolean transfer(int fromId, int toId, int amount) {
        User from = findId(fromId);
        User to = findId(toId);
        if (from != null && to != null && from.getAmount() >= amount) {

        }
        return false;
    }

    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));

        storage.transfer(1, 2, 50);
    }
}
