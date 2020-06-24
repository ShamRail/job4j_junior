package ru.job4j.design.srp.report;

import ru.job4j.design.srp.parser.Parser;
import ru.job4j.design.srp.store.Employer;

import java.util.function.Predicate;

public class FormattedAccountantReport implements FormattedReport {

    private Store store;

    private AccountantReport accountantReport;

    public FormattedAccountantReport(Store store, AccountantReport accountantReport) {
        this.store = store;
        this.accountantReport = accountantReport;
    }

    @Override
    public String parse(Predicate<Employer> filter, Parser parser) {
        return parser.parse(store.findBy(filter));
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        return accountantReport.generate(filter);
    }
}
