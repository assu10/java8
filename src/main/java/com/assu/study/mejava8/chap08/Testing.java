package com.assu.study.mejava8.chap08;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Testing {

  @Test
  public void testMoveRightBy() {
    Point p1 = new Point(5,5);
    Point p2 = p1.moveRightBy(10);

    assertEquals(15, p2.getX());
    assertEquals(5, p2.getY());
  }

  @Test
  public void testComparingTowPoints() {
    Point p1 = new Point(10, 5);
    Point p2 = new Point(5, 10);

    int result = Point.compareByXAndThenY.compare(p1, p2); // 1

    // compare(): 첫 번째 인자가 작으면 -1, 같으면 0, 첫 번째 인자가 더 크면 1

    assertEquals(1, result);
  }

  @Test
  public void testMoveAllPointsRightBy() {
    List<Point> points = Arrays.asList(new Point(5,5), new Point(10,5));
    List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(20,5));

    List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);

    assertEquals(expectedPoints, newPoints);
  }
}
