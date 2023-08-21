package ps.baekjun.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class g5_3020 {
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int h=Integer.parseInt(st.nextToken());

        int [] arr=new int[n];
        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            arr[i]=Integer.parseInt(st.nextToken());
        }

        List<Integer> up=new ArrayList<>();
        List<Integer> down=new ArrayList<>();

        for(int i=0;i<arr.length;i++){
            if(i%2==1){
                up.add(arr[i]);
            }
            else{
                down.add(arr[i]);
            }
        }

        up.sort((o1,o2)->{
            return o1-o2;
        });
        down.sort((o1,o2)->o1-o2);

        int minCount=200001;
        int [] height=new int[arr.length+1];
        for(int i=1;i<arr.length+1;i++){
            int tempCount=0;
            if(i%2==1){
                tempCount+=bi(i,down);
            }
            else{
                tempCount+=bi(arr.length-i+1,up);
            }
            height[i]=tempCount;
            minCount=Math.min(minCount,tempCount);
            System.out.println("tempCount = " + tempCount);
        }
        int answer=0;
        for(int item:height){
            if(item==minCount){
                answer++;
            }
        }
        System.out.print(minCount+" "+answer);




    }
    public static int bi(int target,List<Integer> list){
        int start=0;
        int end=list.size()-1;
        int middle;
        int index=0;
        while(start<end){
            middle=(end+start)/2;
            System.out.println("start = " + start);
            System.out.println("end = " + end);
            if(list.get(middle)==target){
                index=middle;
                break;
            }
            else if(list.get(middle)<target){
                start=middle+1;
                index=middle+1;
            }
            else{
                index=middle;
                end=middle-1;
            }
        }
        return list.size()-index;
    }

}
