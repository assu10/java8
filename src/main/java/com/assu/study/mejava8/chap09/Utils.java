package com.assu.study.mejava8.chap09;

import java.util.List;

public class Utils {
  public static void paint(List<Resizable> lr) {
    lr.forEach(r -> {
      r.setAbsoluteSize(10, 10);
      r.setAbsoluteSize(20, 20);
      //r.draw();
    });
  }
}
