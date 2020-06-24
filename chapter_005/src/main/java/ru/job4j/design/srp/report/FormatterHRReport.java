package ru.job4j.design.srp.report;

import ru.job4j.design.srp.parser.Parser;
import ru.job4j.design.srp.store.Employer;

import java.util.function.Predicate;

public class FormatterHRReport implements FormattedReport {

    private Store store;

    private HRReport hrReport;

    public FormatterHRReport(Store store, HRReport hrReport) {
        this.store = store;
        this.hrReport = hrReport;
    }

    @Override
    public String parse(Predicate<Employer> filter, Parser parser) {
        return parser.parse(store.findBy(filter));
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        return hrReport.generate(filter);
    }
}
