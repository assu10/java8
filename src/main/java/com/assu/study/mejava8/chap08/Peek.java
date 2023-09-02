package com.assu.study.mejava8.chap08;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Peek {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(2,3,4,5);

    // 20 22
//    numbers.stream()
//            .map(x -> x + 17)
//            .filter(x -> x%2 == 0)
//            .limit(3)
//            .forEach(System.out::println);

    List<Integer> result = numbers.stream()
            .peek(x -> System.out.println("from stream: " + x)) // 소스에서 처음 소비한 요소 출력
            .map(x -> x + 17)
            .peek(x -> System.out.println("after map: " + x)) // map() 실행 결과 출력
            .filter(x -> x%2 == 0)
            .peek(x -> System.out.println("after filter: " + x))  // filter() 실행 결과 출력
            .limit(3)
            .peek(x -> System.out.println("after limit: " + x)) // limit() 실행 결과 출력
            .collect(Collectors.toList());

    System.out.println(result); // [20, 22]
  }
}
