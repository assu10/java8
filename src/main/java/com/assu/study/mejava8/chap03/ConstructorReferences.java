package com.assu.study.mejava8.chap03;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class ConstructorReferences {
  public static void main(String[] args) {

    // 생성자 인수가 0개인 경우 - 생성자 레퍼런스 사용
    Supplier<Apple> c1 = Apple::new;  // 디폴트 생성자 Apple() 의 생성자 레퍼런스
    Apple a1 = c1.get();  // Supplier 의 get() 메서드롤 호출하여 새로운 Apple 객체 생성

    // 생성자 인수가 0개인 경우 - 람다 표현식 사용
    Supplier<Apple> c2 = () -> new Apple(); // 디폴트 생성자 Apple() 의 람다 표현식
    Apple a2 = c2.get();  // Supplier 의 get() 메서드롤 호출하여 새로운 Apple 객체 생성


    // 생성자 인수가 1개인 경우 - 생성자 레퍼런스 사용
    Function<Integer, Apple> c3 = Apple::new; // Apple(Integer weight) 의 생성자 레퍼런스
    Apple a3 = c3.apply(10);  // Function 의 apply() 메서드롤 호출하여 새로운 Apple 객체 생성

    // 생성자 인수가 1개인 경우 - 람다 표현식 사용
    Function<Integer, Apple> c4 = (weight) -> new Apple(weight);  // Apple(Integer weight) 의 람다 표현식
    Apple a4 = c4.apply(10);  // Function 의 apply() 메서드롤 호출하여 새로운 Apple 객체 생성


    // map() 을 이용하여 다양한 무게 리스트 만들기
    List<Integer> weights = Arrays.asList(1,5,9,7);
    List<Apple> apples = map(weights, Apple::new);  // map() 메서드로 생성자 레퍼런스 전달


    // 생성자 인수가 2개인 경우 - 생성자 레퍼런스 사용
    BiFunction<Integer, String, Apple> c5 = Apple::new; // Apple(Integer weight, String color) 의 생성자 레퍼런스
    Apple a5 = c5.apply(10, "red"); // BiFunction 의 apply() 메서드롤 호출하여 새로운 Apple 객체 생성

    // 생성자 인수가 2개인 경우 - 람다 표현식 사용
    BiFunction<Integer, String, Apple> c6 = (weight, color) -> new Apple(weight, color);  // Apple(Integer weight, String color) 의 람다 표현식
    Apple a6 = c6.apply(10, "red"); // BiFunction 의 apply() 메서드롤 호출하여 새로운 Apple 객체 생성

  }

  // Apple 이라는 클래스
  public static class Apple {
    private Integer weight;
    private String color;

    public Apple() {

    }

    public Apple(Integer weight) {
      this.weight = weight;
    }

    public Apple(Integer weight, String color) {
      this.weight = weight;
      this.color = color;
    }

    public Integer getWeight() {
      return weight;
    }

    public void setWeight(int weight) {
      this.weight = weight;
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    @Override
    public String toString() {
      return "Apple{" +
              "weight=" + weight +
              ", color='" + color + '\'' +
              '}';
    }
  }

  public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
    List<R> results = new ArrayList<>();
    for (T s: list) {
      // 생성자 레퍼런스를 사용하면 객체의 생성이 delay 됨 (=lazy initialize 가능)
      // 실제 객체는 get() 이나 apply() 같은 메서드가 호출될 때 생성됨
      // factory method pattern 에 유용히 사용
      results.add(f.apply(s));
    }
    return results;
  }
}

