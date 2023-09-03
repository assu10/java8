package com.assu.study.mejava8.chap10;

import java.util.Optional;

public class PersonMain {
  public static void main(String[] args) {
    //Person p = new Person();
    //getCarInsuranceName(p);


    // 빈 Optional 생성
    Optional<Car> optCar = Optional.empty();

    // null 이 아닌 값으로 Optional 생성
    Car car = null;
    //Optional<Car> optCar2 = Optional.of(car);

    // null 값으로 Optional 생성
    Optional<Car> optCar3 = Optional.ofNullable(car);

    // Optional.empty
    System.out.println(optCar3);
  }

//  public static String getCarInsuranceName(Person person) {
//    return person.getCar().getInsurance().getName();
//  }

//  public static String getCarInsuranceName2(Person person) {
//    if (person != null) {
//      Car car = person.getCar();
//      if (car != null) {
//        Insurance insurance = car.getInsurance();
//        if (insurance != null) {
//          return insurance.getName();
//        }
//      }
//    }
//    return "Null";
//  }

//  public static String getCarInsuranceName3(Person person) {
//    if (person == null) {
//      return "Null";
//    }
//
//    Car car = person.getCar();
//    if (car == null) {
//      return "Null";
//    }
//
//    Insurance insurance = car.getInsurance();
//    if (insurance == null) {
//      return "Null";
//    }
//
//    return insurance.getName();
//  }
}
