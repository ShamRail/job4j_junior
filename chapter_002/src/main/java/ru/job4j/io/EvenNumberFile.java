package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            Arrays.stream(text.toString()
                    .split(System.lineSeparator()))
                    .mapToInt(Integer::parseInt)
                    .filter(n -> n % 2 == 0)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
