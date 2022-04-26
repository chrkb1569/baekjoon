import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Coin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int coin, money, result = 0;

        coin = Integer.parseInt(st.nextToken());
        money = Integer.parseInt(st.nextToken());

        int[] value = new int[coin];

        for(int i = 0; i < coin; i++) {
            value[i] = Integer.parseInt(br.readLine());
        }

        int count = coin-1;

        while(true) {
            if(money / value[count] > 0) {
                result += money / value[count];
                money = money % value[count];
                count--;
            }

            else {
                count--;
            }

            if(count < 0) break;
        }

        System.out.println(result);
    }
}
