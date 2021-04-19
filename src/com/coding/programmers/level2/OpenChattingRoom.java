package com.coding.programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenChattingRoom {
	
	public static void main(String[] args) {
		String [] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		solution(record);
	}
	
	public static String[] solution(String[] records) {
		List<String> answer = new ArrayList<>();
		Map<String, String> userMap = new HashMap<>();
		for (String record : records) {
			String []elements = record.split(" ");
			if (elements[0].equals("Enter")) {
				answer.add(elements[1] + "님이 들어왔습니다.");
				userMap.put(elements[1], elements[2]);
			} else if (elements[0].equals("Leave")) {
				answer.add(elements[1] + "님이 나갔습니다.");
			} else {
				userMap.put(elements[1], elements[2]);
			}
		}
		return answer.stream().map(i -> userMap.get(i.substring(0, i.indexOf('님'))) + i.substring(i.indexOf('님'))).toArray(String[]::new);
	}
}
