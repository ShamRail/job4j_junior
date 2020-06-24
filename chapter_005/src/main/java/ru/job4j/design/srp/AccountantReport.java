package ru.job4j.design.srp;

import java.util.function.Predicate;

public class AccountantReport implements Report {

    private Store store;

    public AccountantReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employer employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(MoneyFormatter.inDollars(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
