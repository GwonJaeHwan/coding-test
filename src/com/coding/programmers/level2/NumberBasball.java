package com.coding.programmers.level2;

import java.util.*;
import java.util.stream.IntStream;

public class NumberBasball {

    public static void main(String[] args) {
        int [][]baseball = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};
        System.out.println(solution(baseball));
    }

    public static int solution(int[][] baseball) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap();
        IntStream.range(123, 988)
                .forEach(i -> {
                        String str = String.valueOf(i);
                        if (str.charAt(0) != str.charAt(1)
                                && str.charAt(1) != str.charAt(2)
                                && str.charAt(0) != str.charAt(2)
                                && str.charAt(0) != '0'
                                && str.charAt(1) != '0'
                                && str.charAt(2) != '0') {
                            map.put(i, 0);
                        }
                });

        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            String keyStr = String.valueOf(key);
            char []keyCharArr = keyStr.toCharArray();
            for (int []baseball_ : baseball) {
                char []baseballArr = String.valueOf(baseball_[0]).toCharArray();
                int strike = 0;
                int ball = 0;
                for (int i = 0; i < 3; i++) {
                    if (keyCharArr[i] == baseballArr[i]) {
                        strike++;
                    } else {
                        for (int j = 0; j < 3; j++) {
                            if (i == j) continue;
                            if (keyCharArr[i] == baseballArr[j]) ball++;
                        }
                    }
                }
                if (baseball_[1] == strike && baseball_[2] == ball) {
                    map.put(key, map.get(key).intValue() + 1);
                }
            }
        }

        return (int)keySet.stream()
                .filter(key -> map.get(key) == baseball.length)
                .count();
    }
}
