package ru.job4j.design.lsp.product.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtil {

    public static int pastTimePercent(LocalDateTime first, LocalDateTime second) {
        LocalDateTime now = LocalDateTime.now();
        if (first.isAfter(second)) {
            throw new IllegalArgumentException();
        }
        if (now.isBefore(first)) {
            return 0;
        }
        if (now.isAfter(second)) {
            return 100;
        }
        float commonTime = Duration.between(first, second).toDays();
        float wentTime = Duration.between(first, now).toDays();
        return (int) (wentTime / commonTime * 100);
    }

}
