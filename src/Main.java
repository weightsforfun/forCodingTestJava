import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> arr=new ArrayList<>();
        arr.add("hello");
        arr.add("world");
        List<String> arr3=arr.stream()
                .distinct()
                .collect(Collectors.toList());

        List<String[]> collect = arr.stream()
                .map(word -> word.split(""))
                        .toList();

        System.out.print(collect);
    }
}