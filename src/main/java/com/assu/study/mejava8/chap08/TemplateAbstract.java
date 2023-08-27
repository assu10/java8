package com.assu.study.mejava8.chap08;

// 템플릿 메서드를 정의하는 추상 클래스
abstract class TemplateAbstract {

  // 템플릿 메서드
  public void processCustomer(int id) {
    Customer c = Database.getCustomerWithId(id);
    makeCustomerHappy(c);
  }

  // primitive 메서드
  abstract void makeCustomerHappy(Customer c);

  // dummy class
  static private class Customer { }

  // dummy database
  static private class Database {
    static Customer getCustomerWithId(int id) {
      return new Customer();
    }
  }
}
