import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N_Queen {

    static int[] arr;
    static int number;

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        number = Integer.parseInt(br.readLine());

        arr = new int[number + 1];

        getResult(0);

        System.out.println(count);
    }

    public static void getResult(int length) {
        if(!isValidate(length)) {
            return;
        }
        else if(length == number) {
            count++;
            return;
        }

        for(int i = 1; i <= number; i++) {
            arr[length + 1] = i;

            getResult(length + 1);
        }

        return;
    }

    public static boolean isValidate(int index) {
        for(int i = 1; i <index; i++) {
            if(arr[i] == arr[index]) {
                return false;
            }

            else if(index - i == Math.abs(arr[i] - arr[index])) {
                return false;
            }
        }
        return true;
    }
}
