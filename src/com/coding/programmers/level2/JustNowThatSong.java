package com.coding.programmers.level2;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JustNowThatSong {
	public static void main(String[] args) {
		String m = "ABC";
		String []musicinfos = {"12:00,12:14,HELLO,ABCDEF", "13:00,13:14,WORLD,ABCDEF"};
		System.out.println(solution(m, musicinfos));
	}
	
	public static String solution(String m, String[] musicinfos) {
		List<String> answerList = new ArrayList<>();
		for (int i = 0; i < musicinfos.length; ++i) {
			String []elements = musicinfos[i].split(",");
			long playMinute = getPlayMinute(musicinfos[i]);
			
			StringBuilder fullScore = new StringBuilder();
			String score = substitutionPitchSharp(elements[3]);
			
			for (int j = 0; j < playMinute; ++j) {
				fullScore.append(score.charAt(j % score.length()));
			}
			if (fullScore.toString().contains(substitutionPitchSharp(m))) {
				answerList.add(musicinfos[i]);
			}
		}
		if (answerList.isEmpty()) {
			return "(None)";
		} else if (answerList.size() == 1) {
			return answerList.get(0).split(",")[2];
		} else {
			long max = getPlayMinute(answerList.get(answerList.size() - 1));
			int idx = answerList.size() - 1;
			for (int i = answerList.size() - 2; i > -1; --i) {
				long playMinute = getPlayMinute(answerList.get(i));
				if (playMinute >= max) {
					max = playMinute;
					idx = i;
				}
			}
			return answerList.get(idx).split(",")[2];
		}
	}
	
	private static String substitutionPitchSharp(String pitch) {
		return pitch.replaceAll("C#", "c")
					.replaceAll("D#", "d")
					.replaceAll("F#", "f")
					.replaceAll("G#", "g")
					.replaceAll("A#", "a");
	}
	
	private static long getPlayMinute(String musicinfos) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String []elements = musicinfos.split(",");
		LocalTime startTime = LocalTime.parse(elements[0], formatter);
		LocalTime endTime = LocalTime.parse(elements[1], formatter);
		return Duration.between(startTime, endTime).getSeconds() / 60;
	}
}
