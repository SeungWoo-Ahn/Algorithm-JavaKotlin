package programmers.Practice.Level2;

import java.util.Arrays;

public class SkillTree {
    private static final int[] parents = new int[26];

    private static void fillParents(String skill) {
        Arrays.fill(parents, -1);
        for (int i = 1; i < skill.length(); i++) {
            int parent = skill.charAt(i - 1) - 'A';
            int child = skill.charAt(i) - 'A';
            parents[child] = parent;
        }
    }

    private static boolean isPossibleSkillTree(String skillTree) {
        boolean[] used = new boolean[26];
        for (int i = 0; i < skillTree.length(); i++) {
            int child = skillTree.charAt(i) - 'A';
            int parent = parents[child];
            if (parent >= 0 && !used[parent]) {
                return false;
            }
            used[child] = true;
        }
        return true;
    }

    private static int solution(String skill, String[] skillTrees) {
        fillParents(skill);
        int result = 0;
        for (String skillTree: skillTrees) {
            if (isPossibleSkillTree(skillTree)) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skillTrees = {"BACDE", "CBADF", "AECB", "BDA"};
        int answer = solution(skill, skillTrees);
        System.out.print(answer);
    }
}
