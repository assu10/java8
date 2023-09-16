package com.assu.study.mejava8.chap12;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Date;

public class DateTimeExamples {
  public static void main(String[] args) {
    // Date 클래스 (추천하지 않음)
    // 2014.3.2
    Date date = new Date(114, 2, 2);  // Sun Mar 02 00:00:00 KST 2014
    System.out.println(date);

    // LocalDate
    LocalDate localDate = LocalDate.of(2014, 3, 2); // 2014-03-02

    int year = localDate.getYear(); // 2014
    Month month = localDate.getMonth(); // MARCH
    int day = localDate.getDayOfMonth();  // 2

    DayOfWeek dayOfWeek = localDate.getDayOfWeek(); // 요일, SUNDAY
    int len = localDate.lengthOfMonth();  // 3월의 일 수, 31
    boolean leap = localDate.isLeapYear();  // 윤달 여부, false

    System.out.println("localDate: " + localDate);  // 2014-03-02
    System.out.println("year: " + year);  // 2014
    System.out.println("month: " + month);  // MARCH
    System.out.println("day: " + day);  // 2
    System.out.println("dayOfWeek: " + dayOfWeek);  // SUNDAY
    System.out.println("len: " + len);  // 31
    System.out.println("leap: " + leap);  // false

    LocalDate today = LocalDate.now();
    System.out.println("now: " + today);  // now: 2023-09-16

    int year2 = localDate.get(ChronoField.YEAR);
    int month2 = localDate.get(ChronoField.MONTH_OF_YEAR);
    int day2 = localDate.get(ChronoField.DAY_OF_MONTH);

    System.out.println("year2: " + year2);  // 2014
    System.out.println("month2: " + month2);  // 3
    System.out.println("day2: " + day2);  // 2


    // LocalTime
    LocalTime localTime = LocalTime.of(13, 10, 20); // 13:10:20

    int hour = localTime.getHour(); // 13
    int minute = localTime.getMinute(); // 10
    int second = localTime.getSecond(); // 20

    System.out.println("localTime: " + localTime);  // 13:10:20
    System.out.println("hour: " + hour);  // 13
    System.out.println("minute: " + minute);  // 10
    System.out.println("second: " + second);  // 20


    // parse()
    LocalDate localDate1 = LocalDate.parse("2014-03-02"); // 2014-03-02
    LocalTime localTime1 = LocalTime.parse("13:10:20"); // 13:10:20

    System.out.println("localDate1: " + localDate1);  // 2014-03-02
    System.out.println("localTime1: " + localTime1); // 13:10:20


    // LocalDateTime

    // 2014-03-02T13:10:20
    LocalDateTime localDateTime = LocalDateTime.of(2014, Month.MARCH, 2, 13, 10, 20);
    // 2014-03-02T13:10:20
    LocalDateTime localDateTime2 = LocalDateTime.of(2014, 3, 2, 13, 10, 20);

    System.out.println("localDateTime: " + localDateTime);
    System.out.println("localDateTime2: " + localDateTime2);


    System.out.println("localDate: " + localDate);  // 2014-03-02
    System.out.println("localTime: " + localTime);  // 13:10:20

    LocalDateTime localDateTime3 = LocalDateTime.of(localDate, localTime);  // 2014-03-02T13:10:20
    LocalDateTime localDateTime4 = localDate.atTime(13, 11, 22);  // 2014-03-02T13:11:22
    LocalDateTime localDateTime5 = localDate.atTime(localTime); // 2014-03-02T13:10:20
    LocalDateTime localDateTime6 = localTime.atDate(localDate); // 2014-03-02T13:10:20

    System.out.println("localDateTime3: " + localDateTime3);
    System.out.println("localDateTime4: " + localDateTime4);
    System.out.println("localDateTime5: " + localDateTime5);
    System.out.println("localDateTime6: " + localDateTime6);


    System.out.println("localDateTime: " + localDateTime);  // 2014-03-02T13:10:20

    LocalDate localDate3 = localDateTime.toLocalDate(); // 2014-03-02
    LocalTime localTime3 = localDateTime.toLocalTime(); // 13:10:20

    System.out.println("localDate3: " + localDate3);
    System.out.println("localTime3: " + localTime3);

    // Instant
    System.out.println(Instant.ofEpochSecond(3)); // 1970-01-01T00:00:03Z
    System.out.println(Instant.ofEpochSecond(3, 0)); // 1970-01-01T00:00:03Z
    // 3초 이후의 1억 나노초(1초)
    System.out.println(Instant.ofEpochSecond(3, 1_000_000_000)); // 1970-01-01T00:00:04Z
    // 3초 이전의 1억 나노초(1초)
    System.out.println(Instant.ofEpochSecond(3, -1_000_000_000)); // 1970-01-01T00:00:02Z

    System.out.println(Instant.now());  // 2023-09-16T04:14:17.608598Z

  }
}
