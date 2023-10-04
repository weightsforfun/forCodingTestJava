package ps.baekjun.topologicalSort;

import java.util.*;
import java.io.*;

public class g3_1005 {
    public static void main(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int t=Integer.parseInt(st.nextToken());

        for(int i=0;i<t;i++){
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());


            int [] time=new int[n];
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                time[j]=Integer.parseInt(st.nextToken());
            }

            int [] topo=new int[n];
            List<List<Integer>> arr=new ArrayList<>();
            for(int j=0;j<n;j++){
                arr.add(new ArrayList<>());
            }
            for(int j=0;j<k;j++){
                st=new StringTokenizer(br.readLine());
                int prev=Integer.parseInt(st.nextToken())-1;
                int next=Integer.parseInt(st.nextToken())-1;
                topo[next]++;
                arr.get(prev).add(next);
            }
            st=new StringTokenizer(br.readLine());
            int target=Integer.parseInt(st.nextToken())-1;
            topologicalSort(time,topo,arr,target);
        }


    }
    public static void topologicalSort(int [] time,int [] topo,List<List<Integer>> arr,int target){
        Deque<Integer> pq=new ArrayDeque<>();
        int [] prefixTime=new int[time.length];
        for(int i=0;i<time.length;i++){
            prefixTime[i]+=time[i];
        }
        for(int i=0;i<time.length;i++){
            if(topo[i]==0){
                pq.addLast(i);
            }

        }
        while(!pq.isEmpty()){
            Integer now=pq.pollFirst();
            for(int next:arr.get(now)){
                topo[next]--;
                prefixTime[next]=Math.max(prefixTime[next],prefixTime[now]+time[next]);
                if(topo[next]==0){
                    pq.addLast(next);
                }
            }
        }
        System.out.println(prefixTime[target]);
    }

}
