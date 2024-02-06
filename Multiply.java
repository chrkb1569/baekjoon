import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Multiply {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        long result = multiply(A, B, C);

        System.out.println(result);
    }

    private static long multiply(long a, long b, long c) {
        if(b == 1) return a % c;

        long value = multiply(a, b/2, c);

        if(b % 2 == 0) return value * value % c;
        return (value * value % c) * a % c;
    }
}