package com.assu.study.mejava8.chap08;

import java.util.ArrayList;
import java.util.List;

// ConcreteSubject (변경 관리 대상이 되는 데이터가 있는 클래스)
public class ObserverFeed implements ObserverSubject {
  private final List<Observer> observers = new ArrayList<>();

  @Override
  public void deregisterObserver(Observer o) {
    this.observers.remove(o);
  }

  @Override
  public void registerObserver(Observer o) {
    this.observers.add(o);
  }

  @Override
  public void notifyObservers(String tweet) {
    observers.forEach(o -> o.notify(tweet));
  }
}
