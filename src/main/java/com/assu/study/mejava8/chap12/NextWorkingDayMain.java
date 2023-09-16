package com.assu.study.mejava8.chap12;

import java.time.LocalDate;

public class NextWorkingDayMain {
  public static void main(String[] args) {
    LocalDate date = LocalDate.now();
    //date = date.with(new NextWorkingDay()); // 2023-05-15
    date = date.with(NextWorkingDay.nextWorkingDay2); // 2023-05-15

    System.out.println(date);
  }
}
