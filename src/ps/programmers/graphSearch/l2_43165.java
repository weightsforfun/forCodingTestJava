package ps.programmers.graphSearch;

public class l2_43165 {
    public static int tar;
    public static int count=0;
    public static int length;
    public static int [] nums;
    public void dfs(int depth,int total){
        if(depth==length){
            if(total==tar){
                count++;
            }
        }
        else{
            dfs(depth+1,total+nums[depth]);
            dfs(depth+1,total-nums[depth]);
        }
    }
    public int solution(int[] numbers, int target) {
        int answer = 0;
        tar=target;
        length=numbers.length;
        nums=numbers;
        dfs(0,0);
        answer=count;
        return answer;
    }
}
