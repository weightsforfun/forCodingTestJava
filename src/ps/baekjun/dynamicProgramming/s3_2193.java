package ps.baekjun.dynamicProgramming;

import java.util.*;
import java.io.*;

public class s3_2193 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());

        long answer=0L;
        long [][] dp=new long[n][2];
        if(n==1) {
            System.out.println(1);
            return ;
        }
        if(n==2){
            System.out.println(1);
            return ;
        }
        dp[0][0]=0;
        dp[0][1]=1;
        dp[1][0]=1;
        dp[1][1]=0;
        for(int i=2;i<n;i++){
            dp[i][0]=dp[i-1][1]+dp[i-1][0];
            dp[i][1]=dp[i-1][0];
        }
        System.out.println(dp[n-1][0]+dp[n-1][1]);

    }

}
