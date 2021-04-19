package com.coding.programmers.level2;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {
        int []citations = {0, 0, 0, 0};
        System.out.println(solution(citations));
    }

    public static int solution(int[] citations) {
        Arrays.sort(citations);
        int h = citations[citations.length - 1];
        while(true) {
            if (h == 0) return 0;
            for (int i = citations.length - 1; i > -1; i--) {
                if (i != 0 && citations[i] == citations[i - 1]) continue;
                if (citations[i] >= h && h == citations.length - i && i + 1 <= h) {
                    return h;
                } else if (citations[i] < h || i == 0) {
                    h--;
                    break;
                }
            }
        }
    }
}
