package boj.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1193 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int sum = 0;
        int line;
        for(int i = 1;; i++) {
            sum += i;
            if(sum >= X) {
                line = i;
                break;
            }
        }
        int child;
        int parent;
        if(line %2 == 0) {
            child = line - (sum - X);
            parent = 1 + (sum - X);
        } else {
            child = 1 + (sum - X);
            parent = line - (sum - X);
        }
        System.out.println(child + "/" + parent);
    }
}
