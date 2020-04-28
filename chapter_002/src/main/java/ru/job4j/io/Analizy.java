package ru.job4j.io;

import java.io.*;

public class Analizy {

    private static final String NORMAL_STATUS = "[123].+";

    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            boolean flag = false;
            String start = "";
            String line;
            do {
                line = br.readLine();
                if (line != null) {
                    boolean isNormal = line.matches(NORMAL_STATUS);
                    if (!flag && !isNormal) {
                        start = line.split(" ")[1];
                        flag = true;
                    } else if (flag && isNormal) {
                        String end = line;
                        bw.write(String.format("%s;%s;%n", start, end.split(" ")[1]));
                        flag = false;
                    }
                }
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("unavailable.csv", "target.csv");
    }
}