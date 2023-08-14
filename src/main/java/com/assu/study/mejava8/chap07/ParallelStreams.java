package com.assu.study.mejava8.chap07;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {
  public static void main(String[] args) {
    System.out.println(sequentialSum(5)); // 15
    System.out.println(iterativeSum(5)); // 15
    System.out.println(parallelSum(5)); // 15
  }

  // 순차 리듀싱으로 합계 구하기 (with Stream.iterate())
  public static long sequentialSum(long n) {
    return Stream.iterate(1L, i -> i+1) // 무한 자연수 스트림 생성
            .limit(n) // n 개 이하로 제한
            .reduce(0L, Long::sum); // 모든 숫자를 더함

    // return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
  }

  // 반복형으로 합계 구하기
  public static long iterativeSum(long n) {
    long result = 0;
    for (long i=1L; i<=n; i++) {
      result += i;
    }
    return result;
  }

  // 병렬 리듀싱으로 합계 구하기 (with Stream.iterate())
  public static long parallelSum(long n) {
    return Stream.iterate(1L, i -> i+1)  // 무한 자연수 스트림 생성
            .limit(n) // n 개 이하로 제한
            .parallel() // 스트림을 병렬 스트림으로 변환
            .reduce(0L, Long::sum); // 모든 숫자를 더함

    // return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
  }

  // 순차 리듀싱으로 합계 구하기 (with LongStream.rangeClosed())
  public static long sequentialSumWithRangeClosed(long n) {
    return LongStream.rangeClosed(1, n)
            .reduce(0L, Long::sum);

    // return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
  }

  //  병렬 리듀싱으로 합계 구하기 (with LongStream.rangeClosed())
  public static long parallelSumWithRangeClosed(long n) {
    return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(0L, Long::sum);

    // return LongStream.rangeClosed(1, n)..parallel().reduce(Long::sum).getAsLong();
  }

  public static long sideEffectSum(long n) {
    CustomAccumulator accu = new CustomAccumulator();
    LongStream.rangeClosed(1, n).forEach(accu::add);
    return accu.total;
  }

  public static long sideEffectParallelSum(long n) {
    CustomAccumulator accu = new CustomAccumulator();
    LongStream.rangeClosed(1, n).parallel().forEach(accu::add);
    return accu.total;
  }

  public static class CustomAccumulator {
    private long total = 0;
    public void add(long value) {
      total += value;
    }
  }
}
