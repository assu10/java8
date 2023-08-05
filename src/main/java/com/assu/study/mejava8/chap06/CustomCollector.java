package com.assu.study.mejava8.chap06;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class CustomCollector {
  public static void main(String[] args) {
    System.out.println("Primes and NonPrime: " + partitionPrimes(10));
  }

  // 소수인지 확인하는 Predicate, 중간 결과 리스트를 전달받을 수 있음
//  public static boolean isPrimev1(List<Integer> primes, int candidate) {
//    return primes.stream().noneMatch(i -> candidate % i == 0);
//  }
//
//  // 소수인지 확인하는 Predicate, 중간 결과 리스트를 전달받을 수 있음
//  public static boolean isPrimev2(List<Integer> primes, int candidate) {
//    double candidateRoot = (int) Math.sqrt(candidate);
//    return customTakeWhile(primes, i -> i <= candidateRoot) // List<Integer> 반환
//            .stream() // Stream<Integer> 반환
//            .noneMatch(p -> candidate % p == 0);
//  }

  // 소수인지 확인하는 Predicate, 중간 결과 리스트를 전달받을 수 있음
  public static boolean isPrime(List<Integer> primes, int candidate) {
    double candidateRoot = (int) Math.sqrt(candidate);
    return primes.stream()
            .takeWhile(i -> i <= candidateRoot)
            .noneMatch(i -> candidate % i == 0);
  }

  // 소수와 비소수 구분
  public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
    return IntStream.rangeClosed(2, n)  // IntStream 반환
            .boxed()  // Stream<Integer> 반환
            .collect(new PrimeNumbersCustomCollector());
  }


  // 리스트와 Predicate 를 인수로 받아서 리스트의 첫 요소에서 시작해서 Predicate 를 만족하는 가장 긴 요소로 이루어진 리스트 반환
//  public static <A> List<A> customTakeWhile(List<A> list, Predicate<A> p) {
//    int i = 0;    // i -> i <= 2
//    for (A item: list) {  // 2,3,5
//      if (!p.test(item)) {
//        // 리스트의 현재 요소가 Predicate 를 만족하지 않으면 검사한 항목의 앞쪽에 위치한 서브리스트 반환
//        return list.subList(0, i);
//      }
//      i++;
//    }
//    return list;
//  }
}
