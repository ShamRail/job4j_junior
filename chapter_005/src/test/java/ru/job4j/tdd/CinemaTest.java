package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CinemaTest {

    @Test
    @Ignore
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    @Ignore
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Collections.singletonList(new Session3D())));
    }

    @Test
    @Ignore
    public void add() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(new Session3D()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        LocalDateTime nextYear = LocalDateTime.now().plusYears(1);
        date.set(nextYear.getYear(), nextYear.getMonthValue(), nextYear.getDayOfMonth(), nextYear.getHour(), nextYear.getMinute());
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }


    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1000, 1000, date);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenNotEnoughMoney() {
        Account account = new AccountCinema(10);
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }


}