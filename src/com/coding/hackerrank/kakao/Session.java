package com.coding.hackerrank.kakao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Session {


	public static int solution(List<Integer> start_time, List<Integer> running_time) {
		// Write your code here
        /*
        1.겹치는 건 하나 놔두고 무조건 버림, 대신 짧은 러닝타임을 가지는것을 선택함
        2. 각 start타임을 인덱스 (1 부터시작하자) 로 러닝타임을 리스트로가지는 컬렉션을 만듦
        3. 각 인덱스의 컬렉션사이즈가 2개이상인것을 체크하여 가장 짧은 러닝타임을가진 요소만 취하고 나머지는 버림
        4. 순회하면서 다른세션 시작시간 침범여부 체크
        */

		List<Integer>[] session = new ArrayList[1000];

		for (int i = 0 ; i < start_time.size(); ++i) {
			if (session[start_time.get(i)] == null) {
				session[start_time.get(i)] = new ArrayList<>();
			}
			session[start_time.get(i)].add(running_time.get(i));
		}

		for (int i = 0; i < start_time.size(); ++i) {
			if (session[start_time.get(i)].size() > 1) {
				List<Integer> elemets = session[start_time.get(i)];
				Collections.sort(elemets);
				List<Integer> newer = new ArrayList<>();
				newer.add(elemets.get(0));
				session[start_time.get(i)] = newer;
			}
		}
		boolean isBlocks[] = new boolean[session.length];
		//마지막엔 투르값 개수랑 기존 리스트개수에서 차감해서 반환
		for (int i = 1; i < 1000; ++i) {
			if (session[i] == null) continue;
			int startTime = i;

			if(isBlocks[startTime]) {
				continue;
			}
			int runningTime = session[startTime].get(0);

			List<Integer> crosses = new ArrayList<>();
			for (int j = startTime + 1; j < 1000; ++j) {
				if (session[j] == null) continue;
				int otherStartTime = j;
				if (otherStartTime < startTime + runningTime) { //이 값보다 뒤에 애들중에 시작 시간이 작은 애들이 있으면 그 애들 카운트
					crosses.add(otherStartTime);
				}
			}

			if (crosses.size() >= 2) {
				isBlocks[startTime] = true;    //2개이상겹치면 본인이 블락, 1개겹치면 상대방 블락
			} else if (crosses.size() == 1) {
				isBlocks[crosses.get(0)] = true; //  1개겹치면 상대방 블락
			}
		}

		int blockCount = 0;
		for (boolean isblock : isBlocks) {
			if (isblock) {
				++blockCount;
			}
		}

		int sessionCount = 0;
		for (List item : session) {
			if (item != null) {
				++sessionCount;
			}
		}

		return sessionCount - blockCount;
		//짧게하는것으로 값 1개만가지도록 필터링함 - 순회1
		//이후 짧게하더라도 순회하면서 앞에값이 다름 세션시작에 값에 영향을 준다면? 영향받은 값은 제외 계속체크해야함. - 순회2
		//진행하면서 자기 세션 시간이 다른곳 침범하는 곳 isBlocks로 인덱스에 체크함.
		//그리고 침범을 두개이상할경우 자신의 세션이 제외됨.

	}

	public static void main(String[] args) throws IOException {
		List<Integer> start = Arrays.asList(948,
											386,
											218,
											273,
											540,
											248,
											386,
											497,
											886,
											624,
											421,
											145,
											969,
											736,
											916,
											626,
											535,
											43,
											12,
											680,
											153,
											245,
											296);
		List<Integer> running = Arrays.asList(819,
											  397,
											  693,
											  816,
											  992,
											  34,
											  670,
											  398,
											  554,
											  548,
											  826,
											  211,
											  663,
											  212,
											  809,
											  378,
											  762,
											  626,
											  336,
											  869,
											  996,
											  777,
											  768);

		System.out.println(solution(start, running));
	}
}

