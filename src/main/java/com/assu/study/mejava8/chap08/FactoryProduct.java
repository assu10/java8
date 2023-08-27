package com.assu.study.mejava8.chap08;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// 팩토리 클래스
public class FactoryProduct {

  // 생성자가 외부로 노출되지 않음
  public static Product createProduct(String s) {
    switch (s) {
      case "loan":
        return new Loan();
      case "stock":
        return new Stock();
      default:
        throw new RuntimeException("No such product: " + s);
    }
  }

  public static Product createProductLambda(String s) {
    Supplier<Product> p = map.get(s);
    if (p != null) {
      return p.get();
    }
    throw new RuntimeException("No such product: " + s);
  }


  public interface Product { }
  static private class Loan implements Product { }
  static private class Stock implements Product { }

  final static private Map<String, Supplier<Product>> map = new HashMap<>();
  static {
    map.put("loan", Loan::new); // 메서드 레퍼런스
    map.put("stock", Stock::new);
  }
}


