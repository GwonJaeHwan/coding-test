package com.coding.hackerrank.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Paging {

	public static List<List<Object>> makeObjectTypeList(List<List<String>> items) {
		List<List<Object>> objects = new ArrayList<>();
		for (List<String> i : items) {
			objects.add(Arrays.asList(i.get(0), Integer.valueOf(i.get(1)), Integer.valueOf(i.get(2))));
		}
		return objects;
	}

	public static List<String> solution(List<List<String>> items, int orderBy, int orderDirection, int pageSize, int pageNumber) {
		// Write your code here
		/*
		* 1. items순회 정렬 기준을 orderBy값으로 분기함, orderDirection 값으로도 차순 분기함.
		  2. pageSize, pageNumber로 결과값 컬렉트함.
		*/

			List<List<Object>> objects = makeObjectTypeList(items);
			return objects.stream()
						.sorted((o1, o2) -> {
							if (orderBy == 0) {
								if (orderDirection == 0) {
									return ((String)o1.get(0)).compareTo((String)o2.get(0));
								} else {
									return ((String)o2.get(0)).compareTo((String)o1.get(0));
								}
							} else {
								if (orderDirection == 0) {
									return ((Integer) o1.get(orderBy)) - ((Integer) o2.get(orderBy));
								} else {
									return ((Integer) o2.get(orderBy)) - ((Integer) o1.get(orderBy));
								}
							}
						})
						.map(item -> (String) item.get(0))
						.skip(pageNumber * pageSize)
						.limit(pageSize).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		List<List<String>> arr = new ArrayList<>();
		arr.add(Arrays.asList("item1", "10",  "15"));
		arr.add(Arrays.asList("item2", "3",  "4"));
		arr.add(Arrays.asList("item3", "17",  "8"));
		System.out.println(solution(arr, 0, 0, 2, 0));
	}
}

