package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g4_1967 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer=0;

        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int [] distance=new int[n+1];

        List<List<Integer>> arr=new ArrayList<>();
        for(int i=0;i<n+1;i++){
            arr.add(new ArrayList<>());
        }
        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int parent=Integer.parseInt(st.nextToken());
            int child=Integer.parseInt(st.nextToken());
            int value=Integer.parseInt(st.nextToken());
            distance[child]=value;
            arr.get(parent).add(child);
        }
//        for(List z:arr){
//            System.out.println("z = " + z);
//        }
        Deque<Integer> deq=new ArrayDeque<>();
        for(int i=n;i>=0;i--){
            if(arr.get(i).size()!=0){
                deq.addLast(i);
            }
        }

        while(!deq.isEmpty()){

            int now=deq.pollFirst();

            List<Integer> childs=arr.get(now);
            if(childs.size()==1){
                int childIndex=childs.get(0);
                distance[now]+=distance[childIndex];
                answer=Math.max(answer,distance[childIndex]);
            }
            else if(childs.size()>=2){
                PriorityQueue<Integer> pq=new PriorityQueue<>((o1,o2)->o2-o1);
                for(int child:childs){
                    pq.add(distance[child]);
                }
                int a=pq.poll();
                int b=pq.poll();
                answer = Math.max(answer,a+b);
                distance[now]+=a;
            }
//            System.out.println("now "+now+" answer = " + answer+" distance now"+distance[now]);
        }
        System.out.print(answer);


    }
}
