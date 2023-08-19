package ps.baekjun.dijk;

import java.util.*;
import java.io.*;

public class g4_1504 {
    public static List<List<Node>> arr;
    public static int[] visited;
    public static int[] distance;

    public static int INF=2000000; 
    public static class Node{
        int cost,des;
        Node(int cost,int des){
            this.cost=cost;
            this.des=des;
        }
        public int getCost(){
            return cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());

        int n,e;

        n=Integer.parseInt(st.nextToken());
        e=Integer.parseInt(st.nextToken());
        visited=new int[n+1];
        distance = new int[n+1];
        arr = new ArrayList<>();
        for(int i=0;i<n+1;i++) {
            arr.add(new ArrayList<Node>());
        }

        for(int i=0;i<e;i++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int value=Integer.parseInt(st.nextToken());

            arr.get(start).add(new Node(value,end));
            arr.get(end).add(new Node(value,start));
        }

        st=new StringTokenizer(br.readLine());

        int v1=Integer.parseInt(st.nextToken());
        int v2=Integer.parseInt(st.nextToken());

        int case1=0;
        case1=dijk(1,v1)+dijk(v1,v2)+dijk(v2,n);
        int case2=0;
        case2=dijk(1,v2)+dijk(v2,v1)+dijk(v1,n);

        if(case1>=INF && case2>=INF){
            System.out.print(-1);
        }
        else{
            System.out.print(Math.min(case1,case2));
        }




    }

    public static int dijk(int start, int end){
        Arrays.fill(visited,0);
        Arrays.fill(distance,INF);
        distance[start]=0;
        PriorityQueue<Node> pq=new PriorityQueue<>(Comparator.comparing(item->item.getCost()));
        pq.add(new Node(0,start));
        while(pq.size()!=0){
            Node now=pq.poll();
            int now_des=now.des;
            int now_cost=now.cost;
            if(visited[now_des]==1){
                continue;
            }
            visited[now_des]=1;
            for(Node next_node:arr.get(now_des)){
                if(visited[next_node.des]==0 && distance[next_node.des]>now_cost+next_node.cost){
                    pq.add(new Node(now_cost+next_node.cost,next_node.des));
                    distance[next_node.des]=now_cost+next_node.cost;
                }
            }
        }
        return distance[end];
    }
}
