package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private String phasesPath;

    private String logPath;

    private List<String> phrases = new ArrayList<>(100);

    private List<String> log = new LinkedList<>();

    private static final String PAUSE = "стоп";

    private static final String CONTINUE = "продолжить";

    private static final String STOP = "закончить";

    public ConsoleChat(String phasesPath, String logPath) {
        this.phasesPath = phasesPath;
        this.logPath = logPath;
    }

    public void start() throws IOException {
        onInit();
        boolean run = true;
        boolean botTalk = true;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.print("you: ");
            String userAnswer = scanner.nextLine();
            run = !STOP.equalsIgnoreCase(userAnswer);
            if (run) {
                if (CONTINUE.equalsIgnoreCase(userAnswer)) {
                    botTalk = true;
                }
                if (PAUSE.equalsIgnoreCase(userAnswer)) {
                    botTalk = false;
                }
                log.add(String.format("you: %s", userAnswer));
                if (botTalk) {
                    String botAnswer = randomAnswer();
                    System.out.println(String.format("bot: %s", botAnswer));
                    log.add(String.format("bot: %s", botAnswer));
                }
            }
        }
        onExit();
    }

    private void onInit() throws IOException {
        phrases = Files.readAllLines(Path.of(phasesPath));
    }

    private void onExit() throws IOException {
        Files.write(Path.of(logPath), log);
    }

    private String randomAnswer() {
        int randomIndex = (int) (Math.random() * phrases.size());
        return phrases.get(randomIndex);
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat chat = new ConsoleChat("./chapter_002/data/botAnswers.txt", "./chapter_002/data/chatLog.txt");
        chat.start();
    }

}
