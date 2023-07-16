package com.assu.study.mejava8.chap06;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Grouping {
  enum CaloricLevel { DIET, NOMAL, FAT }
  public static void main(String[] args) {

    System.out.println(groupByType());
    // {MEAT=[pork, beef, chicken], FISH=[prawns, salmon], OTHER=[french fries, rice, season fruit, pizza]}

    System.out.println(groupByCaloricLevel());
    // {FAT=[pork], NOMAL=[beef, french fries, pizza, salmon], DIET=[chicken, rice, season fruit, prawns]}

    System.out.println(groupByTypeAndCaloricLevel());
    // {
    // MEAT={FAT=[pork], NOMAL=[beef], DIET=[chicken]},
    // FISH={NOMAL=[salmon], DIET=[prawns]},
    // OTHER={NOMAL=[french fries, pizza], DIET=[rice, season fruit]}
    // }


    System.out.println(countInGroups());
    //{MEAT=3, FISH=2, OTHER=4}


    System.out.println(maxCaloricByType());
    // {MEAT=Optional[pork], FISH=Optional[salmon], OTHER=Optional[pizza]}

    System.out.println(maxCaloricByTypeWithoutOptional());
    // {MEAT=pork, FISH=salmon, OTHER=pizza}

    System.out.println(maxCaloricByTypeWithReduce());
    // {MEAT=pork, FISH=salmon, OTHER=pizza}


    System.out.println(sumCaloriesByType());
    // {MEAT=1900, FISH=850, OTHER=1550}

    System.out.println(caloricLevelsByType());
    // {MEAT=[FAT, NOMAL, DIET], FISH=[NOMAL, DIET], OTHER=[NOMAL, DIET]}
  }

  // 타입으로 분류 (메서드 레퍼런스)
  public static Map<Dish.Type, List<Dish>> groupByType() {
    return Dish.menu.stream()
            .collect(groupingBy(Dish::getType));  // Dish.Type 과 일치하는 모든 요리를 추출하는 함수를 groupingBy 로 전달
  }

  // 람다 표현식으로 분류
  public static Map<CaloricLevel, List<Dish>> groupByCaloricLevel() {
    return Dish.menu.stream()
            .collect(
                    groupingBy(dish -> {
                      if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                      } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NOMAL;
                      } else {
                        return CaloricLevel.FAT;
                      }
                    })
            );
  }

  // 타입과 칼로리레벨로 다수준 그룹화
  public static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupByTypeAndCaloricLevel() {
    return Dish.menu.stream().collect(
            groupingBy(Dish::getType, // 첫 번째 수준의 분류 함수 (두 번째 수준의 분류 함수를 두 번째 인자로 받음)
                    groupingBy(dish -> {  // 두 번째 수준의 분류 함수
                      if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                      } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NOMAL;
                      } else {
                        return CaloricLevel.FAT;
                      }
                    }))
    );
  }

  // 그룹화 요소 갯수
  public static Map<Dish.Type, Long> countInGroups() {
    return Dish.menu.stream().collect(groupingBy(Dish::getType, counting()));
  }

  // 타입별로 가장 높은 칼로리의 요리 찾기
  public static Map<Dish.Type, Optional<Dish>> maxCaloricByType() {
    return Dish.menu.stream().collect(
            groupingBy(Dish::getType, maxBy(comparing(Dish::getCalories))));
  }

  // 타입별로 가장 높은 칼로리, Optional 없이
  public static Map<Dish.Type, Dish> maxCaloricByTypeWithoutOptional() {
    return Dish.menu.stream().collect(
            groupingBy(Dish::getType, // 분류 함수
                    collectingAndThen(
                            maxBy(comparingInt(Dish::getCalories)), // 감싸인 컬렉터
                            Optional::get // 변환 함수
                    )
    ));
  }

  // 타입별로 가장 높은 칼로리, maxBy 대신 reduce 로
  public static Map<Dish.Type, Dish> maxCaloricByTypeWithReduce() {
    return Dish.menu.stream().collect(
            groupingBy(Dish::getType,
                    collectingAndThen(
                            reducing(((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1:d2)),
                            Optional::get
                    ))
    );
  }

  // 타입별 칼로리 합
  public static Map<Dish.Type, Integer> sumCaloriesByType() {
    return Dish.menu.stream().collect(
            groupingBy(Dish::getType,
                        summingInt(Dish::getCalories))
    );
  }

  // 타입별 CaloricLevel 찾기
  public static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
    return Dish.menu.stream().collect(
            groupingBy(Dish::getType, mapping(dish -> {
              if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
              } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NOMAL;
              } else {
                return CaloricLevel.FAT;
              }
            },
            //toSet()
            toCollection(HashSet::new)
            )));
  }
}
