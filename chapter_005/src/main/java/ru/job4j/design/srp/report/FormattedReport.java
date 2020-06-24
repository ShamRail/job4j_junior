package ru.job4j.design.srp.report;

import ru.job4j.design.srp.store.Employer;
import ru.job4j.design.srp.parser.Parser;

import java.util.function.Predicate;

public interface FormattedReport extends Report {
    String parse(Predicate<Employer> filter, Parser parser);
}
