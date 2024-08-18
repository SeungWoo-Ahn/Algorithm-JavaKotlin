package programmers.Practice.Level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ShuttleBus {
    private static int getIntTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        return hour * 60 + minute;
    }

    private static String getTime(int intTime) {
        int hour = intTime / 60;
        int minute = intTime % 60;
        String hourStr, minuteStr;
        if (hour < 10) {
            hourStr = "0" + hour;
        } else {
            hourStr = Integer.toString(hour);
        }
        if (minute < 10) {
            minuteStr = "0" + minute;
        } else {
            minuteStr = Integer.toString(minute);
        }
        return hourStr + ":" + minuteStr;
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        List<Integer>[] wait = new List[n];
        for (int i = 0; i < n; i++) {
            wait[i] = new ArrayList<>();
        }
        int[] busTimes = new int[n];
        busTimes[0] = getIntTime("09:00");
        for (int i = 1; i < n; i++) {
            busTimes[i] = busTimes[i - 1] + t;
        }
        int busIdx = 0;
        int crewIdx = 0;
        while (crewIdx < timetable.length) {
            int crewTime = getIntTime(timetable[crewIdx]);
            int busTime = busTimes[busIdx];
            if (crewTime <= busTime && wait[busIdx].size() < m) {
                wait[busIdx].add(crewTime);
                crewIdx++;
            } else  {
                busIdx++;
            }
            if (busIdx == n) break;
        }
        int answer;
        List<Integer> last = wait[n - 1];
        if (last.isEmpty() || last.size() < m) {
            answer = busTimes[n - 1];
        } else {
            answer = last.getLast() - 1;
        }
        return getTime(answer);
    }

    public static void main(String[] args) {
        int n = 2;
        int t = 10;
        int m = 2;
        String[] timetable = {"09:10", "09:09", "08:00"};
        String answer = solution(n, t, m, timetable);
        System.out.print(answer);
    }
}
