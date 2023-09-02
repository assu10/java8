package com.assu.study.mejava8.chap08;

import java.util.Arrays;
import java.util.List;

public class Debugging {
  public static void main(String[] args) {
    List<Point> points = Arrays.asList(new Point(3,2), null);
    //points.stream().map(p -> p.getX()).forEach(System.out::println);
    //points.stream().map(Point::getX).forEach(System.out::println);

    List<Integer> numbers = Arrays.asList(1,2,3);
    numbers.stream().map(Debugging::divideByZero).forEach(System.out::println);
  }

  public static int divideByZero(int n) {
    return n / 0;
  }

  private static class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }
  }

}
