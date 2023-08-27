package com.assu.study.mejava8.chap08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Refactoring {
  public static void main(String[] args) {

    // 익명 클래스 사용
    Runnable r1 = new Runnable() {
      @Override
      public void run() {
        System.out.println("익명 클래스 사용");
      }
    };

    // 람다 사용
    Runnable r2 = () -> System.out.println("람다 사용");

    process(r1);
    process(r2);

    // 람다 표현식을 직접 전달
    process(() -> System.out.println("람다 표현식 직접 전달"));


    // 필터링과 추출을 명령형 코드로 구현
    List<String> dishNames = new ArrayList<>();
    for (Dish dish: Dish.menu) {
      if (dish.getCalories() > 300) {
        dishNames.add(dish.getName());
      }
    }

    // [pork, beef, chicken, french fries, rice, pizza, prawns, salmon]
    System.out.println(dishNames);

    // 필터링과 추출을 스트림 API 로 구현
    List<String> dishNames2 = Dish.menu.stream()
            .filter(d -> d.getCalories() > 300)
            .map(Dish::getName)
            .collect(Collectors.toList());

    // [pork, beef, chicken, french fries, rice, pizza, prawns, salmon]
    System.out.println(dishNames2);
  }

  public static void process(Runnable r) {
    r.run();
  }
}
