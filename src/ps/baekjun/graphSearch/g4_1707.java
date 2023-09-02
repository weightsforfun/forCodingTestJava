package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g4_1707 {

    public static boolean flag;
    public static int[] visited;
    public static int[] checked;

    public static int [] div;
    public static boolean done;

    public static List<List<Integer>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int v=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());

            visited=new int[v+1];
            checked=new int[v+1];
            div=new int[v+1];
            done=false;
            flag=true;
            adj=new ArrayList<>();

            for(int j=0;j<v+1;j++){
                adj.add(new ArrayList<>());
            }

            for(int j=0;j<e;j++){
                st=new StringTokenizer(br.readLine());
                int start=Integer.parseInt(st.nextToken());
                int end=Integer.parseInt(st.nextToken());
                adj.get(start).add(end);
                adj.get(end).add(start);
            }
            for(int j=1;j<v+1;j++){
                if(done){
                    break;
                }
                if(checked[j]==0){
                    dfs(j,1);
                }
            }

            if(flag){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }


        }
    }
    public static void dfs(int start,int toggle){
        if(done){
            return;
        }
        visited[start]=1;
        checked[start]=1;
        div[start]=toggle;
//        for(int a:visited){
//            System.out.print(a+" ");
//        }
//        System.out.println("");
        for(int next:adj.get(start)){
//            System.out.println(next);
            if(visited[next]==1){
                if(div[next]==div[start]){
                    flag=false;
                    done=true;
                    return ;
                }
            }
            else{
                dfs(next,toggle*-1);
            }

        }

    }


}
