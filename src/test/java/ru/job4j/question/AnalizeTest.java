package ru.job4j.question;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void whenNotChanged() {
        Analize.User u1 = new Analize.User(1, "A");
        Analize.User u2 = new Analize.User(2, "B");
        Analize.User u3 = new Analize.User(3, "C");
        List<Analize.User> previous = List.of(u1, u2, u3);
        List<Analize.User> current = List.of(u1, u2, u3);
        Analize.Info result = Analize.diff(previous, current);
        assertThat(result.added, is(0));
        assertThat(result.changed, is(0));
        assertThat(result.deleted, is(0));
    }

    @Test
    public void whenOneChanged() {
        Analize.User u1 = new Analize.User(1, "A");
        Analize.User u2 = new Analize.User(2, "B");
        Analize.User u3 = new Analize.User(3, "C");
        List<Analize.User> previous = List.of(u1, u2, u3);
        List<Analize.User> current = List.of(u1, new Analize.User(2, "BB"), u3);
        Analize.Info result = Analize.diff(previous, current);
        assertThat(result.added, is(0));
        assertThat(result.changed, is(1));
        assertThat(result.deleted, is(0));
    }

    @Test
    public void whenOneDeleted() {
        Analize.User u1 = new Analize.User(1, "A");
        Analize.User u2 = new Analize.User(2, "B");
        Analize.User u3 = new Analize.User(3, "C");
        List<Analize.User> previous = List.of(u1, u2, u3);
        List<Analize.User> current = List.of(u1, u3);
        Analize.Info result = Analize.diff(previous, current);
        assertThat(result.added, is(0));
        assertThat(result.changed, is(0));
        assertThat(result.deleted, is(1));
    }

    @Test
    public void whenOneAdded() {
        Analize.User u1 = new Analize.User(1, "A");
        Analize.User u2 = new Analize.User(2, "B");
        Analize.User u3 = new Analize.User(3, "C");
        List<Analize.User> previous = List.of(u1, u2, u3);
        List<Analize.User> current = List.of(u1, u2, u3, new Analize.User(4, "D"));
        Analize.Info result = Analize.diff(previous, current);
        assertThat(result.added, is(1));
        assertThat(result.changed, is(0));
        assertThat(result.deleted, is(0));
    }

    @Test
    public void whenAllChanged() {
        Analize.User u1 = new Analize.User(1, "A");
        Analize.User u2 = new Analize.User(2, "B");
        Analize.User u3 = new Analize.User(3, "C");
        List<Analize.User> previous = List.of(u1, u2, u3);
        List<Analize.User> current = List.of(new Analize.User(1, "AA"), u2, new Analize.User(4, "D"));
        Analize.Info result = Analize.diff(previous, current);
        assertThat(result.added, is(1));
        assertThat(result.changed, is(1));
        assertThat(result.deleted, is(1));
    }

}