package ps.codility.chap12;

public class ChocolatesByNumber {
    public int solution(int N, int M) {
        // Implement your solution here
        int g=gcd(N,M);

        return N/g;

    }
    public int gcd(int a,int b){
        if(a%b==0){
            return b;
        }
        else{
            return gcd(b,a%b);
        }
    }

}
