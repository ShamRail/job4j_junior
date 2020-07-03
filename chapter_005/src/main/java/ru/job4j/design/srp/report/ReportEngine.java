package ru.job4j.design.srp.report;

import ru.job4j.design.srp.store.Employer;
import ru.job4j.design.srp.utils.DateFormatter;

import java.util.function.Predicate;

public class ReportEngine implements Report {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employer employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DateFormatter.format(employee.getHired())).append(";")
                    .append(DateFormatter.format(employee.getFired())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

}
