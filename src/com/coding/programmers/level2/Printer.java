package com.coding.programmers.level2;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Printer {

    public static void main(String[] args) {
        int[] prioities = {1, 1, 9, 1, 1, 1};
        System.out.println(solution(prioities, 0));
    }

    public static int solution(int[] priorities, int location) {

        Queue<Integer> priorityQueue = new LinkedList(Arrays.stream(priorities)
                .boxed()
                .collect(Collectors.toList()));

        int count = 0;

        do {
            if (priorityQueue.peek() >= Collections.max(priorityQueue)) {
                priorityQueue.poll();
                location--;
                count++;
                if (location == -1) { // 내가 요청한 문서가 맨 앞에있던 거라면
                    return count;
                }
            } else {
                Integer head = priorityQueue.poll();
                location--;
                priorityQueue.offer(head);
                if (location == -1) { // 내가 요청한 문서가 맨 앞에있던 거라면
                    location = priorityQueue.size() - 1;
                }
            }
        } while (!priorityQueue.isEmpty());
        return 0;
    }
}