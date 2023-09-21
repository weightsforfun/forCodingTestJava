package ps.codility.chap6;

import java.util.*;

public class Distinct {
    public int solution(int[] A) {
        // Implement your solution here
        Set<Integer> set=new HashSet<>();

        for(int a:A){
            set.add(a);
        }

        return set.size();
    }

}
