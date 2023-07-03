package com.assu.study.mejava8.chap03;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

@SpringBootApplication
public class FunctionalInterfaces {
  public static void main(String[] args) {
    String[] strings = {"abcde", "", "ds3b"};
    List<String> listOfStrings = Arrays.asList(strings);

    ///// Predecate
    Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
    List<String> nonEmptyStrings = filter(listOfStrings, nonEmptyStringPredicate);

    // [a, b]
    //System.out.println(nonEmptyStrings);

    ///// Consumer
    // a
    //
    //b
    //forEach(listOfStrings, (String s) -> System.out.println(s));


    ///// Function
    List<Integer> stringLengths = map(Arrays.asList("abcde", "", "ddd"),
                                    (String s) -> s.length());
    // [5, 0, 3]
    //System.out.println(stringLengths);


    ///// primitive type
    IntPredicate evenNumbers = (int i) -> i % 2 == 0;
    // primitive type(2) 을 reference type 으로 변환하는 boxing 없음
    System.out.println(evenNumbers.test(2));  // true

    Predicate<Integer> evenNumbers2 = (Integer i) -> i % 2 == 0;
    // primitive type(2) 을 reference type 으로 변환하는 boxing 발생
    System.out.println(evenNumbers2.test(2)); // true
  }
  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> results = new ArrayList<>();
    for (T t: list) {
      if (p.test(t)) {
        results.add(t);
      }
    }
    return results;
  }

  public static <T> void forEach(List<T> list, Consumer<T> c) {
    for (T t: list) {
      c.accept(t);
    }
  }

  public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
    List<R> results = new ArrayList<>();
    for (T s: list) {
      results.add(f.apply(s));
    }
    return results;
  }
}
