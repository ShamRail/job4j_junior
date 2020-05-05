package ru.job4j.searcher;

import ru.job4j.io.SearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Searcher {

    public static void main(String[] args) throws IOException {
        Args argsWrapper = new Args(args);
        argsWrapper.validate();
        Predicate<Path> condition = ConditionFactory.newCondition(argsWrapper);
        Searcher searcher = new Searcher();
        List<Path> files = searcher.search(argsWrapper, condition);
        searcher.writeLog(argsWrapper, files);
    }

    private void writeLog(Args args, List<Path> files) throws IOException {
        Files.write(
                Path.of(args.logPath()),
                files.stream().map(Path::toString).collect(Collectors.toList())
        );
    }

    private List<Path> search(Args args, Predicate<Path> condition) throws IOException {
        SearchFiles sf = new SearchFiles(condition);
        Files.walkFileTree(Path.of(args.directory()), sf);
        return sf.getPaths();
    }
}
