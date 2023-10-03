package ps.softeer.backtracking;

import java.util.*;
import java.io.*;


public class l3_260913
{
    public static int n;
    public static int m;
    public static int [][] arr;
    public static int [][] visited;
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
    public static int answer;
    public static Node[] des;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        arr=new int[n][n];
        visited=new int[n][n];
        des=new Node[m];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int y=Integer.parseInt(st.nextToken())-1;
            int x=Integer.parseInt(st.nextToken())-1;
            des[i]=new Node(y,x);
        }

        visited[des[0].y][des[0].x]=1;
        dfs(des[0],0);

        System.out.println(answer);

    }
    public static void dfs(Node now, int depth){
        if(now.y==des[depth].y && now.x==des[depth].x){
            if(depth==des.length-1){
                answer++;
                return ;
            }
            depth++;

        }
        for(int i=0;i<4;i++){
            int ny=now.y+dy[i];
            int nx=now.x+dx[i];
            if(ny>=0 && ny<n && nx>=0 && nx<n && visited[ny][nx]==0 && arr[ny][nx]==0){
                visited[ny][nx]=1;
                dfs(new Node(ny,nx),depth);
                visited[ny][nx]=0;
            }
        }
    }
}
