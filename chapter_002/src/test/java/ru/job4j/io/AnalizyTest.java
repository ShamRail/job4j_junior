package ru.job4j.io;

import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void simpleTest() throws IOException {
        String src = "./data/unavailable.csv";
        String tgt = "./data/target.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(src, tgt);
        List<String> expect = List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;");
        List<String> result = Files.readAllLines(Path.of(tgt));
        assertThat(result, is(expect));
    }

}