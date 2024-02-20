import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Square_Root_Of_Integer {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        long sqrtValue = (long)Math.sqrt(N);

        if(sqrtValue * sqrtValue < N) sqrtValue++;

        System.out.println(sqrtValue);
    }
}