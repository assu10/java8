package com.assu.study.mejava8.chap08;

public class StrategyMain {
  public static void main(String[] args) {
    StrategyValidator v1 = new StrategyValidator(new StrategyIsNumericValidation());
    System.out.println("isNumeric: " + v1.validate("aaa")); // false

    StrategyValidator v2 = new StrategyValidator(new StrategyIsAllLowerCaseValidation());
    System.out.println("isAllLowerCase: " + v2.validate("aaa")); // true

    // 구현 클래스 없이 람다 표현식 바로 사용
    StrategyValidator v3 = new StrategyValidator(s -> s.matches("\\d+"));
    System.out.println("isNumeric: " + v3.validate("aaa")); // false

    StrategyValidator v4 = new StrategyValidator(s -> s.matches("[a-z]+"));
    System.out.println("isAllLowerCase: " + v4.validate("aaa")); // false

  }
}
