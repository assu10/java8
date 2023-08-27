package com.assu.study.mejava8.chap08;

// 모두 숫자인지 확인하는 구현 클래스
public class StrategyIsNumericValidation implements StrategyValidation {
  @Override
  public boolean execute(String s) {
    return s.matches("\\d+");
  }
}
