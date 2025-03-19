package pccp.exam4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Num3 {
    private static int getPos(int[] point) {
        return point[0] * 100 + point[1];
    }

    private static ArrayList<Integer>[] getPosList(int x) {
        ArrayList<Integer>[] posList = new ArrayList[x];
        for (int i = 0; i < posList.length; i++) {
            posList[i] = new ArrayList<>();
        }
        return posList;
    }

    private static int solution(int[][] points, int[][] routes) {
        int x = routes.length;
        ArrayList<Integer>[] posList = getPosList(x);
        for (int i = 0; i < x; i++) {
            int firstIdx = routes[i][0] - 1;
            int[] firstPoint = points[firstIdx];
            int[] cur = new int[2];
            posList[i].add(getPos(firstPoint));
            cur[0] = firstPoint[0];
            cur[1] = firstPoint[1];
            for (int j = 1; j < routes[i].length; j++) {
                int idx = routes[i][j] - 1;
                int[] target = points[idx];
                while (cur[0] != target[0] || cur[1] != target[1]) {
                    if (cur[0] > target[0]) cur[0]--;
                    else if (cur[0] < target[0]) cur[0]++;
                    else if (cur[1] > target[1]) cur[1]--;
                    else cur[1]++;
                    posList[i].add(getPos(cur));
                }
            }
        }
        int idx = 0;
        int crashCnt = 0;
        while (true) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < x; i++) {
                if (idx < posList[i].size()) {
                    int pos = posList[i].get(idx);
                    map.put(pos, map.getOrDefault(pos, 0) + 1);
                }
            }
            if (map.isEmpty()) break;
            for (int key : map.keySet()) {
                if (map.get(key) > 1) {
                    crashCnt++;
                }
            }
            idx++;
        }
        return crashCnt;
    }

    public static void main(String[] args) {
        int[][] points = {
            {3, 2},
            {6, 4},
            {4, 7},
            {1, 4}
        };
        int[][] routes = {
            {4, 2},
            {1, 3},
            {4, 2},
            {4, 3}
        };
        int answer = solution(points, routes);
        System.out.print(answer);
    }
}
