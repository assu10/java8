package com.assu.study.mejava8.chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Chap01Application {
  public static void main(String[] args) {
    //** 메서드 레퍼런스(1) **//
    // 기존 자바
/*    File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
      public boolean accept(File file) {
        return file.isHidden();
      }
    });

    // 메서드 레퍼런스 이용
    File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);*/


    //** 메서드 레퍼런스(2) **//
    List<Apple> inventory = Arrays.asList(new Apple(10, "red"),
                                          new Apple(100, "green"),
                                          new Apple(150, "red"));

    // 기존 자바에서 red 사과 필터링 시
    List<Apple> redApples = filterRedApples(inventory);
    // [Apple{weight=10, color='red'}, Apple{weight=150, color='red'}]
    System.out.println(redApples);

    // 기존 자바에서 100 이상 필터링 시
    List<Apple> weightApples = filterWeightApples(inventory);
    // [Apple{weight=100, color='green'}, Apple{weight=150, color='red'}]
    System.out.println(weightApples);

    // 메서드 레퍼런스로 red 사과 필터링 시
    List<Apple> redApples2 = filterApples(inventory, Chap01Application::isRedApple);
    // [Apple{weight=10, color='red'}, Apple{weight=150, color='red'}]
    System.out.println(redApples2);

    // 메서드 레퍼런스로 100 이상 필터링 시
    List<Apple> weightApples2 = filterApples(inventory, Chap01Application::isWeightApple);
    // [Apple{weight=100, color='green'}, Apple{weight=150, color='red'}]
    System.out.println(weightApples2);

    // 람다로 red 사과 필터링 시
    List<Apple> redApples3 = filterApples(inventory, (Apple a) -> "red".equals(a.getColor()));
    // [Apple{weight=10, color='red'}, Apple{weight=150, color='red'}]
    System.out.println(redApples3);

    // 람다로 100 이상 필터링 시
    List<Apple> weightApples3 = filterApples(inventory, (Apple a) -> a.getWeight() >= 100);
    // [Apple{weight=100, color='green'}, Apple{weight=150, color='red'}]
    System.out.println(weightApples3);
  }

  // 기존 자바에서 red 사과 필터링 시
  public static List<Apple> filterRedApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory) {
      if ("red".equals(apple.getColor())) {
        result.add(apple);
      }
    }
    return result;
  }

  // 기존 자바에서 100 이상 필터링 시
  public static List<Apple> filterWeightApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory) {
      if (apple.getWeight() >= 100) {
        result.add(apple);
      }
    }
    return result;
  }

  // 메서드 레퍼런스로 필터링 시
  public static boolean isRedApple(Apple apple) {
    return "red".equals(apple.getColor());
  }
  public static boolean isWeightApple(Apple apple) {
    return apple.getWeight() >= 100;
  }
  static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory) {
      // p 가 제시하는 조건에 사과가 맞는가?
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }


  // Apple 이라는 클래스
  public static class Apple {
    private int weight;
    private String color;

    public Apple(int weight, String color) {
      this.weight = weight;
      this.color = color;
    }

    public int getWeight() {
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
}
