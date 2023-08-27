package com.assu.study.mejava8.chap08;

public class ChainText extends ChainProcessingObject<String>{
  @Override
  protected String handleWork(String input) {
    return "This is Text" + input + " hi!";
  }
}
