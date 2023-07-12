package com.assu.study.mejava8.chap03;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MethodReferences {
  public static void main(String[] args) {

    List<String> str = Arrays.asList("a", "b", "A", "B");

    // List 의 sort 메서드는 인수로 Comparator 기대함
    // Comparator 는 (T, T) -> int 라는 함수 디스크립터를 가짐

    // 대소문자 구별을 람다 표현식으로
    str.sort((s1, s2) -> s1.compareToIgnoreCase(s2)); // String 클래스에 정의된 compareToIgnoreCase 메서드로 람다 표현식 정의
    // [a, A, b, B]
    System.out.println(str);

    // 대소문자 구별을 메서드 레퍼런스로
    str.sort(String::compareToIgnoreCase);
    // [a, A, b, B]
    System.out.println(str);
  }
}
