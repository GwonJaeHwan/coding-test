package com.coding.programmers.level2;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Cache {
	public static void main(String[] args) {
		int cacheSize = 3;
		String []cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		System.out.println(solution(cacheSize, cities));
	}
	
	public static int solution(int cacheSize, String[] cities) {
		/**
		 * 1. 도시 조회하기전 도시가 캐싱되어 linkedList에 있는지 확인. 있으면 hit ,해당 캐시값 인덱스를 꺼내서 다시 offer함
		 * 2. linkedList에 없으면. miss, 해당 도시 linkedList에 offer 함
		 * 3. linkedList에 size 는 cacheSize로 보면됨. 1번, 2번 수행시 linkedList size가 cacheSize보다 크면 poll 한번함.
		 */
		
		int executionTime = 0;
		LinkedList<String> cacheLinkedList = new LinkedList<>();
		for (int i = 0; i < cities.length; i++) {
			String peekedCity = cities[i].toLowerCase();
			int searchedIndex = cacheLinkedList.indexOf(peekedCity);
			if (searchedIndex == -1) {
				executionTime += 5;
				cacheLinkedList.offer(peekedCity);
			} else {
				executionTime += 1;
				String hitter = cacheLinkedList.get(searchedIndex);
				cacheLinkedList.remove(searchedIndex);
				cacheLinkedList.offer(hitter);
			}
			if (cacheLinkedList.size() > cacheSize) cacheLinkedList.poll();
		}
		return executionTime;
	}
}
