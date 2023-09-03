package ps.baekjun.backtracking;

import java.util.*;
import java.io.*;
public class g5_13023 {

    public static boolean done;

    public static List<List<Integer>> arr;

    public static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        visited=new int[n];
        arr=new ArrayList<>();
        for(int i=0;i<n;i++){
            arr.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            Integer start=Integer.parseInt(st.nextToken());
            Integer end=Integer.parseInt(st.nextToken());

            arr.get(start).add(end);
            arr.get(end).add(start);

        }
        done=false;
        for(int i=0;i<n;i++){
            if(done){
                break;
            }
            visited[i]=1;
            dfs(i,0);
            visited[i]=0;
        }
        if(done){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }

    }
    public static void dfs(int now,int depth){
        if(done){
            return ;
        }
        if(depth==4){
            done=true;
        }
        else{
            for(int friend : arr.get(now)){
                if(visited[friend]==0){
                    visited[friend]=1;
                    dfs(friend,depth+1);
                    visited[friend]=0;
                }
            }
        }
    }
}
