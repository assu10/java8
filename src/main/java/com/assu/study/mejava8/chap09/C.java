package com.assu.study.mejava8.chap09;

public class C implements A, B {
  public static void main(String[] args) {
    // hello from B
    new C().hello();
  }
}
