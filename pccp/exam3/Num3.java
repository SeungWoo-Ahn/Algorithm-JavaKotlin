package pccp.exam3;

public class Num3 {
    /**
     * 시침은 60분에 30도 회전 -> 1초에 1/120도 회전
     * 분침은 1분에 6도 회전 -> 1초에 1/10도 회전
     * 초침은 1분에 360도 회전 -> 1초에 6도 회전
     * 각도를 float, double로 표현하면, 소수부 연산에서 오차가 발생할 가능성이 생김
     * 그래서 int를 사용하는 각도로 표현 -> 한 바퀴가 360 * 120도인 각도 세타로 설정
     * 초당 각도를 세타로 재설정하면,
     * 시침은 1초에 1세타 회전
     * 분침은 1초에 12세타 회전
     * 초침은 1초에 720세타 회전
     */
    private static final int KEY = 360 * 120;

    private static  int getTime(int hh, int mm, int ss) {
        return hh * 3_600 + mm * 60 + ss;
    }

    private static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int time = getTime(h1, m1, s1);
        int endTime = getTime(h2, m2, s2);
        int hr = time % KEY; // 초기 시침 각도
        int mr = time * 12 % KEY; // 초기 분침 각도
        int sr = time * 720 % KEY; // 초기 초침 각도
        boolean beforeH = sr < hr; // 초침이 시침 이전에 있는지
        boolean beforeM = sr < mr; // 초침이 분침 이전에 있는지
        int result = 0;
        if (sr == mr || sr == hr) {
            result++;
        }
        while (time < endTime) {
            hr += 1;
            mr += 12;
            sr += 720;
            boolean nxtBeforeH = sr < hr;
            boolean nxtBeforeM = sr < mr;
            if (hr == mr) {
                if (beforeH != nxtBeforeH || beforeM != nxtBeforeM) {
                    result++;
                }
            } else {
                if (beforeH != nxtBeforeH) result++;
                if (beforeM != nxtBeforeM) result++;
            }
            hr %= KEY;
            mr %= KEY;
            sr %= KEY;
            beforeH = sr < hr;
            beforeM = sr < mr;
            time++;
        }
        return result;
    }

    public static void main(String[] args) {
        int h1 = 0, m1 = 0, s1 = 0;
        int h2 = 23, m2 = 59, s2 = 59;
        int answer = solution(h1, m1, s1, h2, m2, s2);
        System.out.print(answer);
    }
}
