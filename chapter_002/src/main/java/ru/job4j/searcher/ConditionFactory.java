package ru.job4j.searcher;

import java.nio.file.Path;
import java.util.function.Predicate;

public class ConditionFactory {

    public static Predicate<Path> newCondition(Args args) {
        String type = args.searchType();
        Predicate<Path> result = p -> true;
        switch (type.toLowerCase()) {
            case "-f":
                result = p -> p.toFile().getName().equals(args.pattern());
                break;
            case "-m":
                String s = preparePattern(args.pattern());
                result = p -> p.toFile().getName().matches(s);
                break;
            case "-r":
                result = p -> p.toFile().getName().matches(args.pattern());
                break;
            default:
                break;
        }
        return result;
    }

    private static String preparePattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '*') {
                sb.append(".*");
            } else if (c == '.') {
                sb.append("\\.");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
