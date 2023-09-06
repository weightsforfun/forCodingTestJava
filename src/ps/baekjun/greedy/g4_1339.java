package ps.baekjun.greedy;

import java.util.*;
import java.io.*;

public class g4_1339 {

    public static int [] visited;
    public static int n;
    public static Map<Character,Integer> dic;
    public static ArrayDeque<Integer> nums=new ArrayDeque<>();
    public static List<String> list;
    public static int answer=0;
    public static void main(String [] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());

        list=new ArrayList<>();
        dic=new LinkedHashMap<>();
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            String temp=st.nextToken();
            list.add(temp);

            for(int j=0;j<temp.length();j++) {
                if (!dic.containsKey(temp.charAt(j))) {
                    dic.put(temp.charAt(j), -1);
                }
            }

        }

        visited=new int[10];
        dfs(0);
        System.out.println(answer);

    }
    public static void dfs(int depth){
        if(depth==dic.size()){
//            System.out.println(nums);
            for(Character c: dic.keySet()){
                int tempValue=nums.pollFirst();
                dic.put(c,tempValue);
                nums.addLast(tempValue);
            }
//            System.out.println(dic);
            int sum=0;
            for(String s:list){
                int tempNum=0;
                for(int i=0;i<s.length();i++){
                    tempNum*=10;
                    tempNum+=dic.get(s.charAt(i));
                }
                sum+=tempNum;
            }
            answer=Math.max(answer,sum);
        }
        for(int i=9;i>=10-dic.size();i--){
            if(visited[i]==0){
                visited[i]=1;
                nums.addLast(i);
                dfs(depth+1);
                nums.pollLast();
                visited[i]=0;
            }

        }
    }

}
