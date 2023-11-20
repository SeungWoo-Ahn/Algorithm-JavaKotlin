package programmers.String;

public class SplitString {

    static int solution(String s) {
        int answer= 0;
        char x = s.charAt(0);
        int xCnt = 0, notXCnt = 0, index = 0;
        while(index < s.length()) {
            if(s.charAt(index) == x) xCnt++;
            else notXCnt++;
            index++;
            if(xCnt == notXCnt) {
                answer++;
                xCnt = 0;
                notXCnt = 0;
                if(index < s.length()) {
                    x = s.charAt(index);
                }
            }
        }
        if(!(xCnt == 0 && notXCnt == 0)) answer++;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("abracadabra"));
    }
}
