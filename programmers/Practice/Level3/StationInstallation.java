package programmers.Practice.Level3;

import java.util.ArrayList;
import java.util.List;

public class StationInstallation {
    static int solution(int n, int[] stations, int w) {
        List<Integer> spaces = new ArrayList<>();
        int len = stations.length;
        int firstSpace = stations[0] - w - 1;
        if (firstSpace > 0) spaces.add(firstSpace);
        int lastSpace = n - (stations[len - 1] + w + 1) + 1;
        if (lastSpace > 0) spaces.add(lastSpace);
        for (int i = 1; i < len; i++) {
            int space = (stations[i] - w - 1) - (stations[i - 1] + w + 1) + 1;
            if (space > 0) spaces.add(space);
        }
        int answer = 0;
        int range = w * 2 + 1;
        for (int space: spaces) {
            int cnt = space / range;
            if (space % range != 0) cnt++;
            answer += cnt;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 11;
        int[] stations = {4, 11};
        int w = 1;
        int answer = solution(n, stations, w);
        System.out.print(answer);
    }
}
