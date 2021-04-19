package com.coding.programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Carpet {
    public static void main(String[] args) {
        int brown = 8;
        int yellow = 1;//1 3 9
        Arrays.stream(solution(brown, yellow))
                .forEach(System.out::println);
        /*
            1. brown, yellow의 합(넓이 또는 타일개수)의 약수를 구함.
            2. 약수 짝들 리스트로 만든 후 완전탐색 시작
            3. brown, yellow의 합이 될 수 있는 약수짝(x, y)을 구해서 x,y 각각 2차감 -> (x-2) * (y-2) = 주어진 yellow 의 개수가 되는것의 약수 짝(x, y)이 정답
        */
    }

    public static int[] solution(int brown, int yellow) {
        List<List<Integer>> divisorList = makeDivisorList(brown + yellow);
        for (List<Integer> divisor : divisorList) {
            int divisorX = divisor.get(0);
            int divisorY = divisor.get(1);
            if (divisorX >= 3 && divisorY >= 3) {
                if ((divisorX - 2) * (divisorY - 2) == yellow) {
                    int[] answer = {divisorX, divisorY};
                    return answer;
                }
            }

        }
        return null;
    }

    private static List<List<Integer>> makeDivisorList(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
            }
        }
        List<List<Integer>> divisorList = new ArrayList();
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int leftValue = list.get(left);
            int rightValue = list.get(right);
            divisorList.add(leftValue < rightValue
                    ? Arrays.asList(rightValue, leftValue)
                    : Arrays.asList(leftValue, rightValue));
            left++;
            right--;
        }
        return divisorList;
    }
}
