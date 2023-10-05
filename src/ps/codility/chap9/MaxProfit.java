package ps.codility.chap9;

// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class MaxProfit {
    public int solution(int[] A) {
        // Implement your solution here
        int small=200001;
        int answer=0;
        for(int a:A){
            small=Math.min(small,a);
            answer=Math.max(answer,a-small);
        }
        return answer;
    }
}