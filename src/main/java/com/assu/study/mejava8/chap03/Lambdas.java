package com.assu.study.mejava8.chap03;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lambdas {
  public static void main(String[] args) {
    // 람다 사용
    Runnable r1 = () -> System.out.println("Runnable 1~");


    // 익명 클래스 사용
    Runnable r2 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Runnable 2~");
      }
    };

    process(r1);
    process(r2);


    // 람다 표현식 직접 전달
    process(() -> System.out.println("Runnable 3~"));

    int a = 1;
    Runnable r4 = () -> System.out.println(a);
    a = 2;
  }
  public static void process(Runnable r) {
    r.run();
  }
}
