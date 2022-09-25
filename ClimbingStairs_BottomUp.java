import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClimbingStairs_BottomUp {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stairs = Integer.parseInt(br.readLine());

        int[] value = new int[stairs + 1];
        Integer[] result = new Integer[stairs + 1];

        for(int i = 1; i <= stairs; i++) {
            value[i] = Integer.parseInt(br.readLine());
        }

        result[0] = value[0];
        result[1] = value[1];

        if(stairs >= 2) {
            result[2] = value[1] + value[2];
        }

        for(int i = 3; i <= stairs; i++) {
            result[i] = Math.max(result[i - 2], result[i - 3] + value[i - 1]) + value[i];
        }

        System.out.println(result[stairs]);
    }
}
