package com.assu.study.mejava8.chap05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Quiz {
  public static void main(String[] args) {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


    // 2011 에 일어난 모든 트랜잭션을 오름차순으로 정리
    List<Transaction> transaction2011 = transactions.stream()
                    .filter(tr -> tr.getYear() == 2011)
                    .sorted(Comparator.comparing(Transaction::getValue))
                    .collect(Collectors.toList());

    // [{Trader:Brian in Cambridge, year: 2011, value:300}, {Trader:Raoul in Cambridge, year: 2011, value:400}]
    System.out.println(transaction2011);


    // 거래자가 근무하는 모든 도시를 중복없이 나열
    List<String> distictCity = transactions.stream()
            .map(tr -> tr.getTrader().getCity())
            .distinct()
            .collect(Collectors.toList());

    // [Cambridge, Milan]
    System.out.println(distictCity);


    // 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
    List<Trader> cambridgeTraders = transactions.stream()
            .map(Transaction::getTrader)
            .filter(tr -> tr.getCity().equals("Cambridge"))
            .distinct()
            .sorted(Comparator.comparing(Trader::getName))
            .collect(Collectors.toList());

    // [Trader:Alan in Cambridge, Trader:Brian in Cambridge, Trader:Raoul in Cambridge]
    System.out.println(cambridgeTraders);


    // 모든 거래자의 이름을 알파벳순으로 정렬하여 String 으로 반환
    List<String> traderNames = transactions.stream()
            .map(Transaction::getTrader)
            .sorted(Comparator.comparing(Trader::getName))
            .map(Trader::getName)
            .distinct()
            .collect(Collectors.toList());
    System.out.println(traderNames);

    String traderNames2 = transactions.stream() // Stream<Transaction> 반환
            .map(tr -> tr.getTrader().getName())  // Stream<String> 반환
            .distinct()
            .sorted()
            .reduce("", (n1, n2) -> n1 + n2);

    // AlanBrianMarioRaoul
    System.out.println(traderNames2);

    String traderNames3 = transactions.stream() // Stream<Transaction> 반환
            .map(tr -> tr.getTrader().getName())  // Stream<String> 반환
            .distinct()
            .sorted()
            .collect(Collectors.joining());

    // AlanBrianMarioRaoul
    System.out.println(traderNames3);


    String traderStr =
            transactions.stream()
                    .map(transaction -> transaction.getTrader().getName())
                    .distinct()
                    .sorted()
                    .reduce("", (n1, n2) -> n1 + n2);
    System.out.println(traderStr);


    // 밀라노에 거주자가 있는지?
    boolean isMilan = transactions.stream()
            .anyMatch(tr -> tr.getTrader()// Trader 반환
                    .getCity()  // String 반환
                    .equals("Milan"));
    // true
    System.out.println(isMilan);


    // 케임브리지에 거주하는 거래자의 모든 트랜잭션 값 출력
    transactions.stream()
            .filter(tr -> tr.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getValue) //Stream<Integer> 반환
            .forEach(System.out::println);
    // 300
    //1000
    //400
    //950


    // 전체 트랜잭션 중 최대값은?
    Optional<Integer> max = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max);

    // Optional[1000]
    System.out.println(max);


    // 전체 트랜잭션 중 최소값은?
    int min1 = transactions.stream()
            .map(Transaction::getValue)
            .reduce(0, Integer::min); // 항상 0 리턴

    // 0
    System.out.println(min1);

    Optional<Transaction> min2 = transactions.stream()
            .min(Comparator.comparing(Transaction::getValue));

    // Optional[{Trader:Brian in Cambridge, year: 2011, value:300}]
    System.out.println(min2);
  }
}
