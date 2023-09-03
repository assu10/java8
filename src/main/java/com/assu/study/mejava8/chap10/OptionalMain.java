package com.assu.study.mejava8.chap10;

import java.util.Optional;

public class OptionalMain {
  public static void main(String[] args) {

//    Insurance insurance = new Insurance();
//    Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
//    Optional<String> name = optInsurance.map(Insurance::getName);

    Insurance in = null;
    Optional<Insurance> optInsurance = Optional.ofNullable(in);
    Optional<String> name = optInsurance.map(Insurance::getName);

    // Optional.empty
    System.out.println(name.orElse("test"));

    Person person = new Person();
    Optional<Person> optPerson = Optional.of(person);
//    Optional<String> name = optPerson
//            .map(Person::getCar)  // Optional<Optional<Car>> 반환
//            .map(Car::getInsurance) // Optional<U> 반환
//            .map(Insurance::getName);

//    Optional<String> name = optPerson
//            .flatMap(Person::getCar)
//            .flatMap(Car::getInsurance)
//            .map(Insurance::getName);

    //String name2 = getCarInsuranceName(optPerson);
    //System.out.println(name2);

  }

  public static String getCarInsuranceName(Optional<Person> optPerson) {
    return optPerson.flatMap(Person::getCar) // Optional<Car> 반환
            .flatMap(Car::getInsurance) // Optional<Insurance> 반환
            .map(Insurance::getName)  // Optional<String> 반환
            .orElse("Unknown");
  }

//  public String getCarInsuranceName(Person person) {
//    return person.getCar().getInsurance().getName();
//  }

//  public String getCarInsuranceName(Person person) {
//    return person.getCar().getInsurance().getName();
//  }
}
