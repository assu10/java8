package com.assu.study.mejava8.chap09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Intro {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1,3,5,2,4);
    // sort() 는 디폴트 메서드
    // naturalOrder() 는 static 메서드
    numbers.sort(Comparator.naturalOrder());

    Iterator a = new Iterator() {
      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public Object next() {
        return null;
      }
    };
    a.remove();

    // [1, 2, 3, 4, 5]
    System.out.println(numbers);
  }
}
