package ru.job4j.design.srp.parser;

import ru.job4j.design.srp.store.Employer;

import java.util.List;
import java.util.StringJoiner;

public class JsonParser implements Parser {
    @Override
    public String parse(List<Employer> employers) {
        StringJoiner json = new StringJoiner(System.lineSeparator());
        json.add("[");
        for (Employer employer : employers) {
            json.add(parseEmployer(employer));
        }
        json.add("]");
        return json.toString();
    }

    private String parseEmployer(Employer employer) {
        return String.format(
                "{%s \"name\": %s,%s \"hired\": %s,%s \"fired\":%s,%s \"salary\":%s %s}",
                System.lineSeparator(),
                employer.getName(),
                System.lineSeparator(),
                employer.getHired(),
                System.lineSeparator(),
                employer.getFired(),
                System.lineSeparator(),
                employer.getSalary(),
                System.lineSeparator()

        );
    }

}
