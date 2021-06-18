package com.coding.codility.woowabrothers;

public class Q1 {

	public static void main(String[] args) {
		int []C = {2, 0, 2, 0};
		System.out.println(solution(2, 2, C));
	}

	/*
    1. C 에서 2 와 0은 확정 할수있음. 문제는 1일경우 upper냐 lower이냐임.
    2. row size 가 2인 결과용 2차원 배열을 만듦.
    3. C를 통해 2, 0은 확정지어 값 할당, 1인경우는 1또는 0임으로 이를 -1을 할당함으로써 표시 (-1이 안나오면 추가연산없이 그대로 결과만들어서 반환)
    4. U, L로부터 결과 배열에 추가적으로 할당해줘야하는 개수 추출
    5. -1인 곳은 열관점으로 순회하면서 열에서 하나의 row만 할당될수있음을 생각하고있어야함.
    6. 그리고 U, L로부터 추가적으로 할당해줘야하는 개수만큼 열의 개수가 존재해야함.(부족할시 바로 IMPOSSIBLE 리턴!)
    */
	public static String solution(int U, int L, int[] C) {
		int [][]result = new int[2][C.length];
		boolean needAdditionalOperation = false;
		int assignedOneCount = 0;
		int unassignedColumnCount = 0;

		for (int i = 0; i < C.length; ++i) {
			int initValue;
			if (C[i] == 0) {
				initValue = 0;
			} else if(C[i] == 2) {
				++assignedOneCount;
				initValue = 1;
			} else {
				++unassignedColumnCount;
				initValue = -1;
				needAdditionalOperation = true;
			}
			result[0][i] = initValue;
			result[1][i] = initValue;
		}

		if(needAdditionalOperation) { // 추가연산 필요시 추가연산 후 결과값 만들고 리턴
			int needAssignUpperCount = U - assignedOneCount;
			int needAssignLowerCount = L - assignedOneCount;

			int totalNeedAssignUpperCount = needAssignUpperCount + needAssignLowerCount;

			if (unassignedColumnCount < totalNeedAssignUpperCount) {
				return "IMPOSSIBLE";
			}

			// 열로 순회해서 -1인경우 배열에 0 또는 1 을 할당해줌
			// upper 부터 필요한 개수들 할당 -> 다했으면 lower 쪽 필요한개수 1로할당
			for (int i = 0; i < C.length; ++i) {
				if (C[i] == 1) {
					if (needAssignUpperCount > 0) {
						result[0][i] = 1;
						result[1][i] = 0;
						--needAssignUpperCount;
						continue;
					}

					if (needAssignLowerCount > 0) {
						result[0][i] = 0;
						result[1][i] = 1;
						--needAssignLowerCount;
					}
				}
			}
			for (int i = 0; i < C.length * 2; ++i) { //남아있는 -1은 0으로 처리
				int row = i / C.length;
				int col = i >= C.length ? i - C.length : i;
				if (result[row][col] == -1) {
					result[row][col] = 0;
				}
			}
		}

		//결과값 연산
		StringBuilder resultString = new StringBuilder();

		for (int i = 0; i < C.length * 2; ++i) {
			int row = i / C.length;
			int col = i >= C.length ? i - C.length : i;
			if (result[row][col] == 0) {
				resultString.append("0");
			} else {
				resultString.append("1");
			}
			if (i == C.length - 1) {
				resultString.append(",");
			}
		}

		return resultString.toString();
	}
}


/*
1)
1 ? ? 0 ?  add: 2
1 ? ? 0 ?  add: 1

1 0 1 0 1
1 1 0 0 0

2)
0 0 ? ? 1   add: 1
0 0 ? ? 1   add: 2

3)
1 0 1 0
1 0 1 0
*/

