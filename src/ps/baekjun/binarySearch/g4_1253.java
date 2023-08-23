package ps.baekjun.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class g4_1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        long [] arr=new long[n];
        st=new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){
            arr[i]=Long.parseLong(st.nextToken());
        }



        Arrays.sort(arr);
        int answer=0;
        for(int i=0; i<n; i++) {
            int left = 0;
            int right = n-1;
            while(true) {

                if (i == left) left++;
                else if (right == i) right--;

                if (left >= right)	break; // 두 숫자의 합으로 나타낼 수 없다.

                if (arr[left] + arr[right] > arr[i]) right--;
                else if (arr[left] + arr[right] < arr[i]) left++;
                else { // 두 숫자의 합으로 나타낼 수 있다면 ? 좋다~!
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }



}
