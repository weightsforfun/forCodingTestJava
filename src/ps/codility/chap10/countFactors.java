package ps.codility.chap10;

class countFactors {
    public int solution(int N) {
        // Implement your solution here
        int answer=0;
        long i=1;
        while(i*i<N){
            if(N%i==0){
                answer+=2;
            }
            i++;
        }
        if(i*i==N){
            answer+=1;
        }
        return answer;
    }
}
