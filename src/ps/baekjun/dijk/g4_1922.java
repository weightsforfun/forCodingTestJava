package ps.baekjun.dijk;

import java.util.*;
import java.io.*;

public class g4_1922 {
    public static class Node{
        int now;
        int next;
        int distance;
        public Node(int now,int next,int distance){
            this.now=now;
            this.next=next;
            this.distance=distance;
        }
    }
    public static int[] parent;
    public static int find(int n){
        if(parent[n]==n){
            return n;
        }
        else{
            return parent[n]=find(parent[n]);
        }
    }
    public static boolean merge(int u,int v){
        u=find(u);
        v=find(v);
        if(u==v){
            return false;
        }
        else{
            if(u>v){
                parent[v]=u;
            }
            else{
                parent[u]=v;
            }
            return true;
        }
    }
    public static void main(String [] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());

        parent=new int[n+1];

        for(int i=0;i<n+1;i++){
            parent[i]=i;
        }

        st=new StringTokenizer(br.readLine());

        int m =Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq=new PriorityQueue<>((o1,o2)->o1.distance-o2.distance);

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int val=Integer.parseInt(st.nextToken());
            pq.add(new Node(start,end,val));
        }

        int count=0;
        int total=0;
        while(true){
            if(count==n-1){
                break;
            }
            Node now=pq.poll();
            if(merge(now.now,now.next)){
                count++;
                total+=now.distance;
            }

        }
        System.out.println(total);




    }



}
