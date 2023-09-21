package ps.baekjun.simulation;

import java.util.*;
import java.io.*;

public class g3_16236 {
    public static class Node{
        int x;
        int y;
        int distance;
        public Node(int y, int x,int distance){
            this.y=y;
            this.x=x;
            this.distance=distance;
        }
    }
    public static int[] shark={2,2};
    public static int[] dx={1,-1,0,0};
    public static int[] dy={0,0,1,-1};
    public static int n;
    public static int answer=0;
    public static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());

        arr=new int[n][n];
        Node start=new Node(0,0,0);

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
                if(arr[i][j]==9){
                    start.y=i;
                    start.x=j;
                    arr[i][j]=0;
                }
            }
        }
        while(true){
//            System.out.println("ny "+start.y+" nx "+start.x+" dis "+start.distance);
//            System.out.println(shark[0]+" "+shark[1]);
            Node fish=findFish(start,shark[0]);
//            System.out.print("fish.y = " + fish.y);
//            System.out.println(" fish.x = " + fish.x);
//            System.out.println("fish.distance = " + fish.distance);
            if(fish.y==21){
                System.out.print(answer);
                return ;
            }
            arr[fish.y][fish.x]=0;
            shark[1]--;
            if(shark[1]==0){
                shark[0]+=1;
                shark[1]=shark[0];
            }
            answer+=fish.distance;
            start.y=fish.y;
            start.x=fish.x;
            start.distance=0;

        }



    }
    public static Node findFish(Node start,int size){
        Deque<Node> deq=new ArrayDeque<>();
        List<Node> canEat=new ArrayList<>();
        int [][] visited=new int [n][n];
        visited[start.y][start.x]=1;
        int min=401;
        deq.addLast(start);
        while(deq.size()>0){
            Node now =deq.pollFirst();
//            System.out.println("ny "+now.y+" nx "+now.x+" distance "+now.distance);
            if(arr[now.y][now.x]>=1 && arr[now.y][now.x]<=6 && arr[now.y][now.x]<size && now.distance<=min){
//                System.out.println("ny "+now.y+" nx "+now.x);
//                System.out.println(now.distance);
                min=now.distance;
                canEat.add(now);
            }
            for(int i=0;i<4;i++){
                int ny=now.y+dy[i];
                int nx=now.x+dx[i];

                if(check(ny,nx) && arr[ny][nx]<=size && visited[ny][nx]==0){
                    visited[ny][nx]=1;
//                    System.out.println("ny "+ny+" nx "+nx+" dis "+(now.distance+1));
                    deq.addLast(new Node(ny,nx,now.distance+1));
                }
            }
        }
        if(canEat.size()==0){
            return new Node(21,21,21);
        }
        canEat.sort((o1,o2)->{
            if(o1.y==o2.y){
                return o1.x-o2.x;
            }
            else{
                return o1.y-o2.y;
            }
        });
//        System.out.println(canEat.size());
//        System.out.print("fish.y = " + canEat.get(0).y);
//        System.out.println(" fish.x = " + canEat.get(0).x);
//        System.out.println("fish.distance = " + canEat.get(0).distance);
        return canEat.get(0);
    }
    public static boolean check(int y,int x){
        return (y>=0 && y<n && x>=0 && x<n);
    }

}
