package ru.job4j.design.srp.report;

import ru.job4j.design.srp.parser.Parser;
import ru.job4j.design.srp.store.Employer;

import java.util.function.Predicate;

public class FormattedProgrammerReport extends ProgrammerReport implements FormattedReport {

    public FormattedProgrammerReport(Store store) {
        super(store);
    }

    @Override
    public String parse(Predicate<Employer> filter, Parser parser) {
        return parser.parse(store.findBy(filter));
    }

}
