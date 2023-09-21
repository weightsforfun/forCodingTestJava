package ps.codility.chap3;

public class PermMissingElem {

    public int solution(int[] A) {
        // Implement your solution here
        long n=A.length;
        long sum=0;
        for(int a:A){
            sum+=a;
        }
        return (int)((n+1)*(n+2)/2-sum);
    }

}
