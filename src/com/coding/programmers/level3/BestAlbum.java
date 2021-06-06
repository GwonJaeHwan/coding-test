package com.coding.programmers.level3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BestAlbum {

	public static class PlayCount {
		public int count;
		public List<Genre> genres;

		public PlayCount(int count, List<Genre> genres) {
			this.count = count;
			this.genres = genres;
		}
	}

	public static class Genre {
		public int musicNo;
		public int plays;

		public Genre(int musicNo, int plays) {
			this.musicNo = musicNo;
			this.plays = plays;
		}

	}

	public static void main(String[] args) {
		String[] genres = {"A", "A", "B", "A", "B", "B", "A", "A", "A", "A"};
		int[] plays = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		int [] result = solution(genres, plays);
		for (int no : result) {
			System.out.print(no + " ");
		}
	}

	public static int[] solution(String[] genres, int[] plays) {
		//1.장르순서 구함 ->  링크드해시맵(장르-키, 값-플레이스랑 고유번호를담은 객체 컬렉션)
		//2.장르순서대로 가장많이 재생된곡 정렬후 2개씩 꺼내옴(재생횟수가 같다면 고유번호 오름차순기준으로고고)
		Map<String, PlayCount> albums = new HashMap<>();

		for (int i = 0; i < plays.length; ++i) {
			PlayCount playCount = albums.get(genres[i]);

			if (playCount == null) {
				playCount = new PlayCount(plays[i], new LinkedList<>());
				playCount.genres.add(new Genre(i, plays[i]));
				albums.put(genres[i], playCount);
			} else {
				playCount.genres.add(new Genre(i, plays[i]));
				playCount.genres.sort(((o1, o2) -> {
					int difference = o2.plays - o1.plays;
					if (difference == 0) {
						return o1.musicNo - o2.musicNo;
					}
					return difference;
				}));
				playCount.genres = playCount.genres.subList(0, 2);
				playCount.count += plays[i];
			}
		}

		return albums.values().stream().sorted((e1, e2) -> e2.count - e1.count).flatMap(a -> a.genres.stream()).map(g -> g.musicNo).mapToInt(i -> i).toArray();
	}
}
