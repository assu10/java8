package com.assu.study.mejava8.chap09;

public interface Rotatable {
  void setRotationAngle(int angleInDegrees);
  int getRotationAngle();

  // 기본 구현
  default void rotateBy(int angleInDegrees) {
    setRotationAngle((getRotationAngle() + angleInDegrees) % 360);
  }
}
