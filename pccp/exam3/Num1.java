package pccp.exam3;

public class Num1 {
    private static int solution(int[] bandage, int health, int[][] attacks) {
        int idx = 0;
        int success = 0;
        int time = 0;
        int rest = health;
        while (idx < attacks.length) {
            if (time == attacks[idx][0]) {
                rest -= attacks[idx][1];
                if (rest <= 0) {
                    return -1;
                }
                success = 0;
                idx++;
            } else {
                int heal = bandage[1];
                success++;
                if (success == bandage[0]) {
                    heal += bandage[2];
                    success = 0;
                }
                rest = Math.min(rest + heal, health);
            }
            time++;
        }
        return rest;
    }

    public static void main(String[] args) {
        int[] bandage = {5, 1, 5};
        int health = 30;
        int[][] attacks = {
            {2, 10},
            {9, 15},
            {10, 5},
            {11, 5}
        };
        int answer = solution(bandage, health, attacks);
        System.out.print(answer);
    }
}
