package ps.baekjun.twoPointer;


import java.util.*;
import java.io.*;
public class g4_13144 {

    public static void main(String[] args) throws IOException {
        long answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st ;
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int[] arr=new int[n+1];

        st=new StringTokenizer(br.readLine());

        for(int i=1;i<n+1;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        Set<Integer> dic=new HashSet<>();

        int end=0;

        for(int start=1;start<=n;start++){
            while( end<=n-1 && !dic.contains(arr[end+1]) ){
                end++;
                dic.add(arr[end]);

            }
            answer+=(end-start+1);

            dic.remove(arr[start]);


        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer);
        System.out.println(sb);
    }

}
