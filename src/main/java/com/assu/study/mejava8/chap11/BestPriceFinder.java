package com.assu.study.mejava8.chap11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class BestPriceFinder {
  private final List<Shop> shops =
      Arrays.asList(
          new Shop("BestPrice"),
          new Shop("CoolPrice"),
          new Shop("SeventeenPrice"),
          new Shop("SeventeenPrice1"),
          new Shop("SeventeenPrice2"),
          new Shop("SeventeenPrice3"),
          new Shop("SeventeenPrice4"),
          new Shop("SeventeenPrice5"),
          new Shop("SeventeenPrice6"),
          new Shop("SeventeenPrice7"),
          new Shop("SeventeenPrice8"),
          new Shop("SeventeenPrice9"),
          new Shop("SeventeenPrice10"),
          new Shop("SeventeenPrice11"),
          new Shop("TXTPrice"));

  // 커스텀 Executor
  private final Executor executor =
      Executors.newFixedThreadPool(
          // 상점 수만큼의 스레드를 갖는 풀 생성 (스레드 수의 범위는 0~100)
          Math.min(shops.size(), 100),
          new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
              Thread t = new Thread(r);
              t.setDaemon(true); // 프로그램 종료를 방해하지 않는 데몬 스레드 사용
              return t;
            }
          });

  // 제품명 입력 시 상점 이름과 제품가격 반환
  public List<String> findPrices(String product) {
    return shops.stream()
        .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
        .collect(Collectors.toList());
  }

  // 병렬 스트림으로 요청 병렬화
  public List<String> findPricesParallel(String product) {
    return shops.parallelStream()
        .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
        .collect(Collectors.toList());
  }

  // 동기 호출을 비동기 호출로 구현
  public List<String> findPricesFuture(String product) {
    List<CompletableFuture<String>> priceFutures =
        shops.stream()
            .map(
                shop ->
                    CompletableFuture.supplyAsync(
                        () ->
                            String.format(
                                "%s price is %.2f", shop.getName(), shop.getPrice(product))))
            .collect(Collectors.toList());

    return priceFutures.stream()
        .map(CompletableFuture::join) // 모든 비동기 동작이 끝나길 기다림
        .collect(Collectors.toList());
  }

  // executor 사용
  public List<String> findPricesExecutor(String product) {
    List<CompletableFuture<String>> priceFutures =
        shops.stream()
            .map(
                shop ->
                    CompletableFuture.supplyAsync(
                        () ->
                            String.format(
                                "%s price is %.2f", shop.getName(), shop.getPrice(product)),
                        executor))
            .collect(Collectors.toList());

    return priceFutures.stream()
        .map(CompletableFuture::join) // 모든 비동기 동작이 끝나길 기다림
        .collect(Collectors.toList());
  }
}
