package com.assu.study.mejava8.chap11;

import static com.assu.study.mejava8.chap11.Util.delay;
import static com.assu.study.mejava8.chap11.Util.format;

// 할인 서비스 구현
public class Discount {
  public enum Code {
    NONE(0), SILVER(1), GOLD(10), PLATINUM(15), DIAMOND(20);
    private final int percentage;

    Code(int percentage) {
      this.percentage = percentage;
    }
  }

  // Quote 객체를 인수로 받아 할인된 가격 문자열 반환
  public static String applyDiscount(Quote quote) {
    return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
  }

  private static double apply(double price, Code code) {
    delay();  // Discount 는 원격 서비스라고 간주하므로 지연이 있는 것처럼 지연을 줌
    return format(price * (100 - code.percentage) / 100);
  }
}
