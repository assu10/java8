package com.assu.study.mejava8.chap05;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Quiz2 {
  public static void main(String[] args) {
    // 피보나치 수열 0,1,1,2,3,5,8,13,21,...

    // 1. Stream.iterate() 로 피보나치수열 구하기

    // 집합 만들기
    //int[] a = new int[]{0,1};

    // 피보나치 수열 집합 (Stream.iterate())
    Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0]+t[1]})
            .limit(20)
            .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

    // 피보나치 수열 (Stream.iterate())
    List<Integer> fib = Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0]+t[1]})
            .limit(10)
            .map(t -> t[0])
            .collect(toList());

    // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
    System.out.println(fib);


    // 피보나치 수열 (Stream.generate)
    IntSupplier fib2 = new IntSupplier() {
      private int previous = 0;
      private int current = 1;
      @Override
      public int getAsInt() { // 객체 상태가 바뀌며 새로운 값 생상d
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;

        return oldPrevious;
      }
    };

    IntStream fib2Stream = IntStream.generate(fib2)
            .limit(10);

    fib2Stream.forEach(System.out::println);
  }
}
