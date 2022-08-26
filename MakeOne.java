import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MakeOne {

    static Integer arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());

        arr = new Integer[number + 1];
        arr[0] = arr[1] = 0;

        System.out.println(getResult(number));
    }

    public static int getResult(int n) {
        if(arr[n] == null) {
            if(n % 6 == 0) {
                arr[n] = Math.min(Math.min(getResult(n/3), getResult(n/2)), getResult(n-1)) + 1;
            }
            else if(n % 3 == 0) {
                arr[n] = Math.min(getResult(n/3), getResult(n-1)) + 1;
            }
            else if(n % 2 == 0) {
                arr[n] = Math.min(getResult(n/2), getResult(n-1)) + 1;
            }
            else {
                arr[n] = getResult(n-1) + 1;
            }
        }

        return arr[n];
    }
}
