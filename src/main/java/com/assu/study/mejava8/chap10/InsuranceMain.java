package com.assu.study.mejava8.chap10;

import java.util.Optional;

public class InsuranceMain {
  public static void main(String[] args) {

  }

  public Optional<Insurance> nullSafeCheapestInsurance(Optional<Person> person, Optional<Car> car) {
    if (person.isPresent() && car.isPresent()) {
      return Optional.of(findCheapestInsurance(person.get(), car.get()));
    } else {
      return Optional.empty();
    }
  }

  public Optional<Insurance> nullSafeCheapestInsurance2(Optional<Person> person, Optional<Car> car) {
    return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
  }

  public Insurance findCheapestInsurance(Person person, Car car) {
    Insurance cheapestCompany = new Insurance();
    return cheapestCompany;
  }
}
