package com.assu.study.mejava8.chap03;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
public class ConstructorReferenceFruit {
  public static void main(String[] args) {
    Fruit f1 = getFruit("apple", 2);
    Fruit f2 = getFruit("mango", 5);

    System.out.println(f1); // Apple{weight=2, color='null'}
    System.out.println(f2); // Mango{weight=5, color='null'}
  }

  // 다양한 무게를 갖는 여러 종류의 과일
  static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
  static {
    map.put("apple", Apple::new);
    map.put("mango", Mango::new);
  }

  public static Fruit getFruit(String fruit, Integer weight) {
    return map.get(fruit.toLowerCase()) // map 에서 Function<Integer, Fruit> 얻음
            .apply(weight); // Fruit 생성
  }

  public interface Fruit {
    Integer getWeight();
  }

  // Apple 이라는 클래스
  public static class Apple implements Fruit {
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

  public static class Mango implements Fruit {
    private Integer weight;
    private String color;

    public Mango() {

    }

    public Mango(Integer weight) {
      this.weight = weight;
    }

    public Mango(Integer weight, String color) {
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
      return "Mango{" +
              "weight=" + weight +
              ", color='" + color + '\'' +
              '}';
    }
  }
}
