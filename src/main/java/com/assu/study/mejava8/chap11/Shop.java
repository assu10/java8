package com.assu.study.mejava8.chap11;

import java.util.Random;

public class Shop {

  private final String name;
  private final Random random;

  public Shop(String name) {
    this.name = name;
    random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
  }

  // 제품명에 해당하는 가격 반환
//  public double getPrice(String product) {
//    double price = calculatePrice(product);
//    return price;
//  }

  public String getPrice(String product) {
    double price = calculatePrice(product);
    Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];

    return name + ":" + price + ":" + code;
  }

  private double calculatePrice(String product) {
    Util.delay();
    return random.nextDouble() * product.charAt(0) + product.charAt(1);
  }

  public String getName() {
    return name;
  }
}
