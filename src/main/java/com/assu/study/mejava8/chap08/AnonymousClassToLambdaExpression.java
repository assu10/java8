package com.assu.study.mejava8.chap08;

public class AnonymousClassToLambdaExpression {
  public static void main(String[] args) {
    // 익명 클래스는 감싸고 있는 클래스의 변수를 가릴 수 있음 (섀도우 변수)
    int a = 11;
    Runnable r1 = new Runnable() {
      @Override
      public void run() {
        int a = 10;
        System.out.println(a);
      }
    };

    // 10
    r1.run();

    // 람다는 변수를 가릴 수 없음
    int b = 22;
    Runnable r2 = () -> {
      //int b = 20; // 이 부분때문에 컴파일되지 않음
      System.out.println(b);
    };

    r2.run();

    // Task 를 구현하는 익명 클래스 전달
    Run(new Task() {
      @Override
      public void execute() {
        System.out.println("Task 를 구현하는 익명 클래스 전달");
      }
    });

    // 익명 클래스를 람다로 바꾸어 메서드 호출
    //Run(() -> System.out.println("Task 를 구현하는 익명 클래스 전달")); // 오류 발생

    // 명시적 형변환으로 모호함 제거
    Run((Task) () -> System.out.println("Task 를 구현하는 익명 클래스 전달"));


  }

  interface Task {
    public void execute();
  }

  public static void Run(Runnable r) {
    r.run();
  }

  public static void Run(Task t) {
    t.execute();
  }
}
