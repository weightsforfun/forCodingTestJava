package ps.baekjun.graphSearch;

import java.util.*;
import java.io.*;

public class g2_1039 {
    public static class Node{
        int depth;
        int num;
        public Node(int num, int depth ){
            this.depth = depth;
            this.num=num;
        }
    }
    public static String change(int target,int x, int y){
//        System.out.println("x = " + x);
//        System.out.println("y = " + y);
//        System.out.println("target = " + target);
        StringBuilder temp= new StringBuilder();
        temp.append(target);

        char a=temp.charAt(x);
        char b=temp.charAt(y);

        temp.setCharAt(x,b);
        temp.setCharAt(y,a);
        return temp.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        String string = Integer.toString(n);


        int answer=-1;
        int len=string.length();
        int [][] visited = new int[1000001][11];

        Deque<Node> deq=new ArrayDeque<>();
        deq.add(new Node(n,0));



        if(len==1 || (len==2 && (string.charAt(1)-'0'==0))){
            System.out.println(answer);

        }
        else{
            while(!deq.isEmpty()){
                Node now=deq.pollFirst();
                int now_depth=now.depth;
                for(int i=0;i<len;i++){
                    for(int j=i+1;j<len;j++){
                        int now_num=now.num;
                        if(now.depth==k){
                            answer=Math.max(answer,now_num);
                            continue;
                        }

                        String changed = change(now_num, j, i);
                        if((changed.charAt(0)-'0')==0){
                            continue;
                        }
                        Integer changed_num=Integer.parseInt(changed);
                        if(now_depth<k && visited[changed_num][now_depth+1]==0){
                            visited[changed_num][now_depth+1]=1;
                            deq.add(new Node(changed_num,now_depth+1));
                        }
                    }
                }
            }

            System.out.println(answer);

        }












    }

}
