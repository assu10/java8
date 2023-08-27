package com.assu.study.mejava8.chap08;

// ConcreteObserver (ConcreteSubject 의 변경을 통보받는 클래스)
// 트윗에 포함된 다양한 키워드에 다른 동작을 수행할 수 있는 옵저버
public class ObserverLeMonde implements Observer {
  @Override
  public void notify(String tweet) {
    if (tweet != null && tweet.contains("wine")) {
      System.out.println("LeMonde: " + tweet);
    }
  }
}
