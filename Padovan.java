import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Padovan {

    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());

        for(int i = 0; i < number; i++) {
            int length = Integer.parseInt(br.readLine());

            arr = new long[length + 1];

            for(int j = 0; j<= length; j++) {
                if(j == 0) {
                    arr[j] = 0L;
                }
                else if(j == 1) {
                    arr[j] = 1L;
                }
                else if(j == 2) {
                    arr[j] = 1L;
                }
                else {
                    arr[j] = arr[j - 2] + arr[j - 3];
                }
            }

            System.out.println(arr[length]);
        }
    }
}
