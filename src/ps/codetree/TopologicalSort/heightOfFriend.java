package ps.codetree.TopologicalSort;

import java.util.*;
import java.io.*;

public class heightOfFriend {
    public static int[] rank;
    public static void main(String[] args)throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        List<List<Integer>> arr=new ArrayList<>();
        rank=new int[n+1];
        int[] answer=new int[n];
        int[] visited=new int[n+1];
        for(int i=0;i<n+1;i++){
            arr.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            rank[a]++;
            arr.get(b).add(a);
        }


        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=1;i<n+1;i++){
            if(rank[i]==0){
                pq.add(i);
            }
        }

        int index=0;
        while(!pq.isEmpty()){
            int now=pq.poll();
            answer[index]=now;
            visited[now]=1;
            index++;
            for(int next:arr.get(now)){
                rank[next]--;
                if(rank[next]==0 && visited[next]==0){
                    pq.add(next);
                }
            }
        }
        for(int i=n-1;i>=0;i--){
            System.out.print(answer[i]+" ");
        }

    }

}
