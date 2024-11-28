package pccp.exam2;

import java.util.Arrays;

public class Num1 {
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    private static int turnRight(int dir) {
        return (dir + 1) % 4;
    }

    private static int turnLeft(int dir) {
        return (dir + 3) % 4;
    }

    private static void go(int dir, int[] result) {
        result[0] += dx[dir];
        result[1] += dy[dir];
    }

    private static void back(int dir, int[] result) {
        int oDir = (dir + 2) % 4;
        result[0] += dx[oDir];
        result[1] += dy[oDir];
    }

    private static int[] solution(String command) {
        int[] result = new int[2];
        int dir = 0;
        for (int i = 0; i < command.length(); i++) {
            char ch = command.charAt(i);
            switch (ch) {
                case 'R': dir = turnRight(dir);
                    break;
                case 'L': dir = turnLeft(dir);
                    break;
                case 'G': go(dir, result);
                    break;
                case 'B': back(dir, result);
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String command = "GRGLGRG";
        int[] answer = solution(command);
        System.out.print(Arrays.toString(answer));
    }
}
