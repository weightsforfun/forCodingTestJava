package ps.codility.chap6;

import java.util.*;

public class MaxProductOfThree {
    public int solution(int[] A) {
        // Implement your solution here
        int n=A.length;
        Arrays.sort(A);
        return Math.max((A[0]*A[1]*A[n-1]),(A[n-1]*A[n-2]*A[n-3]));
    }

}
