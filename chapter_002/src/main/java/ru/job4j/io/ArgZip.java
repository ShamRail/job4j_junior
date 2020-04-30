package ru.job4j.io;

import java.io.File;
import java.util.Arrays;

public class ArgZip {

    private final String[] args;

    private final static int KEY_COUNT = 3;

    private final static int ARG_COUNT = 3;

    private final static int DIR_KEY = 0;

    private final static int EXT_KEY = 2;

    private final static int OUT_KEY = 4;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != (KEY_COUNT + ARG_COUNT)) {
            throw new IllegalArgumentException("Invalid count of args. java -jar pack.jar -d c:\\project\\job4j\\ -e *.java -o project.zip ");
        }
        long keyCount = Arrays.stream(args).filter(s -> s.startsWith("-")).count();
        if (keyCount != KEY_COUNT) {
            throw new IllegalArgumentException("Missing some keys! Key must start with -");
        }
        long valueCount = Arrays.stream(args).filter(s -> !s.startsWith("-")).count();
        if (valueCount != ARG_COUNT) {
            throw new IllegalArgumentException("Missing some args! Values count must be equal to keys count!");
        }
        if (!args[DIR_KEY].equals("-d") || !args[EXT_KEY].equals("-e") || !args[OUT_KEY].equals("-o")) {
            throw new IllegalArgumentException("Invalid key name! -d is directory, -e is exclude extension, -o output filename");
        }
        if (!args[EXT_KEY + 1].startsWith(".")) {
            throw new IllegalArgumentException("Extension must be start with dot , e.g. .java");
        }
        File file = new File(args[DIR_KEY + 1]);
        if (!file.exists()) {
            throw new IllegalArgumentException("Invalid directory path!");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Path is not directory!");
        }
        return true;
    }

    public String directory() {
        return args[DIR_KEY + 1];
    }

    public String exclude() {
        return args[EXT_KEY + 1];
    }

    public String output() {
        return args[OUT_KEY + 1];
    }
}
