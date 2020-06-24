package ru.job4j.design.srp.parser;

import ru.job4j.design.srp.store.Employer;

import java.util.List;

public interface Parser {
    String parse(List<Employer> employers);
}
