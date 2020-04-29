package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void simpleTest() throws IOException {
        File src = folder.newFile("unavailable.csv");
        File tgt = folder.newFile("target.csv");
        Files.write(src.toPath(), List.of(
                "200 10:56:01",
                "500 10:57:01",
                "400 10:58:01",
                "200 10:59:01",
                "500 11:01:02",
                "200 11:02:02"
        ));
        Analizy analizy = new Analizy();
        analizy.unavailable(src.getAbsolutePath(), tgt.getAbsolutePath());
        List<String> expect = List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;");
        List<String> result = Files.readAllLines(tgt.toPath());
        assertThat(result, is(expect));
    }

}