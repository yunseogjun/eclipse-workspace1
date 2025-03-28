package listSample;
import java.util.ArrayList;
import java.util.List;
public class ListArray {

	public static void main(String[] args) {
        List<String> numbers = new ArrayList<>();
        numbers.add("11");
        numbers.add("12");
        numbers.add("13");
        // forEach를 이용한 출력
        numbers.forEach(num -> System.out.println(num));
        for (String i : numbers) {
        	System.out.println("i=" + i);
        }
	}
}
