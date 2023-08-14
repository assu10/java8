package com.assu.study.mejava8.chap07;

import java.util.function.Function;

public class ParallelStreamsHarness {
  public static void main(String[] args) {
    // 반복형 성능 측정: 2 ms
    //System.out.println("Iterative Sum done in: " + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000L) + " ms");

    // 순차형 리듀싱 (Stream.iterate()) 성능 측정: 75 ms
    //System.out.println("Sequential Sum done in: " + measureSumPerf(ParallelStreams::sequentialSum, 10_000_000L) + " ms");

    // 병렬형 리듀싱 (Stream.iterate()) 성능 측정: 111 ms
    //System.out.println("Parallel Sum done in: " + measureSumPerf(ParallelStreams::parallelSum, 10_000_000L) + " ms");

    // 순차형 리듀싱 (LongStream.rangeClosed()) 성능 측정: 2 ms
    //System.out.println("Range forkJoinSum done in: " + measureSumPerf(ParallelStreams::sequentialSumWithRangeClosed, 10_000_000L) + " ms");

    // 병렬형 리듀싱 (LongStream.rangeClosed()) 성능 측정: 0ms
    //System.out.println("Parallel range forkJoinSum done in: " + measureSumPerf(ParallelStreams::parallelSumWithRangeClosed, 10_000_000L) + " ms");

    // 공유된 누적자를 바꾸는 알고리즘 (병렬)
    //System.out.println("SideEffect parallel sum done in: " + measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000L) + " ms");

    // 포크/조인 프레임워크를 이용해서 병렬 합계 수행
    //System.out.println("ForkJoin sum done in: " + measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L) + " ms");
    System.out.println("ForkJoin sum result in: " + resultSum(ForkJoinSumCalculator::forkJoinSum, 8L));
  }

  // Function<T,R> 은 T -> R
  // 메서드로 전달된 함수를 10번 반복수행하면서 시간을 ms 단위로 측정하고, 그 중 가장 짧은 시간을 리턴
  public static long measureSumPerf_old(Function<Long, Long> adder, long n) {
    long fastest = Long.MAX_VALUE;
    for (int i=0; i<10; i++) {
      long start = System.nanoTime();
      long sum = adder.apply(n);
      long duration = (System.nanoTime() - start) / 1_000_000;
      System.out.println("result: " + sum);
      if (duration < fastest) {
        fastest = duration;
      }
    }
    return fastest;
  }

  // Function<T,R> 은 T -> R
  // 메서드로 전달된 함수를 10번 반복수행하면서 시간을 ms 단위로 측정하고, 그 중 가장 짧은 시간을 리턴
  public static <T,R> long measureSumPerf(Function<T, R> f, T input) {
    long fastest = Long.MAX_VALUE;
    for (int i=0; i<10; i++) {
      long start = System.nanoTime();
      R result = f.apply(input);
      long duration = (System.nanoTime() - start) / 1_000_000;
      System.out.println("result: " + result);
      if (duration < fastest) {
        fastest = duration;
      }
    }
    return fastest;
  }

  public static <T,R> R resultSum(Function<T,R> f, T input) {
    R result = f.apply(input);
    return result;
  }
}
