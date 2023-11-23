package programmers.BruthForce;

public class Fatigue {

    static boolean[] visited;
    static int answer = 0;

    /**
     *
     * @param k 현재 피로도
     * @param dungeons [i][0] : 최소 요구 피로도, [i][1] : 소모 피로도
     * @return 탐험할 수 있는 최대 던전 수
     * 최소 피로도 >= 소모 피로도, 모두 1~1000
     * 서로 다른 던전의 피로도 쌍이 같을 수 있음
     */
    static int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        backTracking(0, k, dungeons);
        return answer;
    }

    static void backTracking(int depth, int k, int[][] dungeons) {
        for(int i = 0; i< dungeons.length; i++) {
            if(visited[i] || dungeons[i][0] > k) continue;
            visited[i] = true;
            backTracking(depth + 1, k - dungeons[i][1], dungeons);
            visited[i] = false;
        }
        answer = Math.max(answer, depth);
    }

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80,20}, {50,40}, {30,10}};
        System.out.println(solution(k, dungeons));
    }
}
