package com.assu.study.mejava8.chap04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamVsCollection {
  public static void main(String[] args) {
    List<String> words = Arrays.asList("abc", "de", "fgh");
    Stream<String> s = words.stream();

    s.forEach(System.out::println);
    // java.lang.IllegalStateException: stream has already been operated upon or closed 발생
    //s.forEach(System.out::println);


    // 외부 반복
    List<String> names = new ArrayList<>();
    for (Dish d: Dish.menu) {
      names.add(d.getName());
    }

    // 내부 반복
    List<String> names2 = Dish.menu.stream()
            .map(Dish::getName) // map 메서드를 getName 메서드로 파라메터화해서 이름 추출
            .collect(toList()); // 파이프라인 실행, 반복자 필요없음
  }
}
