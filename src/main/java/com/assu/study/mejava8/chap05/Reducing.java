package com.assu.study.mejava8.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(3,4,5,1,2);

    // reduce() 를 이용하여 모든 요소 더하기
    int sum = numbers.stream().reduce(0, (a,b) -> a+b);
    // 15
    System.out.println(sum);

    // reduce() 를 이용하여 모든 요소 곱하기
    int product = numbers.stream().reduce(1, (a,b) -> a*b);
    // 120
    System.out.println(product);

    // 메서드 레퍼런스로 더 간결하게
    int sum2 = numbers.stream().reduce(0, Integer::sum);
    // 15
    System.out.println(sum2);

    // 최대값
    Optional<Integer> max = numbers.stream().reduce(Integer::max);
    int max2 = numbers.stream().reduce(0, (a,b) -> Integer.max(a,b));
    // Optional[5]
    System.out.println(max);
    // 5
    System.out.println(max2);

    Optional<Integer> min = numbers.stream().reduce(Integer::min);
    Optional<Integer> min2 = numbers.stream().reduce((a,b) -> a<b ? a : b);
    // Optional[1]
    System.out.println(min);
    // Optional[1]
    System.out.println(min2);


    // 퀴즈. map() 과 reduce() 로 요리 개수 계산
    int dishNumbers = Dish.menu.stream()  // Stream<Dish> 반환
            .map(d -> 1)  // Stream<Integer> 반환
            .reduce(0, (a,b) -> a+b);
    System.out.println(dishNumbers);

    long dishNumbers2 = Dish.menu.stream().count();
    System.out.println(dishNumbers2);
  }
}
