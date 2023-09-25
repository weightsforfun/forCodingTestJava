package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g4_1967 {
    public static List<List<Node>> arr=new ArrayList<>();
    public static int[] visited;

    public static int answer;
    public static int start;
    public static class Node{
        int next;
        int len;
        public Node(int next,int len){
            this.next=next;
            this.len=len;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        visited=new int[n+1];



        for(int i=0;i<n+1;i++){
            arr.add(new ArrayList<>());
        }
        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int parent=Integer.parseInt(st.nextToken());
            int child=Integer.parseInt(st.nextToken());
            int value=Integer.parseInt(st.nextToken());

            arr.get(parent).add(new Node(child,value));
            arr.get(child).add(new Node(parent,value));
        }
        answer=0;
        visited[1]=1;
        dfs(1,0);
//        System.out.println(answer);
//        System.out.println(start);
        answer=0;
        Arrays.fill(visited,0);
        visited[start]=1;
        dfs(start,0);
        System.out.println(answer);
    }
    public static void dfs(int now,int sum){

        List<Node> nexts=arr.get(now);
//        System.out.println("now "+now+" sum "+sum+" nexts "+nexts.size());
        if(nexts.size()==1 && sum!=0){
            if(sum>answer){
                start=now;
                answer=sum;
            }
        }else{
            for(Node next:nexts){
                if(visited[next.next]==0){
                    visited[next.next]=1;
                    dfs(next.next,sum+next.len);
                }
            }
        }
    }
}
