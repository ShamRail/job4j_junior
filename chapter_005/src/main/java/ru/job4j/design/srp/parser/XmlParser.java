package ru.job4j.design.srp.parser;

import ru.job4j.design.srp.store.Employer;

import java.util.List;
import java.util.StringJoiner;

public class XmlParser implements Parser {
    @Override
    public String parse(List<Employer> employers) {
        StringJoiner xml = new StringJoiner(System.lineSeparator());
        xml.add("<employers>");
        for (Employer employer : employers) {
            xml.add(parserEmployer(employer));
        }
        xml.add("</employers>");
        return xml.toString();
    }

    private String parserEmployer(Employer employer) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        sj.add("<employer>");
        sj.add(String.format("<name>%s</name>", employer.getName()));
        sj.add(String.format("<hired>%s</hired>", employer.getHired()));
        sj.add(String.format("<fired>%s</fired>", employer.getFired()));
        sj.add(String.format("<salary>%s</salary>", employer.getSalary()));
        sj.add("</employer>");
        return sj.toString();
    }

}
