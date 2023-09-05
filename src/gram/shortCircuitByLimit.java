package gram;

import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class shortCircuitByLimit {
    public static class Dish {
        int price;
        int cost;
        public Dish(int price,int cost){
            this.price=price;
            this.cost=cost;
        }

    }
    public static void main(String[] args){

        List<Integer> list=new ArrayList<>();

        for(int i=0;i<40;i++){
            list.add(i);
        }
        list.stream()
                .filter(item->(item%2)==0)
                .peek(System.out::println)
                .limit(3)
                .collect(toList());

        int [] temp={1,2,3,4,5,6,7,8,9};
        Stream<Integer> stream = Arrays.stream(temp).boxed();
        List<Integer> collected = stream.filter(item -> (item % 2) == 0)
                .collect(toList());


        List<Dish> menu=new ArrayList<>();
        menu.add(new Dish(4,2));
        menu.add(new Dish(3,5));
        menu.add(new Dish(1,3));
        menu.add(new Dish(6,1));

        Optional<Dish> maxDish=menu.stream().collect(Collectors.maxBy(((o1,o2)->o1.price-o2.price)));
        System.out.println("maxDish = " + maxDish.get().cost);
        Integer collect = menu.stream().collect(summingInt(o -> o.cost));
        System.out.println("collect = " + collect);
    }

}
