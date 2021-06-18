package com.coding.codility.woowabrothers;

import java.util.Arrays;

public class Q4 {
/*
버그를 픽스해야할 코드

class Solution {
    int solution(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;;
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0;
        for (int k = 0; k < n; k++) {
            if (i < m - 1 && B[i] < A[k])  -> if 를 while로 바꿔주면됨, b 배열을 탐색하는 인덱스가 올바른 탐색을 하기위해 인덱스 연산을 조건에 맞게 계속 해줘야하기때문
                i += 1;
            if (A[k] == B[i])
                return A[k];
        }
        return -1;
    }
}


 */
	public static void main(String[] args) {
		int []A = {2, 1};
		int []B = {3, 3};
		System.out.println(solution(A, B));
	}

	static int solution(int[] A, int[] B) {
		int aSize =  A.length;
		int bSize = B.length;;
		Arrays.sort(A);
		Arrays.sort(B);
		int bIndex = 0;

		for (int aIndex = 0; aIndex < aSize; aIndex++) {
			while (bIndex < bSize - 1 && B[bIndex] < A[aIndex]) {
				bIndex += 1;
			}

			if (A[aIndex] == B[bIndex])
				return A[aIndex];
		}
		return -1;
	}
}
// A: 1 1 2 3
// B: 2 2 3 4 5
/*if (bIndex < bSize - 1 && B[bIndex] < A[aIndex])
				bIndex += 1;*/