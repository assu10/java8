package com.assu.study.mejava8.chap11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//  public List<String> findPrices(String product) {
//    return shops.stream()
//        .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
//        .collect(Collectors.toList());
//  }

  // 병렬 스트림으로 요청 병렬화
//  public List<String> findPricesParallel(String product) {
//    return shops.parallelStream()
//        .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
//        .collect(Collectors.toList());
//  }

  // 동기 호출을 비동기 호출로 구현
//  public List<String> findPricesFuture(String product) {
//    List<CompletableFuture<String>> priceFutures =
//        shops.stream()
//            .map(
//                shop ->
//                    CompletableFuture.supplyAsync(
//                        () ->
//                            String.format(
//                                "%s price is %.2f", shop.getName(), shop.getPrice(product))))
//            .collect(Collectors.toList());
//
//    return priceFutures.stream()
//        .map(CompletableFuture::join) // 모든 비동기 동작이 끝나길 기다림
//        .collect(Collectors.toList());
//  }

  // executor 사용
//  public List<String> findPricesExecutor(String product) {
//    List<CompletableFuture<String>> priceFutures =
//        shops.stream()
//            .map(
//                shop ->
//                    CompletableFuture.supplyAsync(
//                        () ->
//                            String.format(
//                                "%s price is %.2f", shop.getName(), shop.getPrice(product)),
//                        executor))
//            .collect(Collectors.toList());
//
//    return priceFutures.stream()
//        .map(CompletableFuture::join) // 모든 비동기 동작이 끝나길 기다림
//        .collect(Collectors.toList());
//  }

  // Discount 서비스를 사용하여 제품명 입력 시 상점 이름과 제품가격 반환 (순차, 동기적)
//  public List<String> findPricesSequential(String product) {
//    return shops.stream()
//        .map(shop -> shop.getPrice(product))  // Stream<String> 반환, 각 상점에서 할인 전 가격 조회
//        .map(Quote::parse)  // Stream<Quote> 반환, 상점에서 반환한 문자열을 Quote 객체로 변환
//        .map(Discount::applyDiscount) // Stream<String> 반환, 할인 서비스를 사용하여 각 Quote 에 할인 적용
//        .collect(Collectors.toList());
//  }

  // Discount 서비스를 사용하여 제품명 입력 시 상점 이름과 제품가격 반환 (비동기적)
  public List<String> findPriceFuture(String product) {
    List<CompletableFuture<String>> priceFuture = shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor)) // 각 상점에서 할인 전 가격을 비동기적으로 조회
        .map(future -> future.thenApply(Quote::parse))  // 상점에서 반환한 문자열을 Quote 객체로 변환
        // 결과 Future 를 다른 비동기 작업과 조합하여 할인 코드 적용
        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
        .collect(Collectors.toList());

    return priceFuture.stream()
        .map(CompletableFuture::join) // 스크림의 모든 Future 가 종료되길 기다렸다가 각각의 결과 추출
        .collect(Collectors.toList());
  }

  public Stream<CompletableFuture<String>> findPriceStream(String product) {
    return shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor)) // Stream<CompletableFuture<String>> 반환
        .map(future -> future.thenApply(Quote::parse))  // // Stream<CompletableFuture<Quote>> 반환
        .map(future -> future.thenCompose(quote ->
            CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
  }

  public void printPricesStream(String product) {
    long start = System.nanoTime();

    CompletableFuture[] futures = findPriceStream(product)  // Stream<CompletableFuture<String>> 반환
        //.map(f -> f.thenAccept(System.out::println))  // Stream<CompletableFuture<Void>> 반환
        .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
        .toArray(size -> new CompletableFuture[size]);

    CompletableFuture.allOf(futures).join();

    System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " ms");
  }

  // 두 개의 독립적인 CompletableFuture 합치기
//  public List<String> findPriceInUSD(String product) {
//    List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
//
//    for (Shop shop : shops) {
//      CompletableFuture<Double> futurePriceInUSD =
//          // 제품 가격 정보를 요청하는 첫 번째 태스크 생성
//          CompletableFuture.supplyAsync(() -> shop.getPrice(product))
//              .thenCombine(
//                  // 환율 정보를 요청하는 독립적인 두 번째 태스크 생성
//                  CompletableFuture.supplyAsync(() -> Exchange.getRate(Exchange.Money.EUR, Exchange.Money.USD)),
//                  // 두 결과를 곱해서 가격과 환율 정보를 합침
//                  (price, rate) -> price * rate);
//      priceFutures.add(futurePriceInUSD);
//    }
//
//    List<String> prices = priceFutures.stream()
//        .map(CompletableFuture::join)
//        .map(price -> price.toString())
//        .collect(Collectors.toList());
//
//    return prices;
//  }
}
