package com.assu.study.mejava8.chap12;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class DateAdjust {
  public static void main(String[] args) {
    // 2023-03-01
    LocalDate localDate1 = LocalDate.of(2023, 3, 1);

    // 2022-03-01
    LocalDate localDate2 = localDate1.withYear(2022);
    // 2023-03-10
    LocalDate localDate3 = localDate1.withDayOfMonth(10);
    // 2023-09-01
    LocalDate localDate4 = localDate1.with(ChronoField.MONTH_OF_YEAR, 9);

    // 2023-03-01, 원래 객체는 변경되지 않음
    System.out.println(localDate1);

    System.out.println(localDate1);
    System.out.println(localDate2);
    System.out.println(localDate3);
    System.out.println(localDate4);

    // 2023-03-01
    LocalDate localDate5 = LocalDate.of(2023, 3, 1);

    // 2023-03-08
    LocalDate localDate6 = localDate5.plusWeeks(1);
    // 2022-03-01
    LocalDate localDate7 = localDate5.minusYears(1);
    // 2023-04-01
    LocalDate localDate8 = localDate5.plus(1, ChronoUnit.MONTHS);

    System.out.println(localDate5);
    System.out.println(localDate6);
    System.out.println(localDate7);
    System.out.println(localDate8);
  }
}
