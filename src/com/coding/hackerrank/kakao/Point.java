package com.coding.hackerrank.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Point {
    public static class Person {
        public String name;
        public int point;
        public Person(String name, int point) {
            this.name = name;
            this.point = point;
        }

    }
    public static List<String> solution(List<List<String>> arr) {
    /* 포인트가있어야 빌릴수있고
    빌려주면 포인트가 올라감
    포인트는 음수까지갈수있음
    거래기록에 따라 최소잔여포인트를 가진사람을 반한하되 최소잔여포인트는 음수일경우에만 해당, 0이상일경우 NONE문자 반환
    여러사람의 최소잔여포인트가 같을경우 알파벳이름순섯로 이름 목록 반환 (오름차순)

    1. 빌린사람, 빌려준사람 리스트를 순회하여 Map에다가 담아 모든 사람의 리스트를 만듦.
    2. arr을 순회하면서 거래기록에 따른 Map의 key(사람)의 포인트를 기록함
    3. 값(포인트)중에 음수 존재하는지 찾으며 없다면 NONE반환, 있다면 Set으로 꺼내온 후 key 오름차순으로 정렬 후 반환.
    */

        HashMap<String, Integer> person = new HashMap<>();
        for (List<String> history : arr) {

            person.putIfAbsent(history.get(0), 0);
            person.putIfAbsent(history.get(1), 0);
        }

        for (List<String> history : arr) {
            person.put(history.get(0), person.get(history.get(0)) - Integer.parseInt(history.get(2)));
            person.put(history.get(1), person.get(history.get(1)) + Integer.parseInt(history.get(2)));
        }

        List<Person> result = person.entrySet().stream()
                                    .filter(p -> p.getValue() < 0)
                                    .sorted(Comparator.comparing(p -> p.getKey()))
                                    .sorted(Comparator.comparing(p -> p.getValue()))
                                    .map(p -> new Person(p.getKey(), p.getValue()))
                                    .collect(Collectors.toList());
        if (result.isEmpty()) {
            return Collections.singletonList("None");
        } else {
            List<String> minimumPointNames = new ArrayList<>();
            int minimumPoint = result.get(0).point;
            minimumPointNames.add(result.get(0).name);
            for (int i = 1 ; i < result.size(); ++i) {
                if (result.get(i).point == minimumPoint) {
                    minimumPointNames.add(result.get(i).name);
                }
            }
            return minimumPointNames;
        }
    }



    public static void main(String[] args) {
        List<List<String>> arr = new ArrayList<>();
        arr.add(Arrays.asList("A", "C",  "10"));
        arr.add(Arrays.asList("B", "D",  "11"));
        System.out.println(solution(arr));
    }
}