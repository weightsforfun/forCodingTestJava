package ps.codility.chap9;
class MaxSliceSum {
    public int solution(int[] A) {
        // Implement your solution here
        int sum=0;
        int answer=Integer.MIN_VALUE;
        for(int a:A){
            sum=Math.max(a,sum+a);
            answer=Math.max(answer,sum);
        }
        return answer;
    }
}