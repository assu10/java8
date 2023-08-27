package com.assu.study.mejava8.chap08;

public class ObserverMain {
  public static void main(String[] args) {
    ObserverFeed observer = new ObserverFeed();
    observer.registerObserver(new ObserverNYTimes());
    observer.registerObserver(new ObserverLeMonde());

    observer.notifyObservers("money!!");  // NYTimes: money!!

    // 람다 표현식으로
    ObserverFeed observerLambda = new ObserverFeed();

    observerLambda.registerObserver((String tweet) -> {
      if (tweet != null && tweet.contains("money")) {
        System.out.println("NYTimes: " + tweet);
      }
    });

    observerLambda.registerObserver((String tweet) -> {
      if (tweet != null && tweet.contains("wine")) {
        System.out.println("LeMonde: " + tweet);
      }
    });

    observerLambda.notifyObservers("money!!");  // NYTimes: money!!
  }
}
