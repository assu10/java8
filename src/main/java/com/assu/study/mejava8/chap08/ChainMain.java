package com.assu.study.mejava8.chap08;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainMain {
  public static void main(String[] args) {
    ChainProcessingObject<String> p1 = new ChainText();
    ChainProcessingObject<String> p2 = new ChainSpellingCheck();

    // 2개의 작업처리 객체 연결
    p1.setSuccess(p2);

    String result = p1.handle("test labdas.");

    // result: This is Texttest lambdas. hi!
    System.out.println("result: " + result);

    // 람다 표현식으로
    UnaryOperator<String> textProcessing = (String text) -> "This is Text" + text + " hi!";
    UnaryOperator<String> spellingCheckProcessing = (String text) -> text.replaceAll("labda", "lambda");

    Function<String, String> pipeline = textProcessing.andThen(spellingCheckProcessing);
    String result2 = pipeline.apply("test labdas.");

    // result2: This is Texttest lambdas. hi!
    System.out.println("result2: " + result2);
  }
}
