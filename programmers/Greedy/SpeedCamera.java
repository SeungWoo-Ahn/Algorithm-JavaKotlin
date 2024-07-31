package programmers.Greedy;

import java.util.Arrays;

class SpeedCamera {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int answer = 1;
        int top = routes[0][1];
        int bottom = routes[0][0];
        for (int i = 1; i < routes.length; i++) {
            if (bottom <= routes[i][0] && routes[i][0] <= top) {
                top = Math.min(top, routes[i][1]);
                bottom = routes[i][0];
            } else  {
                top = routes[i][1];
                bottom = routes[i][0];
                answer++;
            }
        }
        return answer;
    }
}

class Main {
    public static void main(String[] args) {
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int answer = new SpeedCamera().solution(routes);
        System.out.print(answer);
    }
}
