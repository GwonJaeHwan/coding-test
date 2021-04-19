package com.coding.programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoyStick {

    public static void main(String[] args) {
        System.out.println(solution("JAN"));
    }

    public static class WrapIntValue {
        public int head;
        public int count;
    }

    public static int solution(String name) {
        /**
         1. 위,아래
         N으로 가려면 + 13 or - 13 으로해야함 아무거나 상관없음.
         그러나 N 보다 아스키코드값이 작으면 위로,
         많으면 아래로 가야함.

         2. 좌,우
         다음으로 이동할 위치는 좌우측 중 A 가아닌 문자를 찾되 가려는 거리가 가장 짧은 위치로 이동함.

         3. 최초 위아래실행 후 좌우실행
         **/

        List<String> characterList = new ArrayList(Arrays.asList(name.split("")));
        WrapIntValue wrapIntValue = new WrapIntValue();
        do {
            char peekChar = characterList.get(wrapIntValue.head).charAt(0);
            if (peekChar < 'N') {
                wrapIntValue.count += (peekChar - 'A');
            } else if (peekChar > 'N') {
                wrapIntValue.count += (91 - peekChar);
            } else {
                wrapIntValue.count += 13;
            }
            characterList.set(wrapIntValue.head, "A");
        } while (moveLeftOrRight(characterList, wrapIntValue));

        return wrapIntValue.count;
    }

    public static boolean moveLeftOrRight(List<String> characterList, WrapIntValue wrapIntValue) {
        int rightIdx = wrapIntValue.head;
        int leftIdx = wrapIntValue.head;
        int leftCnt = 0;
        boolean existsRightValueOtherThenA = false;
        boolean existsLeftValueOtherThenA = false;

        for (rightIdx++; rightIdx < characterList.size(); rightIdx++) {
            if (characterList.get(rightIdx).charAt(0) != 'A') {
                existsRightValueOtherThenA = true;
                break;
            }
        }

        int initLeftIdx = leftIdx;
        do {
            leftIdx--;
            leftCnt++;
            if (leftIdx == -1) {
                leftIdx = characterList.size() - 1;
            }
            if (characterList.get(leftIdx).charAt(0) != 'A') {
                existsLeftValueOtherThenA = true;
                break;
            }
        } while ((leftIdx - 1 == -1? characterList.size() - 1: leftIdx - 1) != initLeftIdx);

        if (existsLeftValueOtherThenA && existsRightValueOtherThenA) {
            if ((rightIdx - wrapIntValue.head) > leftCnt) {
                wrapIntValue.count += leftCnt;
                wrapIntValue.head = leftIdx;
            } else {
                wrapIntValue.count += (rightIdx - wrapIntValue.head);
                wrapIntValue.head = rightIdx;
            }
            return true;
        } else if (existsLeftValueOtherThenA) {
            wrapIntValue.count += leftCnt;
            wrapIntValue.head = leftIdx;
            return true;
        } else if (existsRightValueOtherThenA) {
            wrapIntValue.count += (rightIdx - wrapIntValue.head);
            wrapIntValue.head = rightIdx;
            return true;
        }
        return false;
    }
}
