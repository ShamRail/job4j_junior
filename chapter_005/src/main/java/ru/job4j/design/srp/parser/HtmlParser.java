package ru.job4j.design.srp.parser;

import ru.job4j.design.srp.store.Employer;
import ru.job4j.design.srp.utils.DateFormatter;

import java.util.List;
import java.util.StringJoiner;

public class HtmlParser implements Parser {
    @Override
    public String parse(List<Employer> employers) {
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

        for (Employer employer : employers) {
            html.add("<tr>");
            html.add(String.format("<td>%s</td>", employer.getName()));
            html.add(String.format("<td>%s</td>", DateFormatter.format(employer.getHired())));
            html.add(String.format("<td>%s</td>", DateFormatter.format(employer.getFired())));
            html.add(String.format("<td>%s</td>", employer.getSalary()));
            html.add("</tr>");
        }

        html.add("</table>");
        html.add("</body>");
        html.add("</html>");

        return html.toString();
    }
}
