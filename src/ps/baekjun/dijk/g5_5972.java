package ps.baekjun.dijk;

import java.util.*;
import java.io.*;

public class g5_5972 {
    public static class Node{
        int start;
        int value;
        public Node(int start,int value){
            this.start=start;
            this.value=value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());;


        List<List<Node>> list=new ArrayList<>();


        for(int i=0;i<=n;i++){
            list.add(new ArrayList<Node>());
        }

         for(int i=0;i<m;i++){
             st=new StringTokenizer(br.readLine());
             int start=Integer.parseInt(st.nextToken());
             int end=Integer.parseInt(st.nextToken());
             int count=Integer.parseInt(st.nextToken());
             list.get(start).add(new Node(end,count));
             list.get(end).add(new Node(start,count));
         }

        PriorityQueue<Node> hq=new PriorityQueue<>((o1,o2)-> o1.value-o2.value);

        hq.add(new Node(1,0));

        int [] FromStart= new int[n+1];
        Arrays.fill(FromStart,50000001);
        int [] visited = new int[n+1];
        FromStart[1]=0;
         while(hq.size()!=0){
             Node c=hq.poll();
             int start=c.start;


             if(visited[start]==0){
                 visited[start]=1;
                 for(Node temp: list.get(start)){
                     if(visited[temp.start]==0 && FromStart[temp.start]>temp.value+FromStart[start]){
                         FromStart[temp.start]=temp.value+FromStart[start];
                         hq.add(new Node(temp.start,FromStart[temp.start]));
                     }
                 }
             }

         }
         System.out.println(FromStart[n]);

    }

}
