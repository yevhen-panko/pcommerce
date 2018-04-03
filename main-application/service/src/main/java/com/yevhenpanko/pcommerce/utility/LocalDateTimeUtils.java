package com.yevhenpanko.pcommerce.utility;

import java.time.LocalDateTime;
import java.util.Date;

import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;

public class LocalDateTimeUtils {

    public static LocalDateTime of(Date date) {
        return ofInstant(date.toInstant(), systemDefault());
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(systemDefault()).toInstant());
    }
}
