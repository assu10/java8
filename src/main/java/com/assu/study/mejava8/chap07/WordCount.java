package com.assu.study.mejava8.chap07;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCount {
  public static final String SENTENCE = " HI  My name is assu.";

  public static void main(String[] args) {
    // 반복형 단어 개수 카운팅
    // Found Iteratively: 5 words
    //System.out.println("Found : " + countWordsIteratively(SENTENCE) + " words");


    // 함수형 단어 개수 카운팅
//    Stream<Character> stream = IntStream.range(0, SENTENCE.length())
//                                        .mapToObj(SENTENCE::charAt);

    // Found Iteratively: 5 words
    //System.out.println("Found : " + countWords(stream) + " words");

    // 함수형 단어 개수 카운팅을 병렬로 수행
//    Stream<Character> stream = IntStream.range(0, SENTENCE.length())
//            .mapToObj(SENTENCE::charAt);
//
//    // 잘못된 예시 - Found : 15 words
//    System.out.println("Found : " + countWords(stream.parallel()) + " words");


    Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
    // 두 번째 인수(true)는 병렬 스트림 생성 여부를 지시함
    Stream<Character> stream = StreamSupport.stream(spliterator, true);

    // Found : 5 words
    System.out.println("Found : " + countWords(stream) + " words");


  }

  // 반복형 단어 개수 카운팅
  private static int countWordsIteratively(String s) {
    int counter = 0;
    boolean lastSpace = true;

    // 문자열의 모든 문자를 하나씩 참색
    for (char c : s.toCharArray()) {
      if (Character.isWhitespace(c)) {
        lastSpace = true;
      } else {
        // 문자를 하나씩 탐색하다가 공백 문자를 만나면 지금까지 탐색한 문자를 단어로 간주하여 단어 개수 증가
        if (lastSpace) {
          counter++;
        }
        lastSpace = false;
      }
    }
    return counter;
  }

  // 함수형 단어 개수 카운팅
  private static int countWords(Stream<Character> stream) {
    WordCounter wordCounter = stream.reduce(new WordCounter(0, true), // 초기값
                                            WordCounter::accumulate,  // 누적 로직
                                            WordCounter::combine);  // 병합 로직
    return wordCounter.getCounter();
  }

  // 함수형 단어 개수 카운팅에서 사용할 클래스
  // 지금까지 발견한 단어 개수 누적값과 마지막 문자의 공백 여부값 저장
  private static class WordCounter {
    private final int counter;
    private final boolean isLastSpace;

    public WordCounter(int counter, boolean isLastSpace) {
      this.counter = counter;
      this.isLastSpace = isLastSpace;
    }

    // 문자열의 문자를 하나씩 탐색하며 새로운 WordCounter 를 어떤 상태로 생성할 것인지 정의
    // " HI  My name is assu."
    public WordCounter accumulate(Character c) {
      if (Character.isWhitespace(c)) {
        System.out.println("accumulater White 1 - this: " + this);
        return isLastSpace ? this : new WordCounter(counter, true);
      } else {
        System.out.println("accumulater White 2 - this: " + this);
        // 공백을 만나면 지금까지 탐색한 문자를 단어로 간주하여 단어 개수 증가
        return isLastSpace ? new WordCounter(counter+1, false) : this;
      }
    }

    // 2 WordCounter 의 counter 값을 더함
    // 문자열 서브 스트림을 처리한 WordCounter 의 결과 합침
    public WordCounter combine(WordCounter wordCounter) {
      return new WordCounter(counter + wordCounter.counter
              , wordCounter.isLastSpace); // counter 값만 더할 것이므로 마지막 공백은 신경쓰지 않음
    }

    public int getCounter() {
      return counter;
    }

    @Override
    public String toString() {
      return "WordCounter{" +
              "counter=" + counter +
              ", isLastSpace=" + isLastSpace +
              '}';
    }
  }

  // 함수형 단어 개수 카운팅을 병렬로 수행
  private static class WordCounterSpliterator implements Spliterator<Character> {
    private final String strings;
    private int currentCharIdx = 0;

    public WordCounterSpliterator(String strings) {
      this.strings = strings;
    }

    // Spliterator 의 요소를 하나씩 순차적으로 소비하면서 탐색해야 요소가 남아있는지 여부를 반환
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
      // 현재 문자를 소비
      // 현재 인덱스에 해당하는 문자를 Consumer 에 제공한 다음에 인덱스를 증가시킴
      action.accept(strings.charAt(currentCharIdx++));

      // 소비할 문자가 남아있으면 true 반환
      return currentCharIdx < strings.length();
    }

    // Spliterator 의 일부 요소를 분할하여 두 번째 spliterator 를 생성
    // 반복될 자료 구조를 분할하는 로직 포함
    @Override
    public Spliterator<Character> trySplit() {
      int currentSize = strings.length() - currentCharIdx;

      // 분할 동작을 중단할 한계를 설정
      // 파싱할 문자열을 순차 처리할 수 있을 만큼 충분히 작아졌음을 알리는 null 반환
      if (currentSize < 4) {
        // 분할 중지
        return null;
      }

      // 파싱할 문자열 chunk 의 중간을 분할 위치로 설정
      for (int splitPos = (currentSize / 2)+currentCharIdx; splitPos < strings.length(); splitPos++) {
        // 단어 중간을 분할하지 않도록 다음 공백이 나올때까지 분할 위치를 뒤로 이동시킴
        if (Character.isWhitespace(strings.charAt(splitPos))) {
          // 분할할 위치를 찾았으면 처음부터 분할 위치까지 문자열을 파싱할 새로운 WordCounterSpliterator 생성
          // 새로운 WordCounterSpliterator 는 현재 위치(currentCharIdx) 부터 분할된 위치까지의 문자를 탐색
          Spliterator<Character> spliterator = new WordCounterSpliterator(strings.substring(currentCharIdx, splitPos));
          // 이 WordCounterSpliterator 의 시작 위치를 분할 위치로 설정
          currentCharIdx = splitPos;
          return spliterator;
        }
      }
      return null;
    }

    // 탐색해야 할 요소 수 반환
    @Override
    public long estimateSize() {
      return strings.length() - currentCharIdx;
    }

    // Spliterator 자체의 특성 집합을 포함하는 int 반환
    @Override
    public int characteristics() {
      return ORDERED  // 문자열의 문자 등장 순서가 유의미함
              + SIZED // estimatedSize() 메서드의 반환값이 정확함
              + SUBSIZED  // trySplit() 으로 생성된 Spliterator 도 정확한 크기를 가짐
              + NONNULL // 문자열에는 null 문자가 존재하지 않음
              + IMMUTABLE;  // 문자열 자체가 불편 클래스이므로 문자열을 파싱하면서 속성이 추가되지 않음
    }
  }
}
