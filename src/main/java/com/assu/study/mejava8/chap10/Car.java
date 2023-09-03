package com.assu.study.mejava8.chap10;

import java.util.Optional;

public class Car {
  // 보험이 있을 수도 있고 없을 수도 있다.
  private Optional<Insurance> insurance;

  public Optional<Insurance> getInsurance() {
    return insurance;
  }
}
