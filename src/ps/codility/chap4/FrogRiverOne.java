package ps.codility.chap4;

public class FrogRiverOne {
    public int solution(int X, int[] A) {
        // Implement your solution here
        int[] arr=new int[X+1];
        int n=A.length;
        int time=0;
        for(int i=0;i<n;i++){
            int a=A[i];
            if(arr[a]==0){
                arr[a]=1;
                time++;
            }
            if(time==X){
                return i;
            }
        }
        return -1;
    }

}
