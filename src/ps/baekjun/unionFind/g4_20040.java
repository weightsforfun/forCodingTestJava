package ps.baekjun.unionFind;

import java.util.*;
import java.io.*;

public class g4_20040 {
    public static int [] parent;
    public static int find(int v){
        if(v==parent[v]){
            return v;
        }
        return parent[v]=find(parent[v]);
    }
    public static boolean merge(int v, int u){
        v=find(v);
        u=find(u);
        if(u==v){
            return false;
        }
        if(u<=v){
            parent[u]=v;
        }
        else{
            parent[v]=u;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        parent=new int[n];

        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        int answer=1000001;
        for(int i=0; i<m;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            if(!merge(u,v)){
                answer=Math.min(answer,i);
            }

        }
        if(answer==1000001){
            System.out.println(0);
        }
        else{
            System.out.println(answer+1);
        }

    }

}
