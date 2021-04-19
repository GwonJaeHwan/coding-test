package com.coding.programmers.level2;

import java.util.Arrays;

public class GettingGround {
    public static void main(String[] args) {
        int [][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}, {100, 0, 9, 8}};
        System.out.println(solution(land));
    }

    public static int solution(int[][] land) {
       /*
        1. 인덱스 0이아닌 1행부터 n행까지 순회하며 각 n행의 열들도 순회.
        2. 이전행의 열중에 현재 열이아닌 열의 값중 가장 큰값과 현재행, 열의 값과 합하여 현재행, 열에 값으로 변경해줌(메모이제이션)
        3. n번째 행의 열중에 가장큰 값이 최대값임.
        */
       for (int i = 1; i < land.length; ++i)
           for (int j = 0; j < 4; ++j)
               land[i][j] = land[i][j] + getMaxValueWithExceptedOneIndex(land[i-1], j);
       return Arrays.stream(land[land.length - 1]).max().getAsInt();
    }

    private static int getMaxValueWithExceptedOneIndex(int []row, int exceptIndex) {
        int max = -1;
        for (int i = 0; i < row.length; ++i) {
            if (exceptIndex != i && max < row[i]) max = row[i];
        }
        return max;
    }
}
