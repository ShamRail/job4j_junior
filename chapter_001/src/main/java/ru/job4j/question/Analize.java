package ru.job4j.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {

    public static Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> currMap = new HashMap<>();
        current.forEach(u -> currMap.put(u.id, u));
        int deleted = 0;
        int changed = 0;
        for (User u : previous) {
            deleted += (!currMap.containsKey(u.id) ? 1 : 0);
            changed += (currMap.containsKey(u.id) && !u.name.equals(currMap.get(u.id).name) ? 1 : 0);
        }
        int added = current.size() + deleted - previous.size();
        return new Info(added, changed,  deleted);
    }

    public static class User {

        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {

        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }

}
