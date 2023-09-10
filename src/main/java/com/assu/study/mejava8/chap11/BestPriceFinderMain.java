package com.assu.study.mejava8.chap11;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {
  private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

  public static void main(String[] args) {
    execute("basic", () -> bestPriceFinder.findPrices("CoolPrice"));
    execute("findPricesParallel", () -> bestPriceFinder.findPricesParallel("CoolPrice"));
    execute("findPricesFuture", () -> bestPriceFinder.findPricesFuture("CoolPrice"));
    execute("findPricesExecutor", () -> bestPriceFinder.findPricesExecutor("CoolPrice"));

    // System.out.println(Runtime.getRuntime().availableProcessors()); // 12
  }

  private static void execute(String msg, Supplier<List<String>> s) {
    long start = System.nanoTime();
    System.out.println(s.get());
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println(msg + "done in " + duration + " ms");
  }
}
