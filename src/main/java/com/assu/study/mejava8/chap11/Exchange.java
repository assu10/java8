package com.assu.study.mejava8.chap11;

import static com.assu.study.mejava8.chap11.Util.delay;

public class Exchange {
  public static double getRate(Money source, Money dest) {
    delay();  // 원격 서비스라 가정하여 지연을 줌
    return dest.rate / source.rate;
  }

  public enum Money {
    USD(1.0), EUR(1.5);
    private final double rate;

    Money(double rate) {
      this.rate = rate;
    }
  }
}
