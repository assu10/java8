package com.assu.study.mejava8.chap03;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@SpringBootApplication
public class Sort {
  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(new Apple(10, "red"),
                                          new Apple(150, "green"),
                                          new Apple(10, "blue"));

    // 1 - 동작 파라메터화
//    inventory.sort(new AppleComparator());
//
//    // [Apple{weight=10, color='red'}, Apple{weight=100, color='red'}, Apple{weight=150, color='green'}]
//    System.out.println(inventory);


    // 2 - 익명 클래스
//    inventory.sort(new Comparator<Apple>() {
//      @Override
//      public int compare(Apple o1, Apple o2) {
//        return o1.getWeight().compareTo(o2.getWeight());
//      }
//    });
//    // [Apple{weight=10, color='red'}, Apple{weight=100, color='red'}, Apple{weight=150, color='green'}]
//    System.out.println(inventory);


//    // 3 - 람다 표현식 사용
//    inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
//    // [Apple{weight=10, color='red'}, Apple{weight=100, color='red'}, Apple{weight=150, color='green'}]
//    System.out.println(inventory);

      // 더 간략히
//      inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
//      // [Apple{weight=10, color='red'}, Apple{weight=100, color='red'}, Apple{weight=150, color='green'}]
//      System.out.println(inventory);

    // Comparator<T> 의 comparing() static 메서드 사용
//    Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
//    inventory.sort(c);
//    // [Apple{weight=10, color='red'}, Apple{weight=100, color='red'}, Apple{weight=150, color='green'}]
//    System.out.println(inventory);

    // 더 간략히
//    inventory.sort(comparing((a) -> a.getWeight()));
//    // [Apple{weight=10, color='red'}, Apple{weight=100, color='red'}, Apple{weight=150, color='green'}]
//    System.out.println(inventory);


    // 4 - 메서드 레퍼런스
//    inventory.sort(comparing(Apple::getWeight));
//    // [Apple{weight=10, color='red'}, Apple{weight=100, color='red'}, Apple{weight=150, color='green'}]
//    System.out.println(inventory);


    // 역정렬
//    inventory.sort(comparing(Apple::getWeight).reversed());
//    // [Apple{weight=150, color='green'}, Apple{weight=10, color='red'}, Apple{weight=10, color='blue'}]
//    System.out.println(inventory);

    // 첫 번째 비교자가 같을 경우 두 번째 비교자로 정렬
//    inventory.sort(comparing(Apple::getWeight)
//            .reversed()
//            .thenComparing(Apple::getColor)); // 무게가 같으면 색깔로 내림차순 정렬
//
//    // [Apple{weight=150, color='green'}, Apple{weight=10, color='blue'}, Apple{weight=10, color='red'}]
//    System.out.println(inventory);

    // Predicate - negate()
//    BiFunction<Integer, String, Apple> biFunction = Apple::new;
//    Apple pinkApple = biFunction.apply(100, "pink");
//    Apple yelloApple = biFunction.apply(100, "yellow");
//    Apple blueApple = biFunction.apply(100, "blue");
//
//    Predicate<Apple> pinkPredicate = (Apple a) -> "pink".equals(a.getColor());
//
//    Apple apple1 = filterApple(pinkApple, pinkPredicate);
//    Apple apple2 = filterApple(yelloApple, pinkPredicate);
//
//    System.out.println(apple1); // Apple{weight=100, color='pink'}
//    System.out.println(apple2); // null
//
//    Predicate<Apple> notPinkPredicate = pinkPredicate.negate();
//
//    Apple apple3 = filterApple(pinkApple, notPinkPredicate);
//    Apple apple4 = filterApple(yelloApple, notPinkPredicate);
//
//    System.out.println(apple3); // null
//    System.out.println(apple4); // Apple{weight=100, color='yellow'}
//
//
//    // Predicate - and()
//    Predicate<Apple> pinkAndHeavyPredicate = pinkPredicate.and(a -> a.getWeight() > 50);
//
//    Apple apple5 = filterApple(pinkApple, pinkAndHeavyPredicate);
//
//    // Apple{weight=100, color='pink'}
//    System.out.println(apple5); // Apple{weight=100, color='pink'}


    // Predicate - or()
//    Predicate<Apple> pinkAndHeavyOrYellowPredicate =
//            pinkPredicate.and(a -> a.getWeight() > 50)
//                         .or(a -> "yellow".equals(a.getColor()));
//
//    Apple apple6 = filterApple(pinkApple, pinkAndHeavyOrYellowPredicate);
//    Apple apple7 = filterApple(yelloApple, pinkAndHeavyOrYellowPredicate);
//    Apple apple8 = filterApple(blueApple, pinkAndHeavyOrYellowPredicate);
//
//    System.out.println(apple6); // Apple{weight=100, color='pink'}
//    System.out.println(apple7); // Apple{weight=100, color='yellow'}
//    System.out.println(apple8); // null


    // Function - andThen()
    Function<Integer, Integer> f = x -> x+1;
    Function<Integer, Integer> g = x -> x*2;

    Function<Integer, Integer> h = f.andThen(g);  // g(f(x))

    int result = h.apply(1);
    System.out.println(result); // 4


    // Function - compose()
    Function<Integer, Integer> z = f.compose(g);  // f(g(x))
    int result2 = z.apply(1);
    System.out.println(result2); // 3

  }

  public static <T> Apple filterApple(Apple apple, Predicate<Apple> p) {
    if (p.test(apple)) {
      return apple;
    }
    return null;
  }
//  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
//    List<T> results = new ArrayList<>();
//    for (T t: list) {
//      if (p.test(t)) {
//        results.add(t);
//      }
//    }
//    return results;
//  }

  // 1 - 동작 파라메터화
  public static class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
      return o1.getWeight().compareTo(o2.getWeight());
    }
  }

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
}
