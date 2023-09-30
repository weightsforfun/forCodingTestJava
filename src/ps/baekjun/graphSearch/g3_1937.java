package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;
public class g3_1937 {
    public static  int[][] arr;
    public static  int[][] dp;
    public static int[] dy={1,-1,0,0};
    public static int[] dx={0,0,1,-1};
    public static int n;

    public static void main(String[] args)throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        arr=new int[n][n];
        dp=new int[n][n];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(dp[i][j]==0){
                    dp[i][j]=dfs(i,j);
                }

            }
        }

//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                System.out.print(" "+dp[i][j]);
//            }
//            System.out.println(" ");
//        }

        int answer=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                answer=Math.max(answer,dp[i][j]);
            }
        }
        System.out.println(answer);
    }
    public static int dfs(int y,int x){
        if(dp[y][x]!=0){
            return dp[y][x];
        }
        dp[y][x]=1;
        for(int i=0;i<4;i++){
            int ny=y+dy[i];
            int nx=x+dx[i];
            if(ny>=0 && ny<n && nx>=0 && nx<n && arr[ny][nx]>arr[y][x]){
                dp[y][x]=Math.max(dp[y][x],(dfs(ny,nx)+1));
            }
        }
        return dp[y][x];
    }

}
