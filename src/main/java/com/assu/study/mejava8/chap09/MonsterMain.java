package com.assu.study.mejava8.chap09;

public class MonsterMain {
  public static void main(String[] args) {
    Monster m = new Monster();
    m.rotateBy(180);
    m.moveVertically(10);

    System.out.println(m);
  }
}
