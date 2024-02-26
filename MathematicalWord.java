import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MathematicalWord {
    private static Map<Character, Integer> resultMap = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int wordCount = 0; wordCount < N; wordCount++) {
            String word = br.readLine();
            mkResultArr(word);
        }

        int maxSum = getMaxSum();

        System.out.println(maxSum);
    }

    private static void mkResultArr(String word) {
        int length = word.length();
        int value = (int)Math.pow(10, length-1);

        for(int index = 0; index < length; index++) {
            char key = word.charAt(index);

            resultMap.put(key, resultMap.getOrDefault(key, 0) + value);
            value /= 10;
        }
    }

    private static int getMaxSum() {
        int sum = 0;
        int multiplier = 9;

        List<Integer> values = new ArrayList<>(resultMap.values());

        Collections.sort(values, Collections.reverseOrder());

        for(int value : values) {
            sum += (value * multiplier);
            multiplier--;
        }

        return sum;
    }
}