import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sum_Of_Numbers {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long S = Long.parseLong(br.readLine());

        int maxN = findMaxN(S);

        System.out.println(maxN);
    }

    private static int findMaxN(long value) {
        long sum = 0;
        int n = 0;

        for(int num = 1; ; num++) {
            if(sum > value) break;
            sum += num;
            n++;
        }

        return n-1;
    }
}
