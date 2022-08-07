import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AtoB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long count = 1;

        long initial = Long.parseLong(st.nextToken());
        long result = Long.parseLong(st.nextToken());

        System.out.println(getResult(initial, result, count));
    }

    public static long getResult(long number, long target, long count) {
        if(number > target) {
            return -1;
        }

        else if(number == target) {
            return count;
        }

        long value1 = getResult(2 * number, target, count+1);

        long value2 = getResult(Long.parseLong(number + "1"), target, count+1);

        return Math.max(value1, value2);
    }
}
