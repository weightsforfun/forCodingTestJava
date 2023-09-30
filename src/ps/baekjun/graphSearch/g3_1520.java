package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g3_1520 {
    public static class Node{
        int y;
        int x;
        public Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    public static int[] dy={1,-1,0,0};
    public static int[] dx={0,0,1,-1};
    public static int[][] dp;
    public static int[][] arr;
    public static int n;
    public static int m;

    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        arr= new int[n][m];
        dp=new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0,0));
//        for(int [] d:dp){
//            for(int p:d){
//                System.out.print(" "+p);
//            }
//            System.out.println(" ");
//        }
    }
    public static int dfs(int y,int x){
        if(y==n-1 && x==m-1){
            return 1;
        }
        else if(dp[y][x]!=-1){
            return dp[y][x];
        }
        dp[y][x]=0;
        for(int i=0;i<4;i++){
            int ny=y+dy[i];
            int nx=x+dx[i];
            if(ny>=0 && ny<n && nx>=0 && nx<m && arr[ny][nx]<arr[y][x]){
                dp[y][x]+=dfs(ny,nx);
            }
        }
        return dp[y][x];
    }
}
