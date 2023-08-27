package com.assu.study.mejava8.chap08;

public class TemplateAbstractMain {
  public static void main(String[] args) {
    new TemplateAbstractLambda().processCustomer(111, (TemplateAbstractLambda.Customer c) -> System.out.println("hello"));
  }
}
