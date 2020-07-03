package ru.job4j.game.io;

public class ConsoleOutput implements Output {
    @Override
    public void write(String text) {
        System.out.print(text);
    }
}
