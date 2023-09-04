package ps.baekjun.implementation;

import java.util.*;
import java.io.*;

public class g4_17406 {
    public static int[] visited;
    public static int answer=5001;
    public static int k;
    public static int [][] arr;
    public static List<List<Integer>> turnList;
    public static void main(String [] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        visited=new int[k];
        arr=new int[n][m];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        turnList=new ArrayList<>();

        for(int i=0;i<k;i++){
            st=new StringTokenizer(br.readLine());
            List<Integer> tempList=new ArrayList<>();
            tempList.add(Integer.parseInt(st.nextToken()));
            tempList.add(Integer.parseInt(st.nextToken()));
            tempList.add(Integer.parseInt(st.nextToken()));
            turnList.add(tempList);
        }

        for(int i=0;i<k;i++){
            visited[i]=1;
            clock(turnList.get(i));
            dfs(0);
            unClock(turnList.get(i));
            visited[i]=0;
        }

        System.out.println(answer);



//        clock(new ArrayList<>(Arrays.asList(3,4,2)));
//        unClock(new ArrayList<>(Arrays.asList(3,4,2)));
//        for(int[] a:arr){
//            for(int b:a){
//                System.out.print(b+" ");
//            }
//            System.out.println(" ");
//        }



    }
    public static void clock(List<Integer> range){
        int length=range.get(2);
        int sy=range.get(0)-length-1;
        int sx=range.get(1)-length-1;
        int ey=range.get(0)+length-1;
        int ex=range.get(1)+length-1;

        while(sy<ey || sx<ex){
            int temp=arr[sy][sx];
            for(int i=sy;i<ey;i++){
                arr[i][sx]=arr[i+1][sx];
            }
            for(int i=sx;i<ex;i++){
                arr[ey][i]=arr[ey][i+1];
            }
            for(int i=ey;i>sy;i--){
                arr[i][ex]=arr[i-1][ex];
            }
            for(int i=ex;i>sx+1;i--){
                arr[sy][i]=arr[sy][i-1];
            }
            arr[sy][sx+1]=temp;
            sy++;
            sx++;
            ey--;
            ex--;
        }

    }

    public static void unClock(List<Integer> range){
        int length=range.get(2);
        int sy=range.get(0)-length-1;
        int sx=range.get(1)-length-1;
        int ey=range.get(0)+length-1;
        int ex=range.get(1)+length-1;

        while(sy<ey || sx<ex){
            int temp=arr[sy][sx];

            for(int i=sx;i<ex;i++){
                arr[sy][i]=arr[sy][i+1];
            }
            for(int i=sy;i<ey;i++){
                arr[i][ex]=arr[i+1][ex];
            }
            for(int i=ex;i>sx;i--){
                arr[ey][i]=arr[ey][i-1];
            }
            for(int i=ey;i>sy+1;i--){
                arr[i][sx]=arr[i-1][sx];
            }

            arr[sy+1][sx]=temp;
            sy++;
            sx++;
            ey--;
            ex--;
        }

    }

    public static void dfs(int depth){
        if(depth==k-1){
            for(int[] a:arr){
                int tempSum=0;
                for(int b:a){
                    tempSum+=b;
                }
                answer=Math.min(answer,tempSum);
        }
        }
        else{


            for(int i=0;i<k;i++){
                if(visited[i]==0){
                    visited[i]=1;
                    clock(turnList.get(i));
                    dfs(depth+1);
                    unClock(turnList.get(i));
                    visited[i]=0;
                }
            }
        }
    }

}
