package com.assu.study.mejava8.chap12;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class TemporalAdjustersExamples {
  public static void main(String[] args) {
    // 2023-05-09
    LocalDate localDate1 = LocalDate.of(2023, 05, 9);

    // 2023-05-14, 현재 날짜를 포함하여 다음으로 돌아오는 일요일의 날짜
    LocalDate localDate2 = localDate1.with(nextOrSame(DayOfWeek.SUNDAY));

    // 2023-05-31, 그 달의 마지막 날짜 반환
    LocalDate localDate3 = localDate1.with(lastDayOfMonth());

    System.out.println(localDate1);
    System.out.println(localDate2);
    System.out.println(localDate3);
  }
}
