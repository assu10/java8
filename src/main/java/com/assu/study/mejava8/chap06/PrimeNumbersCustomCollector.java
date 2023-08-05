package com.assu.study.mejava8.chap06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class PrimeNumbersCustomCollector
                    implements Collector<Integer  // 스트림 요소의 형식
                                        , Map<Boolean, List<Integer>> // 누적자 형식
                                        , Map<Boolean, List<Integer>>> {
  /**
   * A function that creates and returns a new mutable result container.
   * 2 개의 빈 리스트를 포함하는 맵으로 수집 동작 시작
   *
   * @return a function which returns a new, mutable result container
   *        새롭고 변경 가능한 결과 컨테이너를 반환하는 함수 반환
   *        누적자를 만드는 함수를 반환
   */
  @Override
  public Supplier<Map<Boolean, List<Integer>>> supplier() {
    // 누적자로 사용할 맵을 만들면서 true, false 키와 빈 리스트로 초기화 진행
    // (수집 과정에서 빈 리스트에 각각 소수와 비소수 추가)
    return () -> new HashMap<Boolean, List<Integer>>() {
      {
        put(true, new ArrayList<Integer>());
        put(false, new ArrayList<Integer>());
      }
    };
  }

  /**
   * A function that folds a value into a mutable result container.
   *
   * @return a function which folds a value into a mutable result container
   *        값을 변경 가능한 결과 컨테이너로 넣는 함수 반환
   */
  @Override
  public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
    return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
                    acc.get(CustomCollector.isPrime(acc.get(true) // 지금까지 발견한 소수 리스트를 isPrime() 에 전달
                           , candidate))  // isPrime() 결과에 따라 소수 리스트와 비소수 리스트 만듬
                       .add(candidate); // candidate 를 알맞은 리스트에 추가
    };
  }

  /**
   * A function that accepts two partial results and merges them.  The
   * combiner function may fold state from one argument into the other and
   * return that, or may return a new result container.
   *
   * @return a function which combines two partial results into a combined
   * result
   */
  @Override
  public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
    return null;
  }

  /**
   * Perform the final transformation from the intermediate accumulation type
   * {@code A} to the final result type {@code R}.
   *
   * <p>If the characteristic {@code IDENTITY_FINISH} is
   * set, this function may be presumed to be an identity transform with an
   * unchecked cast from {@code A} to {@code R}.
   *
   * @return a function which transforms the intermediate result to the final result
   *        중간 결과를 최종 결과로 변환하는 함수 반환
   */
  @Override
  public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
    // 최종 수집 과정에서 데이터 변환이 필요하지 않으므로 항등 함수 반환
    return Function.identity();
  }

  /**
   * Returns a {@code Set} of {@code Collector.Characteristics} indicating
   * the characteristics of this Collector.  This set should be immutable.
   *
   * @return an immutable set of collector characteristics
   */
  @Override
  public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
  }

}
