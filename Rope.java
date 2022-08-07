import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class Rope {
    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int value;

        int ropes = Integer.parseInt(br.readLine());
        Integer[] rope = new Integer[ropes];

        for(int i = 0; i < ropes; i++) {
            value = Integer.parseInt(br.readLine());
            rope[i] = value;
        }

        Arrays.sort(rope, Collections.reverseOrder());


        for(int i = 1; i <= rope.length; i++) {
            maxValue = Math.max(maxValue, rope[i-1] * i);
        }

        bw.write(maxValue + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
