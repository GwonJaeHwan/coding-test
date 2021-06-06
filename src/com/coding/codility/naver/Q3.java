package com.coding.codility.naver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//발생횟수 리스트를 만들어,
// 발생횟수 중복되는걸 찾아.
//리스트에 중복되지않게 발생횟수 중복되던걸를 조작해
//조작하면서 삭제된 개수 반환~
//근데 제약도많으니 1개조차도 못가지고있으면 다삭제.. 되는겨~
public class Q3 {
	public static void main(String[] args) {
		System.out.println(solution("example")); //1
	}

	public static int solution(String S) {
		Map<String, Integer> frequency = new HashMap<>();
		String []s = S.split("");
		for (int i = 0; i < s.length; ++i) {
			Integer count = frequency.get(s[i]);
			frequency.put(s[i], count == null? 1 : count + 1);
		}

		List<Integer> duplicateCheckCount = new ArrayList<>();
		List<Integer> duplicateCounts = frequency.entrySet().stream().filter(e -> {
			if (duplicateCheckCount.contains(e.getValue())) {
				return true;
			}
			duplicateCheckCount.add(e.getValue());
			return false;
		}).map(e -> e.getValue()).collect(Collectors.toList());

		List<Integer> noDuplicateCheckCount = new ArrayList<>();
		List<Integer> noDuplicateCounts = frequency.entrySet().stream().filter(e -> {
			if (noDuplicateCheckCount.contains(e.getValue())) {
				return false;
			}
			noDuplicateCheckCount.add(e.getValue());
			return true;
		}).map(e -> e.getValue()).collect(Collectors.toList());
		noDuplicateCounts.remove(duplicateCounts);
		int deletedCount = 0;
		for (int i = 0; i < duplicateCounts.size(); ++i) {
			int duplicateCount = duplicateCounts.get(i);
			do {
				--duplicateCount;
				++deletedCount;
			} while (duplicateCount != 0 && noDuplicateCounts.contains(duplicateCount));

			if (duplicateCount != 0) {
				noDuplicateCounts.add(duplicateCount);
			}
		}
		return deletedCount;
	}

}
