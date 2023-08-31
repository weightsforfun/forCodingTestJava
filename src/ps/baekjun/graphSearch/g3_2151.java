package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g3_2151 {
    public static class Node {

        int x;
        int y;
        int direction;
        int count;

        public Node(int y, int x, int direction, int count) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.count = count;
        }
    }
    public static int[][] move={{-1,0},{0,-1},{1,0},{0,1}};
    public static int[][][] visited;

    public static int answer;

    public static int n;

    public static Character[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st= new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        arr=new Character[n][n];

        answer=251;

        int sy = 0;
        int sx=0;
        int ey=0;
        int ex=0;
        boolean flag=true;
        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            String temp=st.nextToken();
            for(int j=0;j<n;j++){
                arr[i][j]=temp.charAt(j);
                if(temp.charAt(j)=='#' && flag){
                        sy=i;
                        sx=j;
                        flag=false;

                }
                else if(temp.charAt(j)=='#' && !flag){
                    ey=i;
                    ex=j;
                }
            }
        }


        bfs(sy,sx,ey,ex);
        bfs(ey,ex,sy,sx);
        System.out.println(answer);


    }
    public static void bfs(int sy,int sx,int ey, int ex){
//        System.out.println("end.y = " + end.y);
//        System.out.println("end.x = " + end.x);
        visited=new int[n][n][4];

        PriorityQueue<Node> pq=new PriorityQueue<>((o1,o2)->o1.count-o2.count);
        for(int i=0;i<4;i++){
            pq.add(new Node(sy,sx,i,0));
            visited[sy][sx][i]=1;
        }


        while(!pq.isEmpty()){

            Node now=pq.poll();
//            System.out.print("now.x  = " + now.x );
//            System.out.println(" now.y  = " + now.y );
            visited[now.y][now.x][now.direction]=1;

            if(now.y==ey && now.x==ex){
                answer=Math.min(answer,now.count);
                return ;
            }

            int ny=now.y+move[now.direction][0];
            int nx=now.x+move[now.direction][1];



//            System.out.print("ny  = " + ny );
//            System.out.println(" nx = " + nx );
            if(ny>=0 && ny<n && nx>=0 && nx<n && visited[ny][nx][now.direction]==0 && arr[ny][nx]!='*'){

                if(arr[ny][nx]=='!'){
                    pq.add(new Node(ny,nx,(now.direction+1)%4, now.count+1));
                    pq.add(new Node(ny,nx,(now.direction+3)%4, now.count+1));
                }
                pq.add(new Node(ny,nx, now.direction,now.count));

            }

        }
    }
}
