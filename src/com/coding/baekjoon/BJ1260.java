package com.coding.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * DFS와 BFS
 * https://www.acmicpc.net/problem/1260
 */
public class BJ1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer baseTokenizer = new StringTokenizer(bufferedReader.readLine());
        Integer nodeCount = Integer.valueOf(baseTokenizer.nextToken());
        Integer edgeCount = Integer.valueOf(baseTokenizer.nextToken());
        Integer startNode = Integer.valueOf(baseTokenizer.nextToken());
        ArrayList<Integer>[] adjacencyList = (ArrayList<Integer>[]) new ArrayList[nodeCount + 1];
        for (int i = 0; i < nodeCount + 1; ++i) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeCount; ++i) {
            StringTokenizer adjacencyListTokenizer = new StringTokenizer(bufferedReader.readLine());
            Integer node1 = Integer.valueOf(adjacencyListTokenizer.nextToken());
            Integer node2 = Integer.valueOf(adjacencyListTokenizer.nextToken());
            adjacencyList[node1].add(node2);
            adjacencyList[node2].add(node1);
        }

        dfs(adjacencyList, nodeCount, startNode);
        System.out.println();
        bfs(adjacencyList, nodeCount, startNode);

        bufferedReader.close();
    }

    public static void dfs(ArrayList<Integer>[] adjacencyList, Integer nodeCount, Integer startNode) throws IOException {

        for (ArrayList list : adjacencyList) { //작은값부터 방문하기위해서는 스택가장 나중에 쌓여야하기떄문에 미리 내림차순으로 정렬
            Collections.sort(list, Comparator.reverseOrder());
        }

        Stack<Integer> stack = new Stack<>();
        boolean []visitCheck = new boolean[nodeCount + 1];
        stack.push(startNode);

        while(!stack.isEmpty()) {
            int node = stack.pop();

            if (!visitCheck[node]) {
                visitCheck[node] = true;
                System.out.print(node + " ");

                for (int i : adjacencyList[node]) {
                    if (!visitCheck[i]) {
                        stack.push(i);
                    }
                }
            }
        }
    }

    public static void bfs(ArrayList<Integer>[] adjacencyList, Integer nodeCount, Integer startNode) throws IOException {
        for (ArrayList list : adjacencyList) {
            Collections.sort(list);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean []visitCheck = new boolean[nodeCount + 1];
        queue.offer(startNode);

        while(!queue.isEmpty()) {
            int node = queue.poll();

            if (!visitCheck[node]) {
                visitCheck[node] = true;
                System.out.print(node + " ");

                for (int i : adjacencyList[node]) {
                    if (!visitCheck[i]) {
                        queue.offer(i);
                    }
                }
            }
        }
    }
}

