package com.coding.hackerrank.kakao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Session_Review {


	public static int solution(List<Integer> start_time, List<Integer> running_time) {
		// Write your code here

		//init
		LinkedList<int[]> matrix = new LinkedList<>();
		for(int i = 0; i < start_time.size(); ++i) {
			int []runningArray = new int[10000];
			int startTime = start_time.get(i);
			int runningTime = running_time.get(i);

			for (int j = startTime; j < startTime + runningTime; ++j) {
				runningArray[j] = runningTime;
			}
			matrix.add(runningArray);
		}

		//1. 두개이상겹치는거 list에서 삭제
		//2. 한개이상겹치는거 둘중 긴 길이를 가진 세션 삭제
		//3. 리스트 사이즈 반환
		int rows = 0;
		do {
			for (int cols = 0; cols < 10000; ++cols) {
				if (matrix.get(rows)[cols] != 0) {



					int crossCount = 0;
					int maximumRunningTime = matrix.get(rows)[cols];
					int maximumRunningTimeRows = rows;

					for (int otherRows = 0 ; otherRows < matrix.size(); ++otherRows) {
						int otherRunningTime = matrix.get(otherRows)[cols];
						if (otherRunningTime != 0 && rows != otherRows) { //겹치는게있는지?
							++crossCount;
							if (maximumRunningTime < otherRunningTime) {
								maximumRunningTime = otherRunningTime;
								maximumRunningTimeRows = otherRows;
							}
						}
					}

					if (crossCount > 1) {
						matrix.remove(rows);
						--rows;
					} else if (crossCount == 1) {
						matrix.remove(maximumRunningTimeRows);
						if (maximumRunningTimeRows == rows) {
							--rows;
						}
					}




				}
			}
			++rows;
		} while (rows < matrix.size());

		return matrix.size();
	}

	public static void main(String[] args) throws IOException {
		List<Integer> start = Arrays.asList(1, 5, 3);
		List<Integer> running = Arrays.asList(3, 1, 3);

		System.out.println(solution(start, running));
	}
}

