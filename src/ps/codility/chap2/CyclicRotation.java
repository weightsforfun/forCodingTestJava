package ps.codility.chap2;

import java.util.*;

public class CyclicRotation {
    public int[] solution(int[] A, int K) {
        // Implement your solution here
        int n=A.length;
        Deque<Integer> deq=new ArrayDeque<>();
        if(n==0){
            return new int[] {};
        }
        for(int a:A){
            deq.addLast(a);
        }
        for(int i=0;i<K;i++){
            int temp=deq.pollLast();
            deq.addFirst(temp);
        }

        int [] answer=new int[n];

        for(int i=0;i<n;i++){
            answer[i]=deq.pollFirst();
        }
        return answer;

    }

}
