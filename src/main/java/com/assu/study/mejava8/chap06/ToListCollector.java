package com.assu.study.mejava8.chap06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
  // 새로운 결과 컨테이터 생성
  @Override
  public Supplier<List<T>> supplier() {
    //return () -> new ArrayList<>();
    return ArrayList::new;  // 생성자 레퍼런스 전달 방식
  }

  // 결과 컨테이너에 요소 추가
  @Override
  public BiConsumer<List<T>, T> accumulator() {
    //return (list, item) -> list.add(item);
    return List::add; // 메서드 레퍼런스 방식
  }

  // 두 결과 컨테이너 병합
  @Override
  public BinaryOperator<List<T>> combiner() {
    return (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    };
  }

  // 최종 변환값을 결과 컨테이너로 적용
  @Override
  public Function<List<T>, List<T>> finisher() {
    //return i -> i;
    return Function.identity(); // 항등 함수 반환
  }

  // 컬렉터의 플래그를 IDENTITY_FINISH, CONCURRENT 로 설정
  @Override
  public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
  }
}
