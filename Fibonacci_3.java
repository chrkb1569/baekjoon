import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci_3 {

    public static int recursiveCount = 0;
    public static int dpCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int value = Integer.parseInt(br.readLine());

        recursiveFib(value);
        dpFib(value);

        System.out.println(recursiveCount + " " + dpCount);
    }

    public static int recursiveFib(int value) {
        if(value == 1 || value == 2) {
            recursiveCount++;
            return 1;
        }
        return recursiveFib(value - 1) + recursiveFib(value - 2);
    }

    public static int dpFib(int value) {
        if(value == 1 || value == 2) {
            return value;
        }

        int[] dpArr = new int[value + 1];

        dpArr[0] = 1;
        dpArr[1] = 1;

        for(int i = 2; i < value; i++) {
            dpCount++;
            dpArr[i] = dpArr[i-1] + dpArr[i-2];
        }

        return dpArr[value-1];
    }
}
