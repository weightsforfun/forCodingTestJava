package ps.leetCode.Array;

import java.io.*;
import java.util.*;

public class E_1768 {
    public String mergeAlternately(String word1, String word2) {
        int a=word1.length();
        int b=word2.length();

        List<Character> arr=new ArrayList<>();

        int first=0;
        int end=0;
        while(true){
            if(first<a && end<b){
                arr.add(word1.charAt(first));
                arr.add(word2.charAt(end));
                first++;
                end++;
            }
            else if(first<a && end==b){
                while(first<a){
                    arr.add(word1.charAt(first));
                    first++;
                }
                break;
            }
            else if(first==a && end<b){
                while(end<b){
                    arr.add(word2.charAt(end));
                    end++;
                }
                break;
            }
            else{
                break;
            }
        }
        StringBuilder sb=new StringBuilder();
        for(char c:arr){
            sb.append(c);
        }
        return sb.toString();
    }

}
