package com.assu.study.mejava8.chap04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {
  public static void main(String[] args) {
    // [season fruit, rice]
    System.out.println(getLowCaloricDishesNamesInJava7(Dish.menu));

    // season fruit
    // rice
    //getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

    //getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    //getLowCaloricDishesNamesInJava82(Dish.menu).forEach(System.out::println);
    System.out.println(getLowCaloricDishesNamesInJava83(Dish.menu));
  }

  // Java7 로 저칼로리 요리의 요리명을 다시 칼로리 기준으로 정렬
  public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
    List<Dish> lowCaloricDishes = new ArrayList<>();  // 가비지 변수, 컨테이너 역할만 하는 중간 변수

    for (Dish d: dishes) {
      if (d.getCalories() < 400) {  // 저칼로리 필터링
        lowCaloricDishes.add(d);
      }
    }

    // 익명 클래스로 요리 정렬
    Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
      @Override
      public int compare(Dish o1, Dish o2) {
        return Integer.compare(o1.getCalories(), o2.getCalories()); // 오름차순으로 정렬
      }
    });

    List<String> lowCaloricDishesName = new ArrayList<>();

    // 정렬된 요리에서 이름 추출
    for (Dish d: lowCaloricDishes) {
      lowCaloricDishesName.add(d.getName());
    }

    return lowCaloricDishesName;
  }

  // Java8 로 저칼로리 요리의 요리명을 다시 칼로리 기준으로 정렬
  public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
    return dishes.stream()  // 스트림 얻음
            .filter(d -> d.getCalories() < 400) // 저칼로리 필터링
            .sorted(comparing(Dish::getCalories)) // 오름차순으로 정렬
            .map(Dish::getName) // 정렬된 요리에서 이름 추출
            .collect(toList()); // 요리명을 리스트로 변환
  }

  // 중간 연산과 최종 연산
  public static List<String> getLowCaloricDishesNamesInJava82(List<Dish> dishes) {
    return dishes.stream()  // 스트림 얻음
            .filter(d -> d.getCalories() > 100) // 중간 연산
            .map(Dish::getName) // 중간 연산
            .limit(3) // 중간 연산
            .collect(toList()); // 최종 연산
  }

  // 중간 연산 과정
  public static List<String> getLowCaloricDishesNamesInJava83(List<Dish> dishes) {
    return dishes.stream()
            .limit(3) // 중간 연산
            .filter(d -> {
              System.out.println("filter: "+ d.getName());
              return d.getCalories() < 500;
            }) // 중간 연산
            .map(d -> {
              System.out.println("map: "+ d.getName());
              return d.getName();
            }) // 중간 연산

            .collect(toList()); // 최종 연산
  }
}
