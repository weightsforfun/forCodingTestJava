package ps.codility.chap2;

import java.util.*;

public class OddOccurrencesInArray {
    public int solution(int[] A) {
        // Implement your solution here
        Set<Integer> set= new HashSet<>();
        for(int a:A){
            if(!set.contains(a)){
                set.add(a);
            }
            else{
                set.remove(a);
            }
        }
        int answer=0;

        for(int a: set){
            answer=a;
        }

        return answer;
    }

}
