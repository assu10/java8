package com.assu.study.mejava8.chap09;

public class C_1 implements A, B_1 {
  public static void main(String[] args) {
    new C_1().hello();
  }

  @Override
  public void hello() {
    B_1.super.hello();
  }
}
