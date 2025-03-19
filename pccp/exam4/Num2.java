package pccp.exam4;

public class Num2 {
    private static long simulation(int level, int[] diffs, int[] times) {
        long usedTime = 0L;
        if (diffs[0] <= level) {
            usedTime += times[0];
        } else {
            usedTime += (long) (diffs[0] - level + 1) * times[0];
        }
        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                usedTime += times[i];
            } else {
                usedTime += (long) (diffs[i] - level) * (times[i] + times[i - 1]) + times[i];
            }
        }
        return usedTime;
    }

    private static int getMaxDiff(int[] diffs) {
        int maxDiff = 0;
        for (int diff : diffs) {
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        return maxDiff;
    }

    private static int solution(int[] diffs, int[] times, long limit) {
        int st = 1;
        int en = getMaxDiff(diffs);
        while (st <= en) {
            int mid = (st + en + 1) / 2;
            long usedTime = simulation(mid, diffs, times);
            if (usedTime > limit) {
                st = mid + 1;
            } else {
                en = mid - 1;
            }
        }
        return st;
    }

    public static void main(String[] args) {
        int[] diffs = {1, 99999, 100000, 99995};
        int[] times = {9999, 9001, 9999, 9001};
        long limit = 3456789012L;
        int answer = solution(diffs, times, limit);
        System.out.print(answer);
    }
}
