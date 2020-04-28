package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;

    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        values.clear();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            bf.lines()
                    .filter(l -> !l.startsWith("#") && !l.isBlank())
                    .forEach(s -> {
                        String[] keyValue = s.split("=");
                        values.put(keyValue[0], keyValue[1]);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Config("chapter_002/data/pair_without_comment.properties"));
    }
}
