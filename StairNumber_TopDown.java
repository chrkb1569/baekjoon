import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StairNumber_TopDown {

    static Long[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());

        arr = new Long[length + 1][10];

        for(int i = 0; i < 10; i++) {
            arr[1][i] = 1L;
        }

        long result = 0;

        for(int i = 1; i < 10; i++) {
            result += getResult(length, i);
        }

        System.out.println(result % 1000000000);

    }

    public static long getResult(int length, int value) {
        if(length == 1) {
            return arr[length][value];
        }

        if(arr[length][value] == null) {
            if(value == 0) {
                arr[length][value] = getResult(length - 1, 1) % 1000000000;
            }

            else if(value == 9) {
                arr[length][value] = getResult(length - 1, 8) % 1000000000;
            }

            else {
                arr[length][value] = (getResult(length - 1, value + 1) +
                        getResult(length - 1, value - 1)) % 1000000000;
            }
        }

        return arr[length][value];
    }
}
