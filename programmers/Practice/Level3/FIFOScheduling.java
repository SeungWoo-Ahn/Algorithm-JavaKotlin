package programmers.Practice.Level3;

public class FIFOScheduling {
    private static int process(int time, int[] cores) {
        int cnt = cores.length;
        for (int core: cores) {
            cnt += time / core;
        }
        return cnt;
    }

    private static int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }
        int lo = 1;
        int hi = n * 10_000;
        int time = 0;
        int work = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cnt = process(mid, cores);
            if (cnt >= n) {
                hi = mid - 1;
                time = mid;
                work = cnt;
            } else {
                lo = mid + 1;
            }
        }
        int lastCore = 0;
        work -= n;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) {
                if (work == 0) {
                    lastCore = i + 1;
                    break;
                }
                work--;
            }
        }
        return lastCore;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] cores = {1, 2, 3};
        int answer = solution(n, cores);
        System.out.print(answer);
    }
}
