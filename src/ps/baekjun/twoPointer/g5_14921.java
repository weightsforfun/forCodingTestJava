package ps.baekjun.twoPointer;


import java.util.*;
import java.io.*;
public class g5_14921 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        long answer = 200000001;

        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());

        long [] arr = new long[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i]=Long.parseLong(st.nextToken());
        }
        int start=0;
        int end=arr.length-1;
        while(start<end){
            long middle=arr[start]+arr[end];
            if(Math.abs(answer)>Math.abs(middle)){
                answer=middle;
            }
            if(middle<0){
                start++;
            }
            else{
                end--;
            }
        }
        System.out.print(answer);
    }

}
