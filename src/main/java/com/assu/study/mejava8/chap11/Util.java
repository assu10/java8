package com.assu.study.mejava8.chap11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Util {

  private static final DecimalFormat formatter =
      new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

  // 외부 서비스를 호출하는 것처럼 인위적으로 1초 지연시키는 메서환
  public static void delay() {
    int delay = 1000;
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static double format(double number) {
    synchronized (formatter) {
      return Double.valueOf(formatter.format(number));
    }
  }
}
