package com.assu.study.mejava8.chap09;

public interface Resizable {
  int getWidth();
  int getHeight();
  void setWidth(int width);
  void setHeight(int height);
  void setAbsoluteSize(int width, int height);
  //void setRelativeSize(int wFactor, int hFactor); // 버전2에 추가된 메서드

  default void setRelativeSize(int wFactor, int hFactor) {
    setAbsoluteSize(getWidth() / wFactor, getHeight()/hFactor);
  }
}
