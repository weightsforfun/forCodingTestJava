package ps.codility.chap7;

import java.util.*;

public class Fish {
    public int solution(int[] A, int[] B) {
        // Implement your solution here
        int n=A.length;
        Deque<Integer> deq=new ArrayDeque<>();
        int answer=0;

        for(int i=0;i<n;i++){
            int size=A[i];
            int direction=B[i];
            if(direction==1){
                deq.addLast(size);
            }
            else{
                while(deq.size()!=0){
                    if(deq.getLast()<size){
                        deq.pollLast();
                    }
                    else{
                        break;
                    }
                }
                if(deq.size()==0){
                    answer++;
                }
            }
        }
        return answer+deq.size();
    }

}
