import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClimbingStairs_TopDown {

    static Integer[] result;
    static int[] value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stairs = Integer.parseInt(br.readLine());

        value = new int[stairs + 1];
        result = new Integer[stairs + 1];

        for(int i = 1; i <= stairs; i++) {
            value[i] = Integer.parseInt(br.readLine());
        }

        result[0] = value[0];
        result[1] = value[1];

        if(stairs >= 2) {
            result[2] = value[1] + value[2];
        }

        System.out.println(getResult(stairs));
    }

    public static int getResult(int n) {
        if(result[n] == null) {
            result[n] = Math.max(getResult(n-2), getResult(n-3) + value[n-1]) + value[n];
        }

        return result[n];
    }
}
