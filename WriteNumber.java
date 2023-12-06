import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WriteNumber {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long result = 0;
        int div = 10;
        int length = 1;

        int N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++) {
            if(i / div == 0) {
                result += length;
                continue;
            }

            div *= 10;
            length++;
            result += length;
        }

        System.out.println(result);
    }
}
