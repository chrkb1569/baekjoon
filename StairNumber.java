import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StairNumber {

    static long[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());

        arr = new long[length + 1][10];

        for(int i = 1; i < 10; i++) {
            arr[1][i] = 1L;
        }

        for(int i = 2; i <= length; i++) {
            for(int j = 0; j < 10; j++) {
                if(j == 0) {
                    arr[i][j] = arr[i - 1][1] % 1000000000;
                }
                else if(j == 9) {
                    arr[i][j] = arr[i - 1][8] % 1000000000;
                }
                else {
                    arr[i][j] = (arr[i - 1][j-1] + arr[i - 1][j+1]) % 1000000000;
                }
            }
        }

        long result = 0;

        for(int i = 0; i < 10; i++) {
            result += arr[length][i];
        }

        System.out.println(result % 1000000000);
    }
}
