package com.assu.study.mejava8.chap12;

import java.time.DayOfWeek;
import java.time.temporal.*;

public class NextWorkingDay implements TemporalAdjuster {
  public static TemporalAdjuster nextWorkingDay2 = TemporalAdjusters.ofDateAdjuster(
      temporal -> {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if (dow == DayOfWeek.FRIDAY) {
          dayToAdd = 3;
        } else if (dow == DayOfWeek.SATURDAY) {
          dayToAdd = 2;
        }
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
      }
  );
//  public Temporal adjustInto(Temporal temporal) {
//    // 현재 요일 조회
//    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
//    int dayToAdd = 1;
//    if (dow == DayOfWeek.FRIDAY) {
//      dayToAdd = 3;
//    } else if (dow == DayOfWeek.SATURDAY) {
//      dayToAdd = 2;
//    }
//    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
//  }

  @Override
  public Temporal adjustInto(Temporal temporal) {
    // 현재 요일 조회
    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
    int dayToAdd = 1;
    if (dow == DayOfWeek.FRIDAY) {
      dayToAdd = 3;
    } else if (dow == DayOfWeek.SATURDAY) {
      dayToAdd = 2;
    }
    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
  }
}
