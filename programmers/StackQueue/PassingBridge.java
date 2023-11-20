package programmers.StackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class PassingBridge {

    /**
     *
     * @param bridge_length 다리에 올라갈 수 있는 트럭 수, 한 대가 지나는 시간
     * @param weight 이하 무게까지 버틸 수 있음
     * @param truck_weights 트럭 무게들
     * @return 총 경과 시간
     */
    static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>();
        int bridge_weight = 0;
        for(int truck_weight : truck_weights) {
            while(true) {
                if(bridge.isEmpty()) { // 1. 다리가 비어있을 때
                    bridge.offer(truck_weight);
                    bridge_weight += truck_weight;
                    answer++;
                     break;
                } else if(bridge.size() == bridge_length) { // 2. 다리가 가득 차있을 때
                    bridge_weight -= bridge.poll();
                } else { // 3. 다리에 다른 트럭이 있지만 들어갈 수 있을 때
                    if(bridge_weight + truck_weight <= weight) { // 3-1. 들어갈 수 있을 때
                        bridge.offer(truck_weight);
                        bridge_weight += truck_weight;
                        answer++;
                        break;
                    } else { //3-2. 들어갈 수 없을 때
                        bridge.offer(0);
                        answer++;
                    }
                }
            }
        }
        return answer + bridge_length;
    }
    public static void main(String[] args) {
        int[] truck_weights = {7,4,5,6};
        System.out.println(solution(2, 10, truck_weights));
    }
}
