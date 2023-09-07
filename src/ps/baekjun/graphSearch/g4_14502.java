package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g4_14502 {

    public static class Node{
        int y;
        int x;
        public Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    public static List<Node> virus=new ArrayList<>();
    public static List<Node> empty=new ArrayList<>();

    public static int[][] arr;

    public static int[] visited;
    public static int[] dy={1,0,-1,0};
    public static int[] dx={0,1,0,-1};
    public static int answer=0;
    public static void main(String [] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        int n= Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        arr=new int[n][m];


        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int value=Integer.parseInt(st.nextToken());
                if(value==2){
                    virus.add(new Node(i,j));
                }
                else if(value==0){
                    empty.add(new Node(i,j));
                }
                arr[i][j]=value;
            }
        }

        visited=new int[empty.size()];
        dfs(0);
        System.out.println(answer);

    }
    public static void bfs(){
        Deque<Node> deq=new ArrayDeque<>();
        int [][] tempArr=new int [arr.length][arr[0].length];

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                tempArr[i][j]=arr[i][j];
            }
        }

        for(Node temp: virus){
            deq.addLast(temp);
        }


        while(!deq.isEmpty()){
            Node now=deq.pollFirst();
            for(int i=0;i<4;i++){
                int ny=now.y+dy[i];
                int nx=now.x+dx[i];
                if(ny>=0 && ny<arr.length&&nx>=0&&nx<arr[0].length &&   tempArr[ny][nx]==0){
                    tempArr[ny][nx]=2;
                    deq.addLast(new Node(ny,nx));
                }
            }
        }
        int safe=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(tempArr[i][j]==0){
                    safe++;
                }
            }
        }
        answer=Math.max(answer,safe);

    }
    public static void dfs(int depth){
        if(depth==3){
            bfs();
        }
        else{
            for(int i=0;i<empty.size();i++){
                if(visited[i]==0){
                    visited[i]=1;
                    Node temp=empty.get(i);
                    arr[temp.y][temp.x]=1;
                    dfs(depth+1);
                    arr[temp.y][temp.x]=0;
                    visited[i]=0;
                }
            }
        }
    }
}
