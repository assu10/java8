package com.assu.study.mejava8.chap08;

public class ChainSpellingCheck extends ChainProcessingObject<String>{
  @Override
  protected String handleWork(String input) {
    return input.replaceAll("labda", "lambda");
  }
}
