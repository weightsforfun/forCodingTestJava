package ps.codility.chap3;

public class TapeEquilibrium {
    public int solution(int[] A) {
        // Implement your solution here
        int n=A.length;
        for(int i=1;i<n;i++){
            A[i]+=A[i-1];
        }
        int answer=100000001;
        for(int i=0;i<n-1;i++){
            answer=Math.min(answer,Math.abs(A[n-1]-2*A[i]));
        }
        return answer;
    }

}
