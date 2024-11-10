package programmers.Practice.Level4;

import java.util.HashSet;
import java.util.Set;

public class BuyCookie {
    private static int solution(int[] cookie) {
        int sum = 0;
        for (int cnt: cookie) {
            sum += cnt;
        }
        Set<Integer> result = new HashSet<>();
        int max = sum / 2;
        for (int mid = 0; mid <cookie.length; mid++) {
            int[] cnt = {0, cookie[mid]};
            int[] index = {mid, mid};
            while (true) {
                if (cnt[0] < cnt[1]) { // 첫째 양이 적은 경우, 왼쪽의 쿠키 더해줌.
                    if (--index[0] < 0) break;
                    cnt[0] += cookie[index[0]];
                } else { // 둘째 양이 적거나 같은 경우, 오른쪽의 쿠키 더해줌.
                    if (++index[1] >= cookie.length) break;
                    cnt[1] += cookie[index[1]];
                }
                if (cnt[0] == cnt[1]) {
                    if (cnt[0] == max) {
                        return max;
                    } else {
                        result.add(cnt[0]);
                    }
                }
            }
        }
        int maxCnt = 0;
        for (int cnt: result) {
            if (cnt > maxCnt) {
                maxCnt = cnt;
            }
        }
        return maxCnt;
    }

    public static void main(String[] args) {
        int[] cookie = {1,1,2,3};
        int answer = solution(cookie);
        System.out.print(answer);
    }
}
