package ps.codility.chap5;

public class PassingCars {
    public int solution(int[] A) {
        // Implement your solution here
        int answer=0;
        int east=0;
        for(int a:A){
            if(a==0){
                east++;
            }
            else{
                answer+=east;
            }
            if(answer>1000000000){
                return -1;
            }
        }
        return answer;
    }

}
