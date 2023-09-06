package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g4_4485 {
    public static class Node{
        int index;
        int cost;
        public Node(int index,int cost){
            this.index=index;
            this.cost=cost;
        }
    }
    public static int[][] arr;
    public static int[] visited;
    public static List<List<Node>> list;
    public static int[][] move={{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String [] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answerIndex=1;
        while(true){
            st=new StringTokenizer(br.readLine());
            int n= Integer.parseInt(st.nextToken());
            if(n==0){
                break;
            }
            arr=new int[n][n];


            for(int i=0;i<n;i++){
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    arr[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            list=new ArrayList<>();
            for(int i=0;i<n*n;i++){
                list.add(new ArrayList<>());
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    for(int [] di:move){
                        int ny=i+di[0];
                        int nx=j+di[1];
                        if(ny>=0 && ny<n && nx>=0 && nx<n ){
                            int now=i*n+j;
                            int next=ny*n+nx;
                            list.get(now).add(new Node(next,arr[ny][nx]));
                        }
                    }
                }
            }
            visited=new int[n*n];
            int [] distance=new int[n*n];
            Arrays.fill(distance,30000);
            distance[0]=arr[0][0];
            PriorityQueue<Node> pq=new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
            pq.add(new Node(0,0));
            while(!pq.isEmpty()){
                Node now = pq.poll();
                if(visited[now.index]==1){
                    continue;
                }
                visited[now.index]=1;
                for(Node next:list.get(now.index)){
                    if(distance[next.index]>distance[now.index]+next.cost){
                        distance[next.index]=distance[now.index]+next.cost;
                        pq.add(new Node(next.index,distance[next.index]));
                    }
                }
            }
            System.out.println("Problem "+answerIndex+": "+distance[(n*n)-1]);
            answerIndex++;
        }
    }
}
