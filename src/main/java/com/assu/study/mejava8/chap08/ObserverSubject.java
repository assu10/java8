package com.assu.study.mejava8.chap08;

// ConcreteObserver 객체를 관리하는 추상 클래스 혹은 인터페이스
// registerObserver() 로 새로운 옵저버 등록 후 notifyObservers() 로 트윗을 옵저버에 알림
public interface ObserverSubject {
  void registerObserver(Observer o);
  void deregisterObserver(Observer o);
  void notifyObservers(String tweet);
}
