package ps.baekjun;

import java.util.*;
import java.io.*;

public class g2_1039 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        String string = Integer.toString(n);

        List<Character> num = new ArrayList<>();
        List<Character> sortedNum = new ArrayList<>();
        for(int i=0;i<string.length();i++){
            num.add(string.charAt(i));
            sortedNum.add(string.charAt(i));
        }

        int len=num.size();

        Collections.sort(sortedNum,Collections.reverseOrder());




        boolean flag=false;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<k;i++){
            int index=0;
            if(len==1 || (len==2 && sortedNum.get(1).equals('0'))){
                System.out.println(-1);
                flag=true;
                break;
            }

            if(sortedNum.equals(num)){
                flag=true;
                boolean sameNum=false;
                for(int j=0;j<len-1;j++){
                    if(num.get(j).equals(num.get(j+1))){
                        sameNum=true;
                        break;
                    }
                }
                if(sameNum || ((k-i)%2==0)){
                    for(Character c:num){
                        sb.append(c);
                    }
                    System.out.println(sb);
                }
                else{
                    Character last=num.get(num.size()-1);
                    Character secondLast=num.get(num.size()-2);
                    num.set(len-1,secondLast);
                    num.set(len-2,last);
                    for(Character c:num){
                        sb.append(c);
                    }
                    System.out.println(sb);
                }

                break;
            }
            else{
                while(true){
                    if(sortedNum.get(index)==num.get(index)){
                        index++;
                    }
                    else{
                        Character now = num.get(index);
                        Character target = sortedNum.get(index);
                        int tempIndex=0;
                        for(int j=0;j<len;j++){
                            if(num.get(j)==target){
                                tempIndex=j;
                            }
                        }

                        num.set(index,target);
                        num.set(tempIndex,now);
                        break;
                    }
                }
            }

        }
        if(!flag){
            for(Character c:num){
                sb.append(c);
            }
            System.out.println(sb);
        }


    }

}
