package com.coding.programmers.level2;

import java.util.Arrays;

public class LifeBoat {
    public static void main(String[] args) {
        int []people = {70, 50, 80, 50};

        int limit = 100;
        System.out.println(solution(people, limit));
    }


    public static int solution(int[] people, int limit) {
        /*
        1. 오름차순으로 정렬함, 양끝의 몸무게 합이 limit 이하이면 양끝의 사람 보트에 탑승가능, boatCount 2 증가
        2. limit 이하가 아니라면 우측끝의 사람만 보트에 탑승시킴(우측끝의 몸무게는 좌측끝의 가장 작은 몸무게랑
        매칭시켜본것이므로 좌측에 앞으로 더 작은 값이 나올 경우가 없음) boatCount 1 증가
        3. 1명만 남는다면 보트 탑승, boatCount 1 증가
        */
        Arrays.sort(people);
        int boatCount = 0;
        int left = 0;
        int right = people.length - 1;
        int size = people.length;
        do {
            if (size == 1) {
                boatCount++;
                break;
            } else if (people[left] + people[right] <= limit) {
                left++;
                right--;
                size -= 2;
                boatCount++;
            } else {
                right--;
                size--;
                boatCount++;
            }
        } while (size != 0);
        return boatCount;
    }
}
