package com.assu.study.mejava8.chap02;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

@SpringBootApplication
public class FilteringApples {
  public static void main(String[] args) {

    List<Apple> inventory = Arrays.asList(new Apple(10, "red"),
            new Apple(100, "green"),
            new Apple(150, "red"));

    // 1. 기존 자바에서 red 사과 필터링 시
    List<Apple> redApples = filterRedApples(inventory);
    // [Apple{weight=10, color='red'}, Apple{weight=150, color='red'}]
    System.out.println(redApples);

    // 1. 기존 자바에서 100 이상 필터링 시
    List<Apple> weightApples = filterWeightApples(inventory);
    // [Apple{weight=100, color='green'}, Apple{weight=150, color='red'}]
    System.out.println(weightApples);


    // 2. 동작 파라메터화로 red 사과 필터링 시
    List<Apple> redApples2 = filterApples(inventory, new AppleRedColorPredicate());
    // [Apple{weight=10, color='red'}, Apple{weight=150, color='red'}]
    System.out.println(redApples2);

    // 2. 동작 파라메터화로 100 이상 필터링 시
    List<Apple> weightApples2 = filterApples(inventory, new AppleWeightPredicate());
    // [Apple{weight=100, color='green'}, Apple{weight=150, color='red'}]
    System.out.println(weightApples2);



    // 3. 익명 클래스로 red 사과 필터링 시
    List<Apple> redApples3 = filterApples(inventory, new ApplePredicate() {
      @Override
      public boolean test(Apple apple) {
        return "red".equals(apple.getColor());
      }
    });
    // [Apple{weight=10, color='red'}, Apple{weight=150, color='red'}]
    System.out.println(redApples3);

    // 3. 익명 클래스로 100 사과 필터링 시
    List<Apple> weightApples3 = filterApples(inventory, new ApplePredicate() {
      @Override
      public boolean test(Apple apple) {
        return apple.getWeight() >= 100;
      }
    });
    // [Apple{weight=100, color='green'}, Apple{weight=150, color='red'}]
    System.out.println(weightApples3);


    // 4. 람다 표현식으로 red 사과 필터링 시
    List<Apple> redApples4 = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
    // [Apple{weight=10, color='red'}, Apple{weight=150, color='red'}]
    System.out.println(redApples4);


    // 4. 람다 표현식으로 100 사과 필터링 시
    List<Apple> weightApples4 = filterApples(inventory, (Apple apple) -> apple.getWeight() >= 100);
    // [Apple{weight=100, color='green'}, Apple{weight=150, color='red'}]
    System.out.println(weightApples4);


    // 5. 리스트 추상화로 red 사과 필터링 시
    List<Apple> redApples5 = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
    // [Apple{weight=10, color='red'}, Apple{weight=150, color='red'}]
    System.out.println(redApples5);

    // 5. 리스트 추상화로 짝수 필터링 시
    List<Integer> numbers = Arrays.asList(10, 11, 12, 13, 14);
    List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
    // [10, 12, 14]
    System.out.println(evenNumbers);


    // 6. Comparator 의 compare() 로 List 의 sort() 동작 파라메터화
    // 무게가 큰 순으로 정렬

    // [Apple{weight=10, color='red'}, Apple{weight=100, color='green'}, Apple{weight=150, color='red'}]
    System.out.println(inventory);

    inventory.sort(new Comparator<Apple>() {
      @Override
      public int compare(Apple o1, Apple o2) {
        return o2.getWeight().compareTo(o1.getWeight());
      }
    });

    // [Apple{weight=150, color='red'}, Apple{weight=100, color='green'}, Apple{weight=10, color='red'}]
    System.out.println(inventory);

     inventory.sort((Apple a1, Apple a2) -> a2.getWeight().compareTo(a1.getWeight()));

     // 메서드 레퍼런스 사용
    inventory.sort(comparing(Apple::getWeight));

    // [Apple{weight=150, color='red'}, Apple{weight=100, color='green'}, Apple{weight=10, color='red'}]
     System.out.println(inventory);


     // Runnable 로 코드 블록 실행
     Thread t = new Thread(new Runnable() {
       @Override
       public void run() {
         System.out.println("hello~");
       }
     });

     Thread t2 = new Thread(() -> System.out.println("hello2~"));

  }

  // Apple 이라는 클래스
  public static class Apple {
    private Integer weight;
    private String color;

    public Apple(int weight, String color) {
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

  // 1. 기존 자바에서 red 사과 필터링 시
  public static List<Apple> filterRedApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory) {
      if ("red".equals(apple.getColor())) {
        result.add(apple);
      }
    }
    return result;
  }

  // 1. 기존 자바에서 100 이상 필터링 시
  public static List<Apple> filterWeightApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory) {
      if (apple.getWeight() >= 100) {
        result.add(apple);
      }
    }
    return result;
  }


  // 2. 동작 파라메터화를 위해 선택 조건을 결정하는 인터페이스
  public interface ApplePredicate {
    boolean test(Apple apple);
  }

  // 2. 동작 파라메터화로 red 사과 필터링 시
  static class AppleRedColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
      return "red".equals(apple.getColor());
    }
  }

  // 2. 동작 파라메터화로 100 이상 필터링 시
  static class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
      return apple.getWeight() >= 100;
    }
  }

  // 2. 동작 파라메터화로 사과 필터링
  public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory) {
      if (p.test(apple)) {  // Predicate 객체로 검사 조건 캡슐화
        result.add(apple);
      }
    }
    return result;
  }


  // 5. 리스트 추상화
  public static <T> List<T> filter(List<T> list, Predicate<T> p) {  // 형식 파라메터 T
    List<T> result = new ArrayList<>();
    for (T e: list) {
      if (p.test(e)) {
        result.add(e);
      }
    }
    return result;
  }
}