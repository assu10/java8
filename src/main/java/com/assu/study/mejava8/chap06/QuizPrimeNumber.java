package com.assu.study.mejava8.chap06;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class QuizPrimeNumber {
  public static void main(String[] args) {
    System.out.println("Primes and NonPrime: " + partitionPrimes(10));
    System.out.println("dd: "+ (int) Math.sqrt(8));
  }

  // 주어진 수가 소수인지 판단
  private static boolean isPrime(int candidate) {
    // 소수의 대상은 주어진 수의 제곱근 이하로 제한
    int candidateRoot = (int) Math.sqrt(candidate);
    return IntStream.rangeClosed(2, candidateRoot)
            .noneMatch(i -> candidate % i == 0);
  }

  // 소수와 비소수 구분
  public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
    return IntStream.rangeClosed(2, n)  // IntStream 반환
            .boxed()  // Stream<Integer> 반환
            .collect(
                    partitioningBy(i -> isPrime(i))
            );
  }
}