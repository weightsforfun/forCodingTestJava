package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g5_7569 {
    static int[][][] arr;
    static int day;
    static int no=0;
    static int yes=0;
    static Deque<int[]> deq=new ArrayDeque<>();
    static int[] dy={1,-1,0,0};
    static int[] dx={0,0,1,-1};
    static int[] dz={1,-1};
    static Integer M;
    static Integer N;
    static Integer H;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr=new int[H][N][M];
        for(int i=0;i<H;i++){
            for(int j=0;j<N;j++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());

                for(int k=0;k<M;k++){
                    arr[i][j][k]=Integer.parseInt(st2.nextToken());
                    if(arr[i][j][k]==0){
                        no++;
                    }
                    else if(arr[i][j][k]==1){
                        yes++;
                        deq.add(new int[]{i,j,k,0});
                    }
                }
            }
        }
        bfs();
        if(no>0){
            System.out.println(-1);
        }
        else{
            System.out.println(day);
        }
    }
    public static void bfs(){
        while(! deq.isEmpty()){
            int[] pollFirst = deq.pollFirst();
            int z=pollFirst[0];
            int y=pollFirst[1];
            int x=pollFirst[2];
            int temp_day=pollFirst[3];
            day=Math.max(day,temp_day);
            for(int i=0;i<4;i++){
                int ny=y+dy[i];
                int nx=x+dx[i];
                if(ny>=0 && nx>=0 && ny<N && nx<M && arr[z][ny][nx]==0){
                    no--;
                    yes++;
                    arr[z][ny][nx]=1;
                    deq.addLast(new int[]{z,ny,nx,temp_day+1});
                }
            }
            for(int i=0;i<2;i++){
                int nz=z+dz[i];
                if(nz>=0 && nz<H && arr[nz][y][x]==0){
                    no--;
                    yes++;
                    arr[nz][y][x]=1;
                    deq.addLast(new int[]{nz,y,x,temp_day+1});
                }
            }

        }
    }

}
