package ps.codetree.Simulation;

import java.io.*;
import java.util.*;
public class codeTreeBread {
    public static class Node{
        int y;
        int x;
        int done;
        public Node(int y, int x,int done) {
            this.y=y;
            this.x=x;
            this.done=done;
        }
    }
    public static int n;
    public static int m;
    public static int [][] arr;
    public static Node[] man;
    public static Node[] store;
    public static int[][] tempCamp;
    public static int count;

    public static int[] dy= {-1,0,0,1};
    public static int[] dx= {0,-1,1,0};
    public static void main(String[] args)throws IOException {
        // TODO Auto-generated method stub

        init();
        count=m;
        int time=0;
        while(count>0) {
            time++;
//			System.out.println(time);
            //사람별 움직이게 하기(편의점 도달시 중지)
            if(time>1) {
                int limit=Math.min(time-2, m-1);
//				System.out.println("limit "+limit);
                for(int i=0;i<=limit;i++) {
                    if(man[i].done==1) continue;
                    move(man[i],store[i],i);
                }
            }

            //t<=m 일 때 베이스 캠프로 이동시키기
            if(time<=m) {

                man[time-1]=setBaseCamp(time-1);
            }

        }

        System.out.println(time);

    }
    public static void init()throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        arr=new int[n][n];
        tempCamp=new int[n][n];
        man=new Node[m];

        store=new Node[m];

        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
                tempCamp[i][j]=arr[i][j];
            }
        }
        for(int i=0;i<m;i++) {
            st=new StringTokenizer(br.readLine());
            int y=Integer.parseInt(st.nextToken())-1;
            int x=Integer.parseInt(st.nextToken())-1;
            store[i]=new Node(y,x,0);
        }

    }
    public static Node setBaseCamp(int i) {
        Node start=store[i];
        int [][] visited=new int[n][n];
        Deque<Node> deq=new ArrayDeque<>();
        List<Node> shortest=new ArrayList<>();
        int min=1000;
        deq.addLast(start);
        while(!deq.isEmpty()) {
            Node now=deq.pollFirst();
            if(tempCamp[now.y][now.x]==1 && now.done<=min) {
                arr[now.y][now.x]=2;
                return new Node(now.y,now.x,0);
            }
            for(int j=0;j<4;j++) {
                int ny=now.y+dy[j];
                int nx=now.x+dx[j];
                if(!checkRange(ny,nx) || visited[ny][nx]==1 || now.done+1>min || arr[ny][nx]==2) continue;
                visited[ny][nx]=1;
                deq.addLast(new Node(ny,nx,now.done+1));
            }
        }
        shortest.sort((o1,o2)->{
            if(o1.y==o2.y) {
                return o1.x-o2.x;
            }
            return o1.y-o2.y;
        });
        Node end=shortest.get(0);
        arr[end.y][end.x]=2;
//		System.out.println("y "+end.y+" x "+end.x);
        return new Node(end.y,end.x,0);

    }
    public static boolean checkRange(int y,int x) {
        return y>=0 && y<n && x>=0 && x<n;
    }
    public static void move(Node start,Node end,int peopleNum) {
        //bfs로 탐색
        Deque<Node> deq=new ArrayDeque<>();
        deq.addLast(start);
        int [][] visited=new int[n][n];
        visited[start.y][start.x]=1;
        while(!deq.isEmpty()) {
            Node now=deq.pollFirst();
            if(now.y==end.y && now.x==end.x) {
                break;
            }
            for(int j=0;j<4;j++) {
                int ny=now.y+dy[j];
                int nx=now.x+dx[j];
                if(!checkRange(ny,nx) || visited[ny][nx]!=0 || arr[ny][nx]==2) continue;
                visited[ny][nx]=j+1;
                deq.addLast(new Node(ny,nx,now.done+1));
            }
        }
        //지금 가야하는 방향 return
        int y=end.y;
        int x=end.x;
        int last_index=0;
        while(true) {
            if(y==start.y && x==start.x) {
                break;
            }
            int index=visited[y][x]-1;
            y=y-dy[index];
            x=x-dx[index];
            last_index=index;
        }
        //방향으로 이동
        Node temp=man[peopleNum];
        temp.y+=dy[last_index];
        temp.x+=dx[last_index];
//		System.out.println("moving "+peopleNum+" last_index "+last_index);
        //이동하고 편의점이면 멈추고 2로 바꾸기
        if(temp.y==end.y && temp.x==end.x) {
//			System.out.println("arrived! "+peopleNum);
            temp.done=1;
            arr[temp.y][temp.x]=2;
            count--;
        }

    }
}