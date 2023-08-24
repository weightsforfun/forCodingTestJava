package ps.programmers.binarySearch;

public class l3_43238 {

    public long solution(int n, int[] times) {
        long answer ;
        long start=0;
        long end=1_000_000_000_000_000_000L;

        // for(int time: times){
        //     end+=((long)time * (long)n);
        // }
        answer=end+1;
        while(start<=end){
            long middle=(start+end)/2;
            long total=0;


            for(int time:times){
                total=total+(middle/time);
            }

            // System.out.println("start "+start+" end "+end+" total "+total);

            if(total>=n){
                answer=Math.min(middle,answer);
                end=middle-1;
                // System.out.println("answer "+answer);
            }
            else{
                start=middle+1;
            }
        }

        return answer;
    }


}
