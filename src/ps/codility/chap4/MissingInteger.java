// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class MissingInteger {
    public int solution(int[] A) {
        // Implement your solution here
        Set<Integer> set=new HashSet<>();
        for(int a:A){
            set.add(a);
        }
        int answer=1000001;
        for(int i=1;i<1000001;i++){
            if(!set.contains(i)){
                answer=i;
                break;
            }
        }
        return answer;
    }
}