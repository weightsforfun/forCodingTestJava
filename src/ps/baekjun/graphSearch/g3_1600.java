package ps.baekjun.graphSearch;

import java.io.*;
import java.util.*;


public class g3_1600 {
    public static int[][][] bfs;
    public static int[][][] visited;
    public static int[][] arr;
    public static int[] dy={1,-1,0,0,2,2,-2,-2,1,-1,1,-1};
    public static int[] dx={0,0,1,-1,1,-1,1,-1,2,2,-2,-2};
    public static int w;
    public static int h;
    public static int k;
    public static class Node{
        int y;
        int x;
        int count;
        int move;
        public Node(int y, int x,int count,int move){
            this.y=y;
            this.x=x;
            this.count=count;
            this.move=move;
        }
    }
    public static void main(String [] args)throws IOException{

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        k=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        w=Integer.parseInt(st.nextToken());
        h=Integer.parseInt(st.nextToken());

        bfs=new int[h][w][k+1];
        visited=new int[h][w][k+1];
        arr=new int[h][w];
        for(int i=0;i<h;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<w;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        Deque<Node> deq=new ArrayDeque<>();
        deq.addLast(new Node(0,0,0,0));
        boolean flag=true;
        while(!deq.isEmpty()){
            Node now=deq.pollFirst();
            if(now.y==h-1 && now.x==w-1){
                System.out.println(now.move);
                flag=false;
                break;
            }
            for(int i=0;i<4;i++){
                int ny=now.y+dy[i];
                int nx=now.x+dx[i];
                if(check(ny,nx) && visited[ny][nx][now.count]==0 && arr[ny][nx]==0){
                    visited[ny][nx][now.count]=1;
                    deq.addLast(new Node(ny,nx,now.count,now.move+1));
                }
            }
            if(now.count==k) continue;
            for(int i=4;i<12;i++){
                int ny=now.y+dy[i];
                int nx=now.x+dx[i];
                if(check(ny,nx) && visited[ny][nx][now.count+1]==0 && arr[ny][nx]==0){
                    visited[ny][nx][now.count+1]=1;
                    deq.addLast(new Node(ny,nx,now.count+1,now.move+1));
                }
            }
        }
        if(flag){
            System.out.println(-1);
        }


    }
    public static boolean check(int y , int x){
        return y>=0 && y<h && x>=0 && x<w;
    }

}
