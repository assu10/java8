package com.assu.study.mejava8.chap09;

public interface B extends A {
  default void hello() {
    System.out.println("hello from B");
  }
}
