package com.coding.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1926 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(solution(bufferedReader));
		bufferedReader.close();
	}

	public static String solution(BufferedReader bufferedReader) throws IOException {
		StringTokenizer matrixTokenizer = new StringTokenizer(bufferedReader.readLine());

		int height = Integer.parseInt(String.valueOf(matrixTokenizer.nextToken()));
		int width = Integer.parseInt(String.valueOf(matrixTokenizer.nextToken()));
		int [][]canvas = new int[height][width];

		for (int i = 0; i < height; ++i) {
			StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < width; ++ j) {
				canvas[i][j] = Integer.parseInt(String.valueOf(tokenizer.nextToken()));
			}
		}

		int areaMaxSize = 0;
		int pictureCount = 0;

		for (int x = 0; x < height; ++x) {
			for (int y = 0; y < width; ++y) {
				if (canvas[x][y] == 1) {
					canvas[x][y] = -1;
					++pictureCount;
					int areaSize = 1 + findOne(x, y, canvas);
					if (areaMaxSize < areaSize) {
						areaMaxSize = areaSize;
					}
				}
			}
		}
		return String.format("%d\n%d", pictureCount, areaMaxSize);
	}

	public static int findOne(int x, int y, int [][]canvas) {
		int foundOneDirectionCount = 0;

		if (x - 1 > -1 && canvas[x - 1][y] == 1) { //위
			canvas[x - 1][y] = -1;
			foundOneDirectionCount += (1 + findOne(x - 1, y, canvas));
		}

		if (y + 1 < canvas[x].length && canvas[x][y + 1] == 1) { //우
			canvas[x][y + 1] = -1;
			foundOneDirectionCount += (1 + findOne(x, y + 1, canvas));
		}

		if (x + 1 < canvas.length && canvas[x + 1][y] == 1) { //아래
			canvas[x + 1][y] = -1;
			foundOneDirectionCount += (1 + findOne(x + 1, y, canvas));
		}

		if (y - 1 > -1 && canvas[x][y - 1] == 1) { //좌
			canvas[x][y - 1] = -1;
			foundOneDirectionCount += (1 + findOne(x, y - 1, canvas));
		}

		return foundOneDirectionCount;
	}
}