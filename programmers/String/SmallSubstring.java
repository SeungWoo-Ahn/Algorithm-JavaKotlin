package programmers.String;

public class SmallSubstring {

    /**
     * p와 같은 길이의 t의 부분문자열 중에서, p보다 같거나 작은 것의 개수
     * p의 길이가 1~18까지임을 조심하자
     */
    static int solution(String t, String p) {
        int answer = 0;
        int pLength = p.length();
        long pInt = Long.parseLong(p);
        for(int i=0; i<t.length()-pLength+1; i++) {
            long subInt = Long.parseLong(t.substring(i, i+pLength));
            if(subInt <= pInt) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("3141592", "271"));
    }
}
