package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g4_1707 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int v=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());
            List<List<Integer>> adj=new ArrayList<>();

            for(int j=0;j<v+1;j++){
                adj.add(new ArrayList<Integer>());

            }
        }
    }


}
