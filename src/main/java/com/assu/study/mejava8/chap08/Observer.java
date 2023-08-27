package com.assu.study.mejava8.chap08;

// Observer
// 새로운 트윗이 있을 때 Feed(Subject) 가 호출할 수 있도록 notify() 메서드를 제공
public interface Observer {
  void notify(String tweet);
}
