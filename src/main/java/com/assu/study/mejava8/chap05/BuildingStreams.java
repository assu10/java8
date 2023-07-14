package com.assu.study.mejava8.chap05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {
  public static void main(String[] args) {
    // 값으로 스트림 생성
    Stream<String> streams = Stream.of("hello", "world");

    // 문자열 스트림의 모든 문자열을 대문자 변환 후 하나씩 출력
    //HELLO
    //WORLD
    streams.map(String::toUpperCase).forEach(System.out::println);

    // 스트림 비우기
    Stream<String> emptyStream = Stream.empty();


    // 배열로 스트림 생성
    int[] numbers = {1,2,3};
    int sum = Arrays.stream(numbers)  // IntStream 반환
            .sum();
    // 6
    System.out.println(sum);


    // 파일로 스트림 생성
    long uniqueWordsCount = 0;
    List<String> uniqueWords;

    String path = System.getProperty("user.dir") + "/src/main/java/com/assu/study/mejava8/chap05/data.txt";

    try (Stream<String> lines = Files.lines(Paths.get(path), Charset.defaultCharset())) {
//      uniqueWordsCount = lines.flatMap(line -> Arrays.stream(line.split(" ")))
//              .distinct()
//              .count();

      uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))) // 각 행의 단어를 여러 스트림으로 만드는 것이 아니라 flatMap() 으로 스트림을 하나로 평면화
              .distinct()
              .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // [안녕하세요., 안녕~, 중복을, 제거할꺼에요., 중복]
    System.out.println(uniqueWords);
    //System.out.println(uniqueWordsCount);

    System.out.println("----------");

    // Stream.iterate
    // 0 부터 짝수 10 출력
    List<Integer> evenNumbers = Stream.iterate(0, n -> n+2)
            .limit(10)
            .collect(Collectors.toList());

    // [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]
    System.out.println(evenNumbers);


    // Stream.generate
    // 0과 1 사이의 임의의 double 숫자 5개 생성
    Stream.generate(Math::random)
            .limit(5)
            .forEach(System.out::println);
    // 0.2783737421773751
    //0.14317629653439412
    //0.9158527117939272
    //0.009494136863103964
    //0.5834030590726629


    // 1 을 출력하는 무한 스트림 생성
    IntStream ones = IntStream.generate(() -> 1).limit(10);

    // 1 이 10번 출력됨
    ones.forEach(System.out::println);


    // 2 를 출력하는 무한 스트림 생성
    IntStream twos = IntStream.generate(new IntSupplier() {
      @Override
      public int getAsInt() {
        return 2;
      }
    }).limit(10);

    // 2 가 10번 출력됨
    twos.forEach(System.out::println);
  }
}
