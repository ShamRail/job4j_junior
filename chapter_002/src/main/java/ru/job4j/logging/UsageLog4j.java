package ru.job4j.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4L;
        float f = 5f;
        double d = 6D;
        char c = '7';
        boolean bb = true;
        LOG.debug("Integer types: byte = {}, short = {}, int = {}, long ={}", b, s, i, l);
        LOG.debug("Float types: float = {}, double = {}", f, d);
        LOG.debug("Character type: char = {}", c);
        LOG.debug("Boolean type: boolean = {}", bb);
    }
}