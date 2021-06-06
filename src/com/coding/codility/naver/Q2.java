package com.coding.codility.naver;

public class Q2 {
	public static void main(String[] args) {
		System.out.println(solution("Forget  CVs..Save time . x x"));
	}

	public static int solution(String S) {
		String []sentences = S.split("[.?!]");
		int maximumWordCount = 0;
			for (int i = 0; i < sentences.length; ++i) {
			int wordCount = sentences[i].trim().replaceAll("  ", " ").split(" ").length;
			if (maximumWordCount < wordCount) {
				maximumWordCount = wordCount;
			}
		}
		return maximumWordCount;
	}

}
