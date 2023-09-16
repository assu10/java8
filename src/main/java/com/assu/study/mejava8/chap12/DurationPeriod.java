package com.assu.study.mejava8.chap12;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DurationPeriod {
  public static void main(String[] args) {
    // Duration

    // 13:10:20
    LocalTime localTime1 = LocalTime.of(13, 10, 20);
    // 13:10:50
    LocalTime localTime2 = LocalTime.of(13, 10, 50);

    // 2014-03-02T13:10:20
    LocalDateTime localDateTime1 = LocalDateTime.of(2014, Month.MARCH, 2, 13, 10, 20);
    // 2014-03-02T13:10:50
    LocalDateTime localDateTime2 = LocalDateTime.of(2014, Month.MARCH, 2, 13, 10, 50);

    // 1970-01-01T00:00:03Z
    Instant instant1 = Instant.ofEpochSecond(3);
    // 1970-01-01T00:00:10Z
    Instant instant2 = Instant.ofEpochSecond(10);

    // PT30S
    Duration d1 = Duration.between(localTime1, localTime2);
    // PT-30S
    Duration d2 = Duration.between(localTime2, localTime1);
    // PT30S
    Duration d3 = Duration.between(localDateTime1, localDateTime2);
    // PT7S
    Duration d4 = Duration.between(instant1, instant2);

    System.out.println("d1: " + d1);
    System.out.println("d2: " + d2);
    System.out.println("d3: " + d3);
    System.out.println("d4: " + d4);


    // Period
    LocalDate localDate1 = LocalDate.of(2014, 3, 2); // 2014-03-02
    LocalDate localDate2 = LocalDate.of(2014, 3, 5); // 2014-03-05

    // P3D
    Period p1 = Period.between(localDate1, localDate2);
    // P0D
    Period p2 = Period.between(localDate2, localDate2);
    //Period p3 = Period.between(localDateTime1, localDateTime2); // 오류

    System.out.println(p1);
    System.out.println(p2);

    Duration d5 = Duration.ofMinutes(3);
    Duration d6 = Duration.of(3, ChronoUnit.MINUTES);

    // PT3M
    System.out.println(d5);
    // PT3M
    System.out.println(d6);

    // P10D
    Period p3 = Period.ofDays(10);
    // P21D (3주는 21일)
    Period p4 = Period.ofWeeks(3);
    // P2Y6M1D (2년 6개월 1일)
    Period p5 = Period.of(2, 6, 1);

    System.out.println(p3);
    System.out.println(p4);
    System.out.println(p5);
  }
}
