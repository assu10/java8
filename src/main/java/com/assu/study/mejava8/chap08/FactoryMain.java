package com.assu.study.mejava8.chap08;

public class FactoryMain {
  public static void main(String[] args) {
    FactoryProduct.Product p = FactoryProduct.createProduct("loan");

    // class com.assu.study.mejava8.chap08.FactoryProduct$Loan
    System.out.println(p.getClass());

    // 람다 표현식으로
    FactoryProduct.Product p2 = FactoryProduct.createProductLambda("loan");

    // class com.assu.study.mejava8.chap08.FactoryProduct$Loan
    System.out.println(p2.getClass());
  }
}
