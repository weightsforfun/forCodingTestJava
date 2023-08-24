package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g4_1987 {
    public static int [][] move={{1,0},{-1,0},{0,1},{0,-1}};
    public static Set<Character> dic = new HashSet<>();
    public static int answer=0;
    public static int r;
    public static int c;
    public static Character[][] arr;
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());

        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());
        String [] str=new String[r];
        for(int i=0;i<r;i++){
            st=new StringTokenizer(br.readLine());
            str[i]=st.nextToken();
        }
        arr=new Character [r][c];


        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                arr[i][j]=str[i].charAt(j);
            }
        }
        dic.add(arr[0][0]);
        dfs(0,0);


        System.out.println(answer);
    }
    public static void dfs(int y,int x){
        answer=Math.max(answer,dic.size());
        for(int i=0;i<4;i++){
            int dy=move[i][0];
            int dx=move[i][1];
            int ny=y+dy;
            int nx=x+dx;
            if(ny>=0 && ny<r && nx>=0 && nx<c &&(!dic.contains(arr[ny][nx]))){
//                System.out.println("arr[ny][nx] = " + arr[ny][nx]);
                dic.add(arr[ny][nx]);
                dfs(ny,nx);
                dic.remove(arr[ny][nx]);
            }
        }
    }

}
