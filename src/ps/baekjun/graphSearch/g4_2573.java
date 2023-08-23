package ps.baekjun.graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class g4_2573 {
    public static int [][] move={{1,0},{-1,0},{0,1},{0,-1}};
    public static class Ice{
        int x;
        int y;
        int value;
        public Ice(int x,int y, int value){
            this.x=x;
            this.y=y;
            this.value=value;
        }
    }
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int answer=0;
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int [][] arr=new int[n][m];
        int[][] visited = new int[n][m];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        Deque<Ice> deq=new ArrayDeque<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]!=0){
                    deq.add(new Ice(j,i,arr[i][j]));
                }
            }
        }
        int year=0;
        while(true){
            int count=deq.size();
//            System.out.println(count);

            if(count==0){
                break;
            }
            for(int i=0;i<count;i++){
                Ice now=deq.pollFirst();
                for(int j=0;j<4;j++){
                    int dy=move[j][0];
                    int dx=move[j][1];
                    if(arr[now.y+dy][now.x+dx]==0){
                        now.value--;
                    }
                }
                deq.addLast(now);

            }
            year++;
            int lost=0;
            for(int i=0;i<count;i++){

                Ice temp=deq.pollFirst();
                // System.out.println("x: "+temp.x+" y: "+temp.y+" value: "+temp.value);
                if(temp.value<=0){
                    arr[temp.y][temp.x]=0;
                    lost++;
                }
                else{
                    arr[temp.y][temp.x]=temp.value;
                    deq.addLast(temp);
                }
            }
            count-=lost;
            Deque<Ice> deq2=new ArrayDeque<>();
            if(count==0){
                break;
            }

            deq2.addLast(deq.getFirst());
            visited[deq.getFirst().y][deq.getFirst().x]=year;
            int count2=0;
            while(deq2.size()!=0){
                Ice now=deq2.pollFirst();
                count2++;
                for(int i=0;i<4;i++){
                    int dy=move[i][0];
                    int dx=move[i][1];
                    if(arr[now.y+dy][now.x+dx]!=0 && visited[now.y+dy][now.x+dx]<year){
                        // System.out.println("x: "+now.x+" y: "+now.y);
                        deq2.addLast(new Ice(now.x+dx,now.y+dy,0));
                        visited[now.y+dy][now.x+dx]=year;
                    }
                }

            }
            // System.out.println(count);
            // System.out.println(count2);
            // System.out.println(year);
            if(count2!=count){
                answer=year;
                break;
            }



        }

        System.out.println(answer);
    }

}
