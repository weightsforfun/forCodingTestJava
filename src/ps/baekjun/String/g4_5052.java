package ps.baekjun.String;


import java.io.*;
import java.util.*;

public class g4_5052 {
    public static boolean check(List<String> arr){
        for(int i=0;i<arr.size()-1;i++){
            if(arr.get(i+1).startsWith(arr.get(i))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {


        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int t=Integer.parseInt(st.nextToken());

        for(int i=0;i<t;i++){
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());

            List<String> arr=new ArrayList<>();


            for(int j=0;j<n;j++){
                st=new StringTokenizer(br.readLine());
                arr.add(st.nextToken());
            }
            Collections.sort(arr);
            if(check(arr)){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }

        }

    }

}
