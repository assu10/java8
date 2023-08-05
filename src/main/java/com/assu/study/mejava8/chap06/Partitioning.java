package com.assu.study.mejava8.chap06;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Partitioning {
  public static void main(String[] args) {
    Map<Boolean, List<Dish>> partitionMenu = partitioningByVegetarian();
    System.out.println(partitionMenu);
    // {false=[pork, beef, chicken, prawns, salmon], true=[french fries, rice, season fruit, pizza]}

    // 위에서 채식자만 분류
    List<Dish> vegetarian = partitionMenu.get(true);
    System.out.println(vegetarian);
    // [french fries, rice, season fruit, pizza]

    List<Dish> notVegetarian = partitionMenu.get(false);
    System.out.println(notVegetarian);
    // [pork, beef, chicken, prawns, salmon]


    System.out.println(vegetarianByFilter());
    // [french fries, rice, season fruit, pizza]

    System.out.println(vegetarianByType());
    // {false={FISH=[prawns, salmon], MEAT=[pork, beef, chicken]}, true={OTHER=[french fries, rice, season fruit, pizza]}}

    System.out.println(mostCaloricPartitioningBy());
    // {false=pork, true=pizza}

    System.out.println(partitioningByVegetarianAndCaloric());
    // {false={false=[chicken, prawns, salmon], true=[pork, beef]}, true={false=[rice, season fruit], true=[french fries, pizza]}}

    System.out.println(partitioningByVegetarianCount());
    // {false=5, true=4}
  }

  // partitioningBy() 사용하여 분류
  private static Map<Boolean, List<Dish>> partitioningByVegetarian() {
    return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
  }

  // 필터로 채식 확인
  private static List<Dish> vegetarianByFilter() {
    return Dish.menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
  }

  // 채식/비채식 스트림을 각각 요리 타입으로 그룹화
  private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianByType() {
    return Dish.menu.stream().collect(
            partitioningBy(Dish::isVegetarian,  // 분할 함수
                          groupingBy(Dish::getType) // 두 번째 컬렉터
            )
    );
  }

  // 채식/비채식 중 가장 높은 칼로리
  private static Map<Boolean, Dish> mostCaloricPartitioningBy() {
    return Dish.menu.stream().collect(
            partitioningBy(Dish::isVegetarian,
                    collectingAndThen(
                            maxBy(Comparator.comparingInt(Dish::getCalories)),
                            Optional::get
                    )
    ));
  }

  // 채식/비채식 중 칼로리가 500 이상인 요리
  private static Map<Boolean, Map<Boolean, List<Dish>>> partitioningByVegetarianAndCaloric() {
    return Dish.menu.stream().collect(
            partitioningBy(Dish::isVegetarian,
                    partitioningBy(d -> d.getCalories() > 500))
    );
  }

  // 채식/비채식 각 항목의 개수
  private static Map<Boolean, Long> partitioningByVegetarianCount() {
    return Dish.menu.stream().collect(
            partitioningBy(Dish::isVegetarian, counting())
    );
  }
}
