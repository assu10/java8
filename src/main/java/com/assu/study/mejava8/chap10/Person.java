package com.assu.study.mejava8.chap10;

import java.util.Optional;

public class Person {
  // 차가 있을 수도 있고 없을 수도 있다.
  private Optional<Car> car;

  public Optional<Car> getCar() {
    return car;
  }

//  public Optional<Car> getCarAsOptional() {
//    return Optional.ofNullable(car);
//  }
}
