package com.assu.study.mejava8.chap05;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {
  public static void main(String[] args) {
    int sum = Dish.menu.stream()  // Stream<Dish> 반환
            .map(Dish::getCalories) // Stream<Integer> 반환
            .reduce(0, Integer::sum);
    System.out.println(sum);


    // 기본형 특화 스트림
    int sum2 = Dish.menu.stream() // Stream<Dish> 반환
            .mapToInt(Dish::getCalories)  // IntStream 반환
            .sum(); // int 반환
    System.out.println(sum2);


    // 객체 스트림으로 복원
    IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories); // Stream 을 IntStream 으로 변환
    Stream<Integer> stream = intStream.boxed(); // IntStream 을 Stream<T> 로 변환


    // OptionalInt
    OptionalInt maxCalorie = Dish.menu.stream() // Stream<Dish> 반환
            .mapToInt(Dish::getCalories)  // IntStream 반환
            .max(); // OptionalInt 반환

    // 최대값이 없으면 1 리턴
    int max = maxCalorie.orElse(1);


    // 숫자 범위
    // 1~100 까지의 짝수 스트림
    IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n%2 == 0);
    long evenCount = evenNumbers.count();

    // 50
    System.out.println(evenCount);
  }
}

