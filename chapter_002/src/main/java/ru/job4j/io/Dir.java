package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\79962\\Documents\\job4j_junior");
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid arguments. Try again, e.g. java -jar DIR_NAME .txt");
        }
        if (!file.exists()) {
            throw new IllegalStateException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalStateException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("name:%s size:%d KB",
                    subfile.getName(), (long) Math.ceil(subfile.length() / 1024.0)));
        }
    }
}