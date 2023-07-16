package com.assu.study.mejava8.chap06;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class Summarizing {
  public static void main(String[] args) {
    System.out.println(calculateCount()); // 9
    System.out.println(calculateCount2()); // 9

    // Optional[Dish{name='pork', vegetarian=false, calories=800, type=MEAT}]
    System.out.println(calculateMaxCalorie());
    // Optional[Dish{name='pork', vegetarian=false, calories=800, type=MEAT}]
    System.out.println(calculateMaxCalorieByReducing());

    // Optional[Dish{name='season fruit', vegetarian=true, calories=120, type=OTHER}]
    System.out.println(calculateMinCalorie());

    System.out.println(calculateTotalCalories()); // 4300
    System.out.println(calculateTotalCaloriesByReducing()); // 4300
    System.out.println(calculateTotalCaloriesByReducingAndMethodReference()); // 4300
    System.out.println(calculateTotalCaloriesWithoutCollectors()); // 4300
    System.out.println(calculateTotalCaloriesBySum()); // 4300


    System.out.println(calculateAverageCalories()); // 477.77..
    System.out.println(calculateStatistics()); // IntSummaryStatistics{count=9, sum=4300, min=120, average=477.777778, max=800}

    // pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon
    System.out.println(getMenuString());

  }

  private static Long calculateCount() {
    return Dish.menu.stream().collect(counting());
  }

  private static Long calculateCount2() {
    return Dish.menu.stream().collect(counting());
  }

  private static Optional<Dish> calculateMaxCalorie() {
    // 칼로리를 비교할 Comparator 구현
    Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

    // Collectors.maxBy 로 Comparator 전달
    Optional<Dish> maxCalorieDish = Dish.menu.stream().collect(maxBy(dishCaloriesComparator));

    return maxCalorieDish;
  }

  private static Optional<Dish> calculateMaxCalorieByReducing() {
    return Dish.menu.stream()
            .collect(reducing((r1, r2) -> r1.getCalories() > r2.getCalories() ? r1 : r2));
  }


  private static Optional<Dish> calculateMinCalorie() {
    // 칼로리를 비교할 Comparator 구현
    Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

    // Collectors.maxBy 로 Comparator 전달
    Optional<Dish> maxCalorieDish = Dish.menu.stream().collect(minBy(dishCaloriesComparator));

    return maxCalorieDish;
  }

  // summingInt() 컬렉터로 합계 구하기
  private static int calculateTotalCalories() {
    return Dish.menu.stream().collect(summingInt(Dish::getCalories));
  }

  // reducing() 컬렉터와 람다 표현식으로 합계 구하기
  private static int calculateTotalCaloriesByReducing() {
    return Dish.menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i+j));
  }

  // reducing() 컬렉터와 메서드 레퍼런스로 합계 구하기
  private static int calculateTotalCaloriesByReducingAndMethodReference() {
    return Dish.menu.stream().collect(reducing(
            0 // 초기값
            , Dish::getCalories // 변환 함수
            , Integer::sum  // 합계 함수
    ));
  }

  // 컬렉터를 이용하지 않고 합계 구하기
  private static int calculateTotalCaloriesWithoutCollectors() {
    return Dish.menu.stream() // Stream<Dish> 반환
            .map(Dish::getCalories) // Stream<Integer> 반환
            .reduce(Integer::sum) // Optional<Integer> 반환
            .get(); // Integer 반환
  }

  // sum() 으로 합계 구하기
  private static int calculateTotalCaloriesBySum() {
    return Dish.menu.stream() // Stream<Dish> 반환
            .mapToInt(Dish::getCalories)  // IntStream 반환
            .sum();
  }

  private static Double calculateAverageCalories() {
    return Dish.menu.stream().collect(averagingInt(Dish::getCalories));
  }

  private static IntSummaryStatistics calculateStatistics() {
    return Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
  }

  private static String getMenuString() {
    return Dish.menu.stream().map(Dish::getName).collect(joining(", "));
  }

}