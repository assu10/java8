package com.assu.study.mejava8.chap05;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Mapping {
  public static void main(String[] args) {

    // 각 요소에 함수 적용 - 요리명 추출
    List<String> dishNames = Dish.menu.stream()
            .map(Dish::getName)
            .collect(toList());

    // [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
    System.out.println(dishNames);

    // 각 요소에 함수 적용 - 요리명의 길이 추출
    List<Integer> dishNameLength = Dish.menu.stream()
            .map(Dish::getName)
            .map(String::length)
            .collect(toList());

    // [4, 4, 7, 12, 4, 12, 5, 6, 6]
    System.out.println(dishNameLength);


    // 스트림 평면화
    List<String> words = Arrays.asList("hello", "world");

    // map() 이 Stream<String[]> 을 반환하기 때문에 오류
//    List<String> first = words.stream() // Stream<String> 반환
//            .map(word -> word.split(""))  // Stream<String[]> 반환
//            .distinct()
//            .collect(toList());

//    String[] strArray = {"hello", "world"};
//    Stream<String> streamOfWords = Arrays.stream(strArray);

    // Arrays.stream() 으로 고유 문자 추출
    // map(Arrays::stream) 이 Stream<Stream<String>> 반환하기 때문에 오류
//    List<String> first = words.stream() // Stream<String> 반환
//            .map(word -> word.split(""))  // Stream<String[]> 반환
//            .map(Arrays::stream)  // Stream<Stream<String>> 반환
//            .distinct()
//            .collect(toList());


    // flatMap() 으로 고유 문자 추출
    List<String> first = words.stream() // Stream<String> 반환
            .map(word -> word.split(""))  // Stream<String[]> 반환
            .flatMap(Arrays::stream)  // Stream<String> 반환, 생성된 스트림을 하나의 스트림으로 평면화
            .distinct()
            .collect(toList());

    // [h, e, l, o, w, r, d]
    System.out.println(first);


    // 퀴즈) 숫자 리스트가 있을 때 각 숫자의 제곱근으로 이루어진 리스트 구하기
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
    List<Integer> squared = numbers.stream()
            .map(n -> n*n)
            .collect(toList());

    //System.out.println(squared);

    // 퀴즈) 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트 반환
    // [1,2,3], [3,4] 가 있으면 [(1,3), (1,4), (2,3), (2,4), (3,3), (3,4)] 반환
    List<Integer> number1 = Arrays.asList(1, 2, 3);
    List<Integer> number2 = Arrays.asList(3, 4);
    List<int[]> pairs = number1.stream()  // Stream<Integer> 반환
            .flatMap(i -> number2.stream()
                    .map(j -> new int[]{i,j}))  // Stream<int[]> 반환
            .collect(toList());

    //pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));


    // 위 퀴즈에서 합이 3으로 나누어 떨어지는 쌍만 반환
    // (2,4), (3,3)
    List<int[]> pairs2 = number1.stream() // Stream<Integer> 반환
            .flatMap(i -> number2.stream()
                    .filter(j -> (i+j)%3 == 0)
                    .map(j -> new int[]{i,j}))  // Stream<int[]> 반환
            .collect(toList());
    pairs2.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
  }
}
