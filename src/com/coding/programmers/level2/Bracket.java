package com.coding.programmers.level2;

public class Bracket {
    public static void main(String[] args) {
        String p = "(()())()";
        System.out.println(solution(p));
    }

    public static String solution(String p) {
        if (p.isEmpty()) {
            return "";
        }
        int balanceCutIdx = getPBalanceCutIdx(p);
        String u = p.substring(0, balanceCutIdx);
        String v = p.substring(balanceCutIdx);
        if (isPCorrectly(p)) {
            return u + solution(v);
        }else {
            String empty = "";
            empty.concat("(");
            empty.concat(solution(v));
            empty.concat(")");
            if (u.length() < 2) {
                u = "";
            } else {
                u = u.substring(1, u.length() - 1);
            }
            empty.concat(reverse(u));
            return empty;
        }
    }

    public static int getPBalanceCutIdx(String p) {
        int cutIdx = 0;
        int left = 0;
        int right = 0;
        for (int i=0; i < p.length(); i++) {
            char ch = p.charAt(i);
            if (ch == '(')
                left++;
            if (ch == ')')
                right++;
            cutIdx++;
            if (left == right)
                return cutIdx + 1;
        }
        return 0;
    }

    public static boolean isPCorrectly(String p) {
        int cnt = 0;
        for (int i=0; i < p.length(); i++) {
            char ch = p.charAt(i);
            if (ch == '(')
                cnt++;
            if (ch == ')')
                cnt--;
            if (cnt < 0) {
                return false;
            }
        }
        return true;
    }

    public static String reverse(String p) {
        return new StringBuilder(p).reverse().toString();
    }

}
