package ru.job4j.design.srp.report;

import ru.job4j.design.srp.store.Employer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HRReport implements Report {

    private Store store;

    public HRReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        List<Employer> employers = sortEmployers(filter);
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        text.append(System.lineSeparator());
        for (Employer employee : employers) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private List<Employer> sortEmployers(Predicate<Employer> filter) {
        return store
                    .findBy(filter)
                    .stream()
                    .sorted((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()))
                    .collect(Collectors.toList());
    }
}
