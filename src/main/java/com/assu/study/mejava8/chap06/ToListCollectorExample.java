package com.assu.study.mejava8.chap06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToListCollectorExample {
  public static void main(String[] args) {
    List<Dish> dishes = Dish.menu.stream().collect(new ToListCollector<Dish>());
    List<Dish> dishes2 = Dish.menu.stream().collect(Collectors.toList());

    // [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
    System.out.println(dishes);
    System.out.println(dishes2);

    // 커스텀 컬렉터 구현하지 않고 커스텀하게 데이터 수집
    List<Dish> dishes3 = Dish.menu.stream().collect(
            ArrayList::new, // supplier d
            List::add,  // accumulator
            List::addAll  // combiner
    );
    // [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
    System.out.println(dishes3);
  }
}
