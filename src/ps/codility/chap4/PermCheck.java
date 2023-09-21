package ps.codility.chap4;

import java.util.*;

public class PermCheck {
    public int solution(int[] A) {
        // Implement your solution here
        Set<Integer> set=new HashSet<>();

        for(int a:A){
            set.add(a);
        }

        for(int i=1;i<A.length+1;i++){
            if(!set.contains(i)){
                return 0;
            }
        }
        return 1;
    }

}
