package com.assu.study.mejava8.chap09;

public interface Movable {
  int getX();
  int getY();
  void setX(int x);
  void setY(int y);

  default void moveHorizontally(int distance) {
    setX(getX() + distance);
  }

  default void moveVertically(int distance) {
    setX(getY() + distance);
  }
}
