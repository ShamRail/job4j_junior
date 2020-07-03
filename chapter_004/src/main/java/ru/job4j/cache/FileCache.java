package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FileCache implements Cache<String, String> {

    private Map<String, SoftReference<String>> map = new HashMap<>();

    private String sourceDir;

    public FileCache(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    @Override
    public String get(String key) throws IOException {
        String content = "";
        if (map.containsKey(key)) {
            content = map.get(key).get();
            if (content == null) {
                content = readFile(key);
                map.put(key, new SoftReference<>(content));
            }
        } else {
            content = readFile(key);
            map.put(key, new SoftReference<>(content));
        }
        return content;
    }

    @Override
    public void put(String key, String value) {
        map.put(key, new SoftReference<>(value));
    }

    private String readFile(String name) throws IOException {
        Path path = Path.of(sourceDir, name);
        File file = path.toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException("Invalid file name!");
        }
        return Files.readAllLines(path).stream()
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
