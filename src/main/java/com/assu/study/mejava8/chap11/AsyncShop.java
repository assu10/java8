package com.assu.study.mejava8.chap11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class AsyncShop {
  private final Random random;
  private final String name;

  public AsyncShop(String name) {
    this.name = name;
    random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
  }

  public Future<Double> getPriceAsync(String product) {
    /*    // 계산 결과를 포함할 CompletableFuture 생성
    CompletableFuture<Double> future = new CompletableFuture<>();

    new Thread(
            () -> {
              try {
                // 다른 스레드에서 비동기적으로 계산 수행
                double price = calculatePrice(product);
                // 계산이 완료되면 Future 에 값 설정
                future.complete(price);
              } catch (Exception e) {
                // 도중에 에러 발생 시 에러를 포함시켜서 Future 종료
                future.completeExceptionally(e);
              }
            })
        .start();

    // 계산 결과가 완료되길 기다리지 않고 Future 반환
    return future;*/

    return CompletableFuture.supplyAsync(() -> calculatePrice(product));
  }

  private double calculatePrice(String product) {
    Util.delay();
    return random.nextDouble() * product.charAt(0) + product.charAt(1);
  }
}
