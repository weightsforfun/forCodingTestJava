package ps.codility.chap10;


class minPerimeterRectangle {
    public int solution(int N) {
        // Implement your solution here
        long answer=100000000000L;
        long i=1;
        while(i*i<=N){
            if(N%i==0){
                answer=Math.min(answer,(i+N/i)*2);
            }
            i++;
        }
        return (int)answer;
    }
}
