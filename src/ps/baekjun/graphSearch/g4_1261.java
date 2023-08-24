package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g4_1261 {
    public static class Node{
        int x;
        int y;
        int crash;
        public Node(int y,int x, int crash){
            this.x=x;
            this.y=y;
            this.crash=crash;
        }
    }
    public static int [][] move ={{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());

        int m=Integer.parseInt(st.nextToken());
        int n=Integer.parseInt(st.nextToken());

        int [][] arr =new int [n][m];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            String str=st.nextToken();
            for(int j=0;j<m;j++){
                arr[i][j]=(str.charAt(j)-'0');
            }
        }

        int [][] visited=new int[n][m];
        Deque<Node> deq=new ArrayDeque<>();
        deq.addLast(new Node(0,0,0));
        visited[0][0]=1;
        while(deq.size()!=0){
            Node current=deq.pollFirst();
            int c_x=current.x;
            int c_y=current.y;
            int c_crash=current.crash;
            // System.out.println("x "+c_x+" y "+c_y+" crash "+c_crash);
            if(c_y==n-1 && c_x==m-1){
                System.out.println(c_crash);
                break;
            }
            for(int i=0;i<4;i++){
                int dy=move[i][0];
                int dx=move[i][1];
                int n_y=c_y+dy;
                int n_x=c_x+dx;
                if(n_y>=0 && n_y<n && n_x>=0 && n_x<m && visited[n_y][n_x]==0){
                    if(arr[n_y][n_x]==0){
                        deq.addFirst(new Node(n_y,n_x,c_crash));
                    }
                    else{
                        deq.addLast(new Node(n_y,n_x,c_crash+1));
                    }
                    visited[n_y][n_x]=1;
                }
            }
        }


    }

}
