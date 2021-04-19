package com.coding.programmers.level2;

public class CorrectlyBracket {
	public static void main(String[] args) {
		String s = "()()";
		System.out.println(solution(s));
	}
	
	public static boolean solution(String s) {
		int n = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') ++n;
			else if (s.charAt(i) == ')') --n;
			
			if (n < 0)
				return false;
		}
		return n == 0;
	}
}
