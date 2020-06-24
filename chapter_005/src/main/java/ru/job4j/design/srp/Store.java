package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    List<Employer> findBy(Predicate<Employer> filter);
}
