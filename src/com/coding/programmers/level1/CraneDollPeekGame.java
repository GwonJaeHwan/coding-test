package com.coding.programmers.level1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CraneDollPeekGame {
    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(solution(board, moves));
    }

    public static int solution(int[][] board, int[] moves) {
        List<Stack<Integer>> dollStackList = new ArrayList<>();
        Stack<Integer> bucket = new Stack<>();
        int count = 0;

        for (int i = 0; i < board.length; i++) {
            Stack<Integer> dollStack = new Stack<>();
            for (int j = board.length - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    dollStack.push(board[j][i]);
                }
            }
            dollStackList.add(dollStack);
        }

        for (int i = 0; i < moves.length; i++) {
            int peekIdx = moves[i];
            if (!dollStackList.get(peekIdx - 1).isEmpty()) {
                Integer popDoll = dollStackList.get(peekIdx - 1).pop();

                if (!bucket.isEmpty()) {
                    if (popDoll.equals(bucket.peek())) {
                        bucket.pop();
                        count += 2;
                    } else {
                        bucket.push(popDoll);
                    }
                } else {
                    bucket.push(popDoll);
                }
            }

        }

        return count;
    }
}
