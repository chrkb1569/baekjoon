import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddWith123 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        for(int i = 0; i < num; i++) {
            int number = Integer.parseInt(br.readLine());

            System.out.println(getResult(number, 0));
        }
    }

    public static int getResult(int n, int sum) {

        int result = 0;

        if(n == sum) {
            return 1;
        }

        if(n < sum){
            return 0;
        }

        if(n > sum) {
            int value1 = getResult(n, sum + 1);
            int value2 = getResult(n, sum + 2);
            int value3 = getResult(n, sum + 3);

            result += (value1 + value2 + value3);
        }

        return result;
    }
}
