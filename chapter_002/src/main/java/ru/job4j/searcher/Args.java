package ru.job4j.searcher;

import java.io.File;

public class Args {

    private static final int DIR_KEY = 0;
    private static final int PATTERN_KEY = 2;
    private static final int SEARCH_TYPE = 4;
    private static final int LOG_PATH_KEY = 5;
    private final String[] args;

    public Args(String[] args) {
        this.args = args;
    }

    public void validate() {
        if (args.length != 7) {
            throw new IllegalArgumentException("Invalid args count! Try again, e.g. java -jar find.jar -d c:/ -n *.txt -m -o log.txt");
        }
        if (!args[DIR_KEY].startsWith("-")
                || !args[PATTERN_KEY].startsWith("-")
                || !args[SEARCH_TYPE].startsWith("-")
                || !args[LOG_PATH_KEY].startsWith("-")) {
            throw new IllegalArgumentException("Args must be started with -");
        }
        if (!args[DIR_KEY].equalsIgnoreCase("-d")
                || !args[PATTERN_KEY].equalsIgnoreCase("-n")
                || !args[LOG_PATH_KEY].equalsIgnoreCase("-o")) {
            throw new IllegalArgumentException("Wrong arg name!");
        }
        File dir = new File(args[DIR_KEY + 1]);
        if (!dir.exists()) {
            throw new IllegalArgumentException("Search directory is not exist!");
        }
    }

    public String directory() {
        return args[DIR_KEY + 1];
    }

    public String pattern() {
        return args[PATTERN_KEY + 1];
    }

    public String searchType() {
        return args[SEARCH_TYPE];
    }

    public String logPath() {
        return args[LOG_PATH_KEY + 1];
    }

}
