package programmers.Practice.Level2;

public class LandGrabbing {
    private static int solution(int[][] land) {
        for (int row = 1; row < land.length; row++) {
            for (int col = 0; col < 4; col++) {
                int max = 0;
                for (int prevCol = 0; prevCol < 4; prevCol++) {
                    if (col == prevCol) continue;
                    max = Math.max(max, land[row - 1][prevCol]);
                }
                land[row][col] += max;
            }
        }
        int result = 0;
        for (int col = 0; col < 4; col++) {
            result = Math.max(result, land[land.length - 1][col]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] land = {
            {1,2,3,5},
            {5,6,7,8},
            {4,3,2,1}
        };
        int answer = solution(land);
        System.out.print(answer);
    }
}
