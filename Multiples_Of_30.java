import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Multiples_Of_30 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String value = br.readLine();

        System.out.println(findMaxValue(value));
    }

    public static String findMaxValue(String value) {
        if(!isValidate(value)) return "-1";

        return getList(value).stream().sorted(Collections.reverseOrder()).map(s -> s.toString()).collect(Collectors.joining());
    }

    public static boolean isValidate(String value) {
        List<Character> list = getList(value);

        int sum = 0;

        for(char c : list) {
            sum += c-48;
        }

        return (sum % 3 == 0) && (list.contains('0'));
    }

    public static List<Character> getList(String value) {
        List<Character> list = new LinkedList<>();

        for(char c : value.toCharArray()) {
            list.add(c);
        }

        return list;
    }
}
