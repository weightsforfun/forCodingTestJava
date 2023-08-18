package ps.baekjun.dijk;

import java.util.*;
import java.io.*;

public class g4_1504 {
    public static int[] distance;
    public static int[] visited;

    public static int[][] arr;
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int e;
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        e=Integer.parseInt(st.nextToken());

        arr=new int[n+1][n+1];

        for(int [] row:arr){
            for(int item:row){
                item=Integer.MAX_VALUE;
            }
        }

        for(int i=0;i<e;i++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int value=Integer.parseInt(st.nextToken());
            arr[start][end]=value;
        }

        int des1;
        int des2;

        st=new StringTokenizer(br.readLine());
        des1=Integer.parseInt(st.nextToken());
        des2=Integer.parseInt(st.nextToken());

        int res1=0;
        res1+=dijk(1,des1);
        res1+=dijk(des1,des2);
        res1+=dijk(des2,n);

        int res2=0;
        res2+=dijk(1,des2);
        res2+=dijk(des2,des1);
        res2+=dijk(des1,n);

        System.out.println(Math.min(res1,res2));

    }
    public static int dijk(int start, int end){

        for(int[] row:arr){
            for(int item:row){
                item=Integer.MAX_VALUE;
            }
        }
        Arrays.fill(visited,0);
        Arrays.fill(distance,Integer.MAX_VALUE);
        visited[0]=1;
        visited[1]=1;
        distance[start]=0;
        PriorityQueue<Integer[]> pq=new PriorityQueue<>((item1,item2)-> item1[0]>item2[0] ? 1: -1);

        int index=0;
        pq.add(new Integer[]{0,start});

        while(pq.size()!=0){
            Integer[] top = pq.poll();
            int currentIndex=top[1];
            int currentValue=top[0];
            distance[currentIndex]=currentValue;
            visited[currentIndex]=1;

            for(int i=0;i<n;i++){
                if(visited[i]==1){
                    continue;
                }
                if(arr[start][currentIndex]+arr[currentIndex][i]<arr[start][i]){
                    
                }
                distance[currentIndex]=Math.min(arr[start][currentIndex]+arr[currentIndex][i],arr[start][i]);
                pq.add(new Integer[]{arr[start][i],i});
            }
        }
//        System.out.println("start = " + start);
//        System.out.println("end = " + end);
//        System.out.println("distance[end] = " + distance[end]);
        return distance[end];
    }
}
