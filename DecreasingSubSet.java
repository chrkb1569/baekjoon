import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DecreasingSubSet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] value = new int[N+1];
        int[] result = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 1; i <= N; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            result[i] = 1;
            for(int j = 0; j < i; j++) {
                if(value[i] < value[j] && result[i] <= result[j]) {
                    result[i] = result[j] + 1;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int s : result) {
            if(s >= max) {
                max = s;
            }
        }

        System.out.println(max);
    }
}
