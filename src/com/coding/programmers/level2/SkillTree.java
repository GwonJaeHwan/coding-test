package com.coding.programmers.level2;

import java.util.*;

public class SkillTree {
    public static void main(String[] args) {
        String skill = "CBD";
        String []skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        /**
         1. skill 큐에 넣음.
         2. skill_trees에서 하나씩 읽음
         3. 큐(리스트)가 비어있다면 그냥 가능한 스킬트리
         4. skill 큐(리스트)에서 해당 스킬트리가 존재는 하지만 가장 앞서 나올 스킬이아니라면 가능한 스킬트리가 아님
         + skill이 skill큐에 존재하지 않는 skill이라면 건너뜀
         5. 앞서나올 스킬이면 해당 큐 poll진행
         6. 큐에 존재하지않는 스킬이면 그냥 지나침 -> 다음꺼 검사함.
         7. 큐에 존재하지만 앞서나올 스킬이 아닌경우 불가능 스킬트리로 순회 종료
         **/
        int answer = 0;

        for (String skillTree : skill_trees) {
            boolean possible = false;
            int i = 0;
            Queue<String> skillQueue = new LinkedList(Arrays.asList(skill.split("")));
            for (; i < skillTree.length(); i++) {
                char skillChar = skillTree.charAt(i);
                String skillStr = String.valueOf(skillChar);
                if (skillQueue.isEmpty()) { //3번 조건
                    possible = true;
                    break;
                } else if (!skillQueue.contains(skillStr)) { // +
                    continue;
                } else if (skillQueue.contains(skillStr) && !skillQueue.peek().equals(skillStr)) { //4번 조건
                    break;
                } else if (skillQueue.peek().equals(skillStr)) { //5번 조건
                    skillQueue.poll();
                    continue;
                }
            }
            if (!possible) {
                possible = i == skillTree.length();//끝까지 수행완료
            }
            if (possible) {
                answer++;
            }
        }
        return answer;
    }
}
