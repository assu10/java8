package com.assu.study.mejava8.chap12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateTimeFormatterExamples {
  public static void main(String[] args) {
    LocalDate localDate1 = LocalDate.of(2023, 5, 9);

    // 20230509
    String s1 = localDate1.format(DateTimeFormatter.BASIC_ISO_DATE);

    // 2023-05-09
    String s2 = localDate1.format(DateTimeFormatter.ISO_LOCAL_DATE);

    System.out.println(s1);
    System.out.println(s2);

    // 2023-05-09
    LocalDate localDate2 = LocalDate.parse("20230509", DateTimeFormatter.BASIC_ISO_DATE);

    // 2023-05-09
    LocalDate localDate3 = LocalDate.parse("2023-05-09", DateTimeFormatter.ISO_LOCAL_DATE);

    System.out.println(localDate2);
    System.out.println(localDate3);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // 2023-05-09
    LocalDate localDate4 = LocalDate.of(2023, 5, 9);
    // 09/05/2023
    String formattedDateString = localDate4.format(formatter);
    // 2023-05-09
    LocalDate localDate5 = LocalDate.parse(formattedDateString, formatter);

    System.out.println(formatter);
    System.out.println(localDate4);
    System.out.println(formattedDateString);
    System.out.println(localDate5);

    DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMM yyyy", Locale.ITALIAN);
    // 2023-05-09
    LocalDate localDate6 = LocalDate.of(2023, 5, 9);
    // 9. mag 2023
    String formattedDateString6 = localDate6.format(italianFormatter);
    // 2023-05-09
    LocalDate localDate7 = LocalDate.parse(formattedDateString6, italianFormatter);

    System.out.println(localDate6);
    System.out.println(formattedDateString6);
    System.out.println(localDate7);

    DateTimeFormatter italianFormatter1 = new DateTimeFormatterBuilder()
        .appendText(ChronoField.DAY_OF_MONTH)
        .appendLiteral(". ")
        .appendText(ChronoField.MONTH_OF_YEAR)
        .appendLiteral(" ")
        .appendText(ChronoField.YEAR)
        .parseCaseInsensitive()
        .toFormatter(Locale.ITALIAN);

    // 2023-05-09
    LocalDate localDate8 = LocalDate.of(2023, 5, 9);
    // 9. maggio 2023
    String formattedDateString8 = localDate8.format(italianFormatter1);
    // 2023-05-09
    LocalDate localDate9 = LocalDate.parse(formattedDateString8, italianFormatter1);

    System.out.println(localDate8);
    System.out.println(formattedDateString8);
    System.out.println(localDate9);
  }
}
