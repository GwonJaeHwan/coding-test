package com.coding.codility.woowabrothers;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Q2 {

	public static void main(String[] args) {
		System.out.println(solution("my.song.mp3 11b\ngreatSong.flac 1000b\nnot3.txt 5b\nvideo.mp4 200b\ngame.exe 100b\nmov!e.mkv 10000b"));
	}

	public enum Extension {
		MUSIC(0), IMAGE(1), MOVIE(2), OTHER(3);

		private static List<String> musicExtensions = Arrays.asList("mp3", "aac", "flac");
		private static List<String> imageExtensions = Arrays.asList("jpg", "bmp", "gif");
		private static List<String> movieExtensions = Arrays.asList("mp4", "avi", "mkv");

		private int printOrder;

		Extension(int printOrder) {
			this.printOrder = printOrder;
		}

		public static Extension from(String extension) {
			if (musicExtensions.contains(extension)) {
				return MUSIC;
			} else if (imageExtensions.contains(extension)) {
				return IMAGE;
			} else if (movieExtensions.contains(extension)) {
				return MOVIE;
			} else {
				return OTHER;
			}
		}
	}

	public static String solution(String S) {
		int []result = new int[4];
		StringTokenizer linesTokenizer = new StringTokenizer(S, "\n");

		while (linesTokenizer.hasMoreTokens()) {
			StringTokenizer lineTokenizer = new StringTokenizer(linesTokenizer.nextToken(), " ");
			String fileName = lineTokenizer.nextToken();
			fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
			int fileSize = Integer.parseInt(lineTokenizer.nextToken().replace("b", ""));

			Extension extension = Extension.from(fileName.substring(fileName.lastIndexOf(".") + 1));
			result[extension.printOrder] = result[extension.printOrder] + fileSize;
		}

		StringBuilder resultStringBuilder = new StringBuilder();

		for (int i = 0; i < result.length; ++i) {
			switch (i) {
				case 0:
					resultStringBuilder.append("music ");
					break;
				case 1:
					resultStringBuilder.append("images ");
					break;
				case 2:
					resultStringBuilder.append("movies ");
					break;
				case 3:
					resultStringBuilder.append("other ");
					break;
			}
			resultStringBuilder.append(result[i]);
			resultStringBuilder.append("b\n");
		}
		String resultString = resultStringBuilder.toString();
		return resultString.substring(0, resultString.length() -1);
	}
}

/*
        1. S는 각 라인마다 파일이름과 사이즈가 space로 구분됨.
        2. 라인을 순회(토크나이저사용하자)하면서 왼쪽의 토큰(파일명)의 가장 마지막 도트를 기준으로 확장자를 분리하자
        3. 오른쪽 토큰(사이즈)의 b를 빼고 정수값을 추출해 얻어진 확장자에 사이즈값 add
        4. 결과값 문자열로 반환
        */
