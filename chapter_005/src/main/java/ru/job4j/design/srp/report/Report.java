package ru.job4j.design.srp.report;

import ru.job4j.design.srp.store.Employer;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employer> filter);
}
