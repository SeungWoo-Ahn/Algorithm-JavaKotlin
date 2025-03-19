package pccp.exam4;

public class Num1 {
    private static int getTime(String time) {
        int mm = Integer.parseInt(time.substring(0, 2));
        int ss = Integer.parseInt(time.substring(3, 5));
        return mm * 60 + ss;
    }

    private static String getTime(int time) {
        int mm = time / 60;
        int ss = time % 60;
        StringBuilder sb = new StringBuilder();
        if (mm < 10) sb.append('0');
        sb.append(mm).append(':');
        if (ss < 10) sb.append('0');
        sb.append(ss);
        return sb.toString();
    }

    private static int prev(int curTime) {
        return Math.max(curTime - 10, 0);
    }

    private static int next(int curTime, int totalTime) {
        return Math.min(curTime + 10, totalTime);
    }

    private static int jump(int curTime, int stTime, int enTime) {
        if (stTime <= curTime && curTime <= enTime) {
            return enTime;
        }
        return curTime;
    }

    private static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int totalTime = getTime(video_len);
        int curTime = getTime(pos);
        int stTime = getTime(op_start);
        int enTime = getTime(op_end);
        curTime = jump(curTime, stTime, enTime);
        for (String command : commands) {
            if (command.equals("prev")) {
                curTime = prev(curTime);
                curTime = jump(curTime, stTime, enTime);
            } else if (command.equals("next")) {
                curTime = next(curTime, totalTime);
                curTime = jump(curTime, stTime, enTime);
            }
        }
        return getTime(curTime);
    }

    public static void main(String[] args) {
        String video_len = "10:55";
        String pos = "00:05";
        String op_start = "00:15";
        String op_end = "06:55";
        String[] commands = {"prev", "next", "next"};
        String answer = solution(video_len, pos, op_start, op_end, commands);
        System.out.print(answer);
    }
}
