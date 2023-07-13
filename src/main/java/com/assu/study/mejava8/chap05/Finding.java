package com.assu.study.mejava8.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Finding {
  public static void main(String[] args) {
    if (isVegetarian()) {
      System.out.println("Vegetarian");
    }

    if (isHealthy()) {
      System.out.println("healthy");
    }

    if (isHealthy2()) {
      System.out.println("healthy2");
    }

    Optional<Dish> dish = findVegetarian();
    dish.ifPresent(d -> System.out.println(d.getName())); // 값이 있으면 출력하고 없으면 아무일도 일어나지 않음


    // 3으로 나누어 떨어지는 첫 번째 제곱값을 반환
    List<Integer> numbers = Arrays.asList(1, 2, 9, 3, 4, 5);
    Optional<Integer> firstSquareDivisibleByThree = numbers.stream()
            .map(n -> n*n)
            .filter(n -> n%3 == 0)
            .findFirst();

    // Optional[81]
    System.out.println(firstSquareDivisibleByThree);
  }

  // 적어도 한 요소와 일치하는지 확인
  private static boolean isVegetarian() {
    return Dish.menu.stream().anyMatch(Dish::isVegetarian);
  }

  // 모든 요소와 일치하는지 확인
  private static boolean isHealthy() {
    return Dish.menu.stream().allMatch(d -> d.getCalories() < 1000);
  }

  // 모든 요소와 일치하지 않는지 확인
  private static boolean isHealthy2() {
    return Dish.menu.stream().noneMatch(d -> d.getCalories() >= 1000);
  }

  // 현재 스트림에서 임의의 요소 반환
  private static Optional<Dish> findVegetarian() {
    // 쇼트서킷을 이용해서 결과를 찾는 즉시 실행 종료
    return Dish.menu.stream().filter(Dish::isVegetarian).findAny();
  }
}
