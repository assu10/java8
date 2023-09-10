package com.assu.study.mejava8.chap11;

import java.util.concurrent.Future;

public class AsyncShopClient {
  public static void main(String[] args) {
    AsyncShop shop = new AsyncShop("assuShop");
    long start = System.nanoTime();

    // 상점에 가격 정보 요청
    Future<Double> futurePrice = shop.getPriceAsync("my product");
    long invocationTime = ((System.nanoTime() - start) / 1_000_000);

    System.out.println("Invocation returned after " + invocationTime + " ms");

    // assuShop 의 가격을 계산하는 동안 다른 작업 수행
    doSomeThingElse();

    try {
      // 가격 정보가 있으면 Future 에서 가격 정보를 읽고, 없으면 가격 정보를 받을 때까지 블록
      double price = futurePrice.get();
      System.out.println("Price: " + Util.format(price));
      // System.out.printf("Price is %.2f%n", price);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("Price returned after " + retrievalTime + "ms");
  }

  private static void doSomeThingElse() {
    Util.delay();
    System.out.println("doSomeThingElse...");
  }
}
