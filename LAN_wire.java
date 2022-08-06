import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LAN_wire {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int line = Integer.parseInt(st.nextToken());
        long quantity = Long.parseLong(st.nextToken());

        long[] lines = new long[line];

        long minValue = 0;
        long maxValue = Long.MIN_VALUE;

        for(int i = 0; i < line; i++) {
            long value = Long.parseLong(br.readLine());
            lines[i] = value;

            if(value > maxValue) {
                maxValue = value;
            }
        }

        maxValue++;

        while(minValue < maxValue) {
            long midValue = (minValue + maxValue) / 2;
            long count = 0;

            for(long s : lines) {
                count += (s / midValue);
            }

            if(count < quantity) {
                maxValue = midValue;
            }
            else {
                minValue = midValue + 1;
            }
        }

        System.out.println(minValue - 1);
    }
}
