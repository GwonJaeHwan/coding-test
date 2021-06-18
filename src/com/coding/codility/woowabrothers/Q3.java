package com.coding.codility.woowabrothers;


import java.util.Arrays;
import java.util.Collections;

public class Q3 {

	public static void main(String[] args) {
		int[] A = {1, 2, 3, -4};

		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		Arrays.sort(A);
		for (int i = A.length - 1; A[i] > 0; --i) {
			int rightLargeNumber = A[i];
			int findNegativeNumber = -A[i];

			for (int j = 0; A[j] <= findNegativeNumber; ++j) {
				if (A[j] == findNegativeNumber) {
					return rightLargeNumber;
				}
			}
		}
		return 0;
	}
}

/*
 1. 주어진 배열을 오름차순 정렬한다.
 2. 가장큰 값부터 오른쪽 끝부터 순회 하되 오른쪽 값을 음수로 취한값을 왼쪽 끝에서부터 탐색하며 찾는다.
 */