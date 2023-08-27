package com.assu.study.mejava8.chap08;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class LambdaToMethodReference {


  public static void main(String[] args) {
    // 람다 표현식 사용
    Map<Dish.CaloricLevel, List<Dish>> dishesByCaloricLevel = groupByCaloricLevel();

    // {NORMAL=[beef, french fries, pizza, salmon], DIET=[chicken, rice, season fruit, prawns], FAT=[pork]}
    System.out.println(dishesByCaloricLevel);

    // 메서드 레퍼런스 사용
    Map<Dish.CaloricLevel, List<Dish>> dishByCaloricLevel2 = Dish.menu.stream()
            .collect(
                    groupingBy(Dish::getCaloricLevel)
            );

    // {NORMAL=[beef, french fries, pizza, salmon], DIET=[chicken, rice, season fruit, prawns], FAT=[pork]}
    System.out.println(dishByCaloricLevel2);


    // 람다 + 저수준 리듀싱 조합
    int totalCalories = Dish.menu.stream()
            .map(Dish::getCalories)
            .reduce(0, (c1, c2) -> c1+c2);

    // 4300
    System.out.println(totalCalories);


    // 메서드 레퍼런스 + Collectors API 조합
    int totalCalories2 = Dish.menu.stream().collect(summingInt(Dish::getCalories));

    // 4300
    System.out.println(totalCalories2);
  }

  public static Map<Dish.CaloricLevel, List<Dish>> groupByCaloricLevel() {
    return Dish.menu.stream()
            .collect(
                    groupingBy(dish -> {
                      if (dish.getCalories() <= 400) {
                        return Dish.CaloricLevel.DIET;
                      } else if (dish.getCalories() <= 700) {
                        return Dish.CaloricLevel.NORMAL;
                      } else {
                        return Dish.CaloricLevel.FAT;
                      }
                    })
            );
  }
}
