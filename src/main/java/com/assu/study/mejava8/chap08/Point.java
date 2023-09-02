package com.assu.study.mejava8.chap08;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Point {
  private final int x;
  private final int y;

  public final static Comparator<Point> compareByX =
          Comparator.comparing(Point::getX);

  public final static Comparator<Point> compareByY =
          Comparator.comparing(Point::getY);

  public final static Comparator<Point> compareByXAndThenY =
          Comparator.comparing(Point::getX).thenComparing(Point::getY);
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Point moveRightBy(int x) {
    return new Point(this.x + x, this.y);
  }

  public static List<Point> moveAllPointsRightBy(List<Point> point, int x) {
    return point.stream()
            .map(p -> new Point(p.getX()+x, p.getY()))
            .collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return getX() == point.getX() && getY() == point.getY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }
}
