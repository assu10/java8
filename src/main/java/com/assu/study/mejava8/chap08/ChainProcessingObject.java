package com.assu.study.mejava8.chap08;

public abstract class ChainProcessingObject<T> {
  protected ChainProcessingObject<T> successor;

  public void setSuccess(ChainProcessingObject<T> successor) {
    this.successor = successor;
  }

  public T handle(T input) {
    T r = handleWork(input);
    if (successor != null) {
      return successor.handle(r);
    }
    return r;
  }

  abstract protected T handleWork(T input);
}
