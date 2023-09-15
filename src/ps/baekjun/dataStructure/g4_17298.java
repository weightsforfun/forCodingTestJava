package ps.baekjun.dataStructure;

import java.util.*;
import java.io.*;

public class g4_17298 {
    public static void main(String [] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        int [] arr=new int[n];

        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int [] answer= new int [n];
        Deque<Integer> deq=new ArrayDeque<>();
        answer[n-1]=-1;
        deq.addLast(arr[n-1]);

        for(int i=n-2;i>=0;i--){
            int now=arr[i];
            if(deq.getLast()>now){
                answer[i]=deq.getLast();
                deq.addLast(now);
            }
            else{
                while(true){
                    if(deq.isEmpty()){
                        answer[i]=-1;
                        deq.addLast(now);
                        break;
                    }
                    if(deq.getLast()>now){
                        answer[i]=deq.getLast();
                        deq.addLast(now);
                        break;
                    }
                    deq.pollLast();

                }
            }
        }
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<n;i++){
            if(i==n-1){
                sb.append(answer[i]);
            }
            else{
                sb.append(answer[i]);
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }





}
