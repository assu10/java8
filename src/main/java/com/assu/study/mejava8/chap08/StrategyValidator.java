package com.assu.study.mejava8.chap08;

// 검증 전략 활용
public class StrategyValidator {
  private final StrategyValidation strategy;

  public StrategyValidator(StrategyValidation v) {
    this.strategy = v;
  }

  public boolean validate(String s) {
    return strategy.execute(s);
  }
}
