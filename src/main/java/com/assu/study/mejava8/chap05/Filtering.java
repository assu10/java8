package com.assu.study.mejava8.chap05;

import java.util.List;
import java.util.stream.Collectors;

public class Filtering {
  public static void main(String[] args) {
    // Predicate 로 필터링
//    List<Dish> vegetarian = Dish.menu.stream()
//            .filter(Dish::isVegetarian) // 채식인지 확인하는 메서드 레퍼런스
//            .collect(toList());
//
////    Dish{name='french fries', vegetarian=true, calories=530, type=OTHER}
////    Dish{name='rice', vegetarian=true, calories=350, type=OTHER}
////    Dish{name='season fruit', vegetarian=true, calories=120, type=OTHER}
////    Dish{name='pizza', vegetarian=true, calories=550, type=OTHER}
//    vegetarian.forEach(System.out::println);


    // 고유 요소 필터링
//    List<Integer> numbers = Arrays.asList(1,2,3,4,7,5,8,2);
//    numbers.stream()
//          .filter(i -> i % 2 == 0) // 짝수 필터링
//          .distinct() // 중복 제거
//          .forEach(System.out::print);  // 248


    // 스트림 축소
//    List<Dish> dishes = Dish.menu.stream()
//            .filter(d -> d.getCalories() > 500)
//            .limit(3)
//            .collect(toList());
//
////    Dish{name='pork', vegetarian=false, calories=800, type=MEAT}
////    Dish{name='beef', vegetarian=false, calories=700, type=MEAT}
////    Dish{name='french fries', vegetarian=true, calories=530, type=OTHER}
//    dishes.forEach(System.out::println);


    // 요소 건너뛰기
//    List<Dish> dishes = Dish.menu.stream()
//            .filter(d -> d.getCalories() < 710)
//            .skip(2)
//            .collect(toList());
//
//    dishes.forEach(System.out::println);

    List<Dish> meats = Dish.menu.stream()
            . filter(d -> d.getType().equals(Dish.Type.MEAT))
            .limit(2)
            .collect(Collectors.toList());

    meats.forEach(System.out::println);

  }
}
