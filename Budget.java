import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Budget {

    static long[] budget;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());
        long minValue = 0;
        long maxValue = Long.MIN_VALUE;
        long midValue = 0;

        budget = new long[number];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < number; i++) {
            long value = Long.parseLong(st.nextToken());

            budget[i] = value;

            if(value >= maxValue) {
                maxValue = value;
            }
        }

        long money = Long.parseLong(br.readLine());

        while(minValue <= maxValue) {
            long sum = 0;
            midValue = (minValue + maxValue) / 2;

            for(long s : budget) {
                sum += (((s - midValue) > 0)? midValue : s);
            }

            if(sum <= money) {
                minValue = midValue + 1;
            }
            else {
                maxValue = midValue - 1;
            }
        }

        System.out.println(maxValue);
    }
}
