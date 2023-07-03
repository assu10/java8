package com.assu.study.mejava8.chap03;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class ExecuteAround {
  public static void main(String[] args) throws IOException {
    String result1 = processFile();

    String oneLine = processFile((BufferedReader reader) -> reader.readLine());
    String twoLine = processFile((BufferedReader reader) -> reader.readLine() + reader.readLine());
  }

  // 최초 코드
  public static String processFile() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader("data.txt"))) {
      return reader.readLine();
    }
  }

  // 함수형 인터페이스 생성
  @FunctionalInterface
  public interface BufferedReaderProcess {
    String process(BufferedReader b) throws IOException;
  }

  public static String processFile(BufferedReaderProcess b) throws IOException {
    try (BufferedReader reader =
            new BufferedReader(new FileReader("data.txt"))) {
      return b.process(reader);
    }
  }
}
