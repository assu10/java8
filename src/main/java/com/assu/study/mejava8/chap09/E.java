package com.assu.study.mejava8.chap09;

public class E extends D implements A, B {
  public static void main(String[] args) {
    // hello from B
    new E().hello();
  }
}
