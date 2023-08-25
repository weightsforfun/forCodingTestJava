package ps.baekjun.dijk;


import java.util.*;
import java.io.*;

public class g5_5972_2 {
    public static class Node{
        int index;
        int cost;
        public Node(int index, int cost){
            this.index=index;
            this.cost=cost;
        }
    }
    public static void main(String [] args)throws IOException {
        int answer = 0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st= new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        List<List<Node>> arr=new ArrayList<>();
        for(int i=0;i<n+1;i++){
            arr.add(new ArrayList<Node>());
        }

        for(int i=0;i<m;i++){
            st= new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int cost=Integer.parseInt(st.nextToken());
            arr.get(start).add(new Node(end,cost));
            arr.get(end).add(new Node(start,cost));
        }

        PriorityQueue<Node> pq=new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
        pq.add(new Node(1,0));

        int[] distance=new int [n+1];
        Arrays.fill(distance,50000001);
        distance[1]=0;
        int[] visited=new int [n+1];

        while(!pq.isEmpty()){
            Node now=pq.poll();
            int index=now.index;
            if(visited[index]==0) {
                visited[index]=1;
                for (Node next : arr.get(index)) {
                    if (visited[next.index] == 0
                            && distance[next.index] > distance[index] + next.cost) {
                        distance[next.index] = distance[index] + next.cost;
                        pq.add(new Node(next.index, distance[next.index]));
                    }
                }
            }

        }
        StringBuilder sb=new StringBuilder();
        sb.append(distance[n]);
        System.out.println(sb);



    }

}
