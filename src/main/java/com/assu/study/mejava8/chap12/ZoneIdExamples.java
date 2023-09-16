package com.assu.study.mejava8.chap12;

import java.time.*;
import java.util.TimeZone;

public class ZoneIdExamples {
  public static void main(String[] args) {
    ZoneId romeZone = ZoneId.of("Europe/Rome");
    // Europe/Rome
    System.out.println(romeZone);

    ZoneId zoneId = TimeZone.getDefault().toZoneId();
    // Asia/Seoul
    System.out.println(zoneId);

    // ZoneId 객체를 얻은 후 LocalDate, LocalDateTime, Instant 를 이용해서 ZoneDateTime 인스턴스로 변환 가능
    ZoneId romeZone1 = ZoneId.of("Europe/Rome");
    // 2023-03-10
    LocalDate localDate1 = LocalDate.of(2023, Month.MARCH, 10);
    // 2023-03-10T00:00+01:00[Europe/Rome]
    ZonedDateTime zdt1 = localDate1.atStartOfDay(romeZone1);

    // 2023-03-10T13:20
    LocalDateTime localDateTime1 = LocalDateTime.of(2023, Month.MARCH, 10, 13, 20);
    // 2023-03-10T13:20+01:00[Europe/Rome]
    ZonedDateTime zdt2 = localDateTime1.atZone(romeZone1);

    // 2023-07-16T10:12:58.272252Z
    Instant instant1 = Instant.now();
    // 2023-07-16T12:12:58.272252+02:00[Europe/Rome]
    ZonedDateTime zdt3 = instant1.atZone(romeZone1);


    System.out.println(localDate1);
    System.out.println(zdt1);

    System.out.println(localDateTime1);
    System.out.println(zdt2);

    System.out.println(instant1);
    System.out.println(zdt3);
  }
}
