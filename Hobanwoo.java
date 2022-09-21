import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Hobanwoo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;

        int goods = Integer.parseInt(br.readLine());

        int[] good = new int[goods];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < goods; i++) {
            good[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(good);

        if(goods % 2 == 0) {
            int start = goods / 2;

            for(int i = start; i < goods; i++) {
                sum += (2 * good[i]);
            }
        }

        else {
            int start = goods / 2;

            sum += good[start];

            for(int i = start + 1; i < goods; i++) {
                sum += (2*good[i]);
            }
        }

        System.out.println(sum);
    }
}
