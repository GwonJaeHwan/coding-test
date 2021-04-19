package com.coding.programmers.level2;

import java.util.*;

public class StringCompression {
    public static void main(String[] args) {
        String s = "xababcdcdababcdcd";
        System.out.println(solution(s));
    }

    public static class SingleStringSet {
        private String value;


        public String getValue() {
            return this.value;
        }

        public boolean putValue(String value) { // true 면 중복값
            if (this.value == null || !this.value.equals(value)) {
                this.value = value;
                return false;
            }
            else {
                return true;
            }
        }

        public void initValue() {
            this.value = null;
        }
    }

    public static int solution(String s) {
        System.out.println(s);
        List<Integer> sizeList = new ArrayList<>();
        SingleStringSet set = new SingleStringSet();

        if (s.length() < 3) {
            return s.length();
        }
        //aa bb ac cc
        //01 23 45 67
        for (int unit = 1; unit <= s.length() - 1; unit++) {
            int duplicateCnt = 1;
            String result = "";
            String preString = "";

            int i = 0;
            //ab ab ab // (unit == 1? i : i + unit - 1)
            for (; i < (s.length() / unit) * unit ; i += unit) {
                preString = set.getValue(); //이전 데이터 저장
                boolean duplicate = set.putValue(s.substring(i, i + unit)); //현재 인덱스가 가리키는 값 셋에 저장
                if (duplicate) { //앞서 저장된값이랑 같다면?
                    duplicateCnt++; // 중복 수치 증가
                } else { //같지 않다면
                    if (i != 0 && duplicateCnt == 1) { //근데 처음이 아니라면.. 그리고 중복수치가 1이라면
                        result = result + preString;   // 앞에그냥 문자열붙임
                        duplicateCnt = 1;               //중복수치 초기화
                    }
                    else if (i != 0 && duplicateCnt > 1){ // 중복수치 1보다크다면 중복수치랑같이 문자열 붙임
                        result = result + duplicateCnt + preString;
                        duplicateCnt = 1;
                    }
                }
            }
            if (duplicateCnt == 1) {
                result += set.getValue();
            } else {
                result = result + duplicateCnt + preString;
            }
            if (unit > 1 && i == (s.length() / unit) * unit) {
                result += s.substring(i);
            }

            set.initValue();
            System.out.println("단위 : " + unit + "," + result);
            sizeList.add(result.length());
        }
        return Collections.min(sizeList);
    }
}
