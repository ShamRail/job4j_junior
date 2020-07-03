package ru.job4j.design.srp;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.design.srp.report.AccountantReport;
import ru.job4j.design.srp.report.Report;
import ru.job4j.design.srp.store.Employer;
import ru.job4j.design.srp.store.MemStore;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static ru.job4j.design.srp.utils.DateFormatter.format;

public class AccountantReportTest {

    @Test
    @Ignore
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 150);
        store.add(worker);
        Report engine = new AccountantReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(format(worker.getHired())).append(";")
                .append(format(worker.getFired())).append(";")
                .append("2,50").append(";")
                .append(System.lineSeparator());
        assertEquals(engine.generate(em -> true), expect.toString());
    }

}