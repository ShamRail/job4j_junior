package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ProgrammerReportTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ProgrammerReport(store);

        StringJoiner html = new StringJoiner(System.lineSeparator());

        html.add("<!DOCTYPE html>");
        html.add("<html>");
        html.add("<head>");
        html.add("<meta charset=\"UTF-8\">");
        html.add("<title>Employers</title>");
        html.add("</head>");
        html.add("<body>");

        html.add("<table>");
        html.add("<tr>");
        html.add("<th>Name</th>");
        html.add("<th>Hired</th>");
        html.add("<th>Fired</th>");
        html.add("<th>Salary</th>");
        html.add("</tr>");

        html.add("<tr>");
        html.add(String.format("<td>%s</td>", worker.getName()));
        html.add(String.format("<td>%s</td>", worker.getHired()));
        html.add(String.format("<td>%s</td>", worker.getFired()));
        html.add(String.format("<td>%s</td>", worker.getSalary()));
        html.add("</tr>");

        html.add("</table>");
        html.add("</body>");
        html.add("</html>");

        assertThat(engine.generate(em -> true), is(html.toString()));
    }

}