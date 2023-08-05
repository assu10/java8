package com.assu.study.mejava8.chap06;

import java.util.function.Consumer;

public class CollectorHarness {
  public static void main(String[] args) {
    // 425ms
    //System.out.println(execute(QuizPrimeNumber::partitionPrimes) + "ms");

    // 122ms
    System.out.println(execute(CustomCollector::partitionPrimes) + "ms");
  }

  private static long execute(Consumer<Integer> primePartitioner) {
    long fastest = Long.MAX_VALUE;
    // 테스트 10번 반복
    for (int i=0; i<10; i++) {
      long start = System.nanoTime();
      // 백만 개의 숫자를 소수와 비소수로 분할
      primePartitioner.accept(1_000_000);
      long duration = (System.nanoTime() - start) / 1_000_000;

      // 가장 빨리 실행된 값을 저장
      if (duration < fastest) {
        fastest = duration;
      }
    }
    return fastest;
  }
}
