package ps.codility.chap7;

import java.util.*;

public class Brackets {
    public int solution(String S) {
        // Implement your solution here
        Map<Character,Character> map=new HashMap<>();
        map.put('[',']');
        map.put('{','}');
        map.put('(',')');

        Deque<Character> deq=new ArrayDeque<>();
        char[] arr=S.toCharArray();
        for(char s:arr){
            if(map.containsKey(s)){
                deq.add(s);
            }
            else{
                if(deq.size()==0){
                    return 0;
                }
                else{
                    char top=deq.getLast();
                    if(s==map.get(top)){
                        deq.pollLast();
                    }
                    else{
                        return 0;
                    }
                }
            }
        }
        if(deq.size()!=0){
            return 0;
        }
        return 1;
    }

}
