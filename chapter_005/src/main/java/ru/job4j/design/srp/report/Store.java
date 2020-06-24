package ru.job4j.design.srp.report;

import ru.job4j.design.srp.store.Employer;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    List<Employer> findBy(Predicate<Employer> filter);
}
