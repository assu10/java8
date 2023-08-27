package com.assu.study.mejava8.chap08;

import java.util.function.Consumer;

public class TemplateAbstractLambda {
  // 템플릿 메서드
  public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
    Customer c = Database.getCustomerWithId(id);
    makeCustomerHappy.accept(c);
  }

  // dummy class
  static public class Customer { }

  // dummy database
  static public class Database {
    static Customer getCustomerWithId(int id) {
      return new Customer();
    }
  }
}
