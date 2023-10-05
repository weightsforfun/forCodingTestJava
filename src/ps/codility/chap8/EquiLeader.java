package ps.codility.chap8;

class EquiLeader {
    public int solution(int[] A) {
        // Implement your solution here
        int size=0;
        int value=0;
        for(int a: A){
            if(size==0){
                value=a;
                size++;
            }
            else{
                if(value==a){
                    size++;
                }
                else{
                    size--;
                }
            }
        }
        int candidate=-1;
        if(size>0){
            candidate=value;
        }
        int count=0;
        for(int a:A){
            if(value==a) count++;
        }
        int leader=0;
        if(count>A.length/2){
            leader=candidate;
        }
        else{
            return 0;
        }
        int left=0;
        int right=count;
        int answer=0;
        for(int i=0;i<A.length;i++){
            if(A[i]==leader){
                left++;
                right--;
            }
            if(left>((i+1)/2) && right>((A.length-i-1)/2)) answer++;
        }
        return answer;
    }

}