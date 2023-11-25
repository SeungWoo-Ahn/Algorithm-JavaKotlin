package programmers.KaKao;

import java.util.Arrays;
import java.util.List;

public class Winter_2023 {

    /**
     * @param friends 친구들의 이름들 2~50개, 알파벳 소문자 길이 10 이하, 이름 같은 친구는 없다
     * @param gifts 주고받은 선물 기록, "A B" 형태, "A"는 선물을 준 친구, "B"는 받은 친구
     * @return 선물을 가장 많이 받을 친구가 받을 선물의 수
     */
    static int solution(String[] friends, String[] gifts) {
        int len = friends.length;
        List<String> friendsArr = Arrays.asList(friends);
        int[][] giftTable = new int[len][len];
        int[][] friendTable = new int[len][2];

        for(String gift: gifts) {
            String giver = gift.split(" ")[0];
            String taker = gift.split(" ")[1];
            int giverIdx = friendsArr.indexOf(giver);
            int takerIdx = friendsArr.indexOf(taker);
            giftTable[giverIdx][takerIdx]++;
            friendTable[giverIdx][0]++;
            friendTable[takerIdx][1]++;
        }

        int[] results = new int[len];
        for(int a=0; a<len; a++) {
            for(int b=0; b<len; b++) {
                if(a == b) continue;
                int AtoB = giftTable[a][b];
                int BtoA = giftTable[b][a];
                if(AtoB != BtoA) {
                    if(AtoB > BtoA) results[a]++;
                    else results[b]++;
                } else {
                    int aScore = friendTable[a][0] - friendTable[a][1];
                    int bScore = friendTable[b][0] - friendTable[b][1];
                    if(aScore > bScore) results[a]++;
                    else if(bScore > aScore) results[b]++;
                }
            }
        }

        int answer = 0;
        for(int i: results) {
            if(i > answer) {
                answer = i;
            }
        }

        return answer/2;
    }

    public static void main(String[] args) {
        String[] friends = {"a", "b", "c"};
        String[] gifts = {"a b","b a","c a","a c", "a c", "c a"};
        System.out.println(solution(friends, gifts));
    }
}
