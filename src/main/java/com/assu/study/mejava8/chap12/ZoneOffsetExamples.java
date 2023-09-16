package com.assu.study.mejava8.chap12;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ZoneOffsetExamples {
  public static void main(String[] args) {
    ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
    // -05:00
    System.out.println(newYorkOffset);

    // 2023-02-10T18:10:20
    LocalDateTime localDateTime1 = LocalDateTime.of(2023, 2, 10, 18, 10, 20);
    // 2023-02-10T18:10:20-05:00
    OffsetDateTime localDateTimeInNewYork = OffsetDateTime.of(localDateTime1, newYorkOffset);

    System.out.println(localDateTime1);
    System.out.println(localDateTimeInNewYork);
  }
}
