package ru.job4j.design.srp.report;

import ru.job4j.design.srp.store.Employer;
import ru.job4j.design.srp.parser.HtmlParser;

import java.util.function.Predicate;

public class ProgrammerReport implements Report {

    protected Store store;

    public ProgrammerReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
       return new HtmlParser().parse(store.findBy(filter));
    }
}
