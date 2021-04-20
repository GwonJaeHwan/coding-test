package com.coding.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ11403 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int [][] result = solution(bufferedReader);
		for (int i = 0; i < result.length; ++i) {
			for (int j = 0; j < result[i].length; ++j) {
				System.out.print(result[i][j] + (result[i].length - 1 == j ? "" : " "));
			}
			System.out.println();
		}
		bufferedReader.close();
	}

	public static int[][] solution(BufferedReader bufferedReader) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
		int nodeCount = Integer.parseInt(String.valueOf(tokenizer.nextToken()));
		int [][]adjacencyMatrix = new int[nodeCount][nodeCount];

		for (int i = 0; i < nodeCount; ++i) {
			tokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < nodeCount; ++j) {
				adjacencyMatrix[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		/*
		 * 1. 결과용 매트릭스를 하나 동일하게 만듦
		 * 2. 인접행렬의 n*n을 각각 순회하면서 (동일한 수면 무조건 통과) 갈수있는 경로가있는지 검사
		 * 3. 검사는 스택에하나 푸시하고, 반복문시작: 하나팝하고 방문true 하고 하나 넣은 노드의 인접 노드 다 푸시함.(이게 한싸이클)
		 * 4. 시작값과 끝값을 저장해두고 시작값정점기준으로 DFS하면되고 (인접노드 푸시할때 발견 하면될듯)
		 */
		int [][]resultMatrix = new int[nodeCount][nodeCount];
		Stack<Integer> stack = new Stack<>();
		for (int rows = 0; rows < nodeCount; ++rows) {
			for (int cols = 0; cols < nodeCount; ++cols) {
				if (adjacencyMatrix[rows][cols] == 1) {
					resultMatrix[rows][cols] = 1;
				} else {
					boolean []visitCheck = new boolean[nodeCount];
					int searchRowsNode = rows;
					int searchColsNode = cols;
					boolean isFound = false;
					stack.push(searchRowsNode);

					while (!stack.isEmpty()) {
						int middleNode = stack.pop();

						if(!visitCheck[middleNode]) {
							visitCheck[middleNode] = true;

							for (int endNode = 0; endNode < nodeCount; ++endNode) {
								if (middleNode != endNode && adjacencyMatrix[middleNode][endNode] == 1) {
									if (endNode == searchColsNode) {
										resultMatrix[searchRowsNode][searchColsNode] = 1;
										isFound = true;
										stack.clear();
										break;
									}
									stack.push(endNode);
								}
							}
						}
					}
					if (!isFound) {
						resultMatrix[searchRowsNode][searchColsNode] = 0;
					}
				}
			}
		}

		return resultMatrix;
	}
}