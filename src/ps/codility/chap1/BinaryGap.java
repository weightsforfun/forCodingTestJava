package ps.codility.chap1;

import java.util.*;

public class BinaryGap {
    public int solution(int N) {
        // Implement your solution here
        String bi= Integer.toBinaryString(N);
        char [] arr=bi.toCharArray();

        int left=0;
        int right=0;
        int answer=0;

        while(right<arr.length){
            if(arr[right]=='1'){
                answer=Math.max(answer,right-left-1);
                left=right;
                right++;
            }
            else{
                right++;
            }
        }
        return answer;
    }

}
