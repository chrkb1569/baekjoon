import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bag {

    static int[][] arr;
    static int[] w;
    static int[] feel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int quantity = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());

        arr = new int[quantity + 1][weight + 1];
        w = new int[weight + 1];
        feel = new int[quantity + 1];

        for(int i = 1; i <= quantity; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            w[i] = Integer.parseInt(st.nextToken());
            feel[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= quantity; i++) {
            for(int j = 1; j <= weight; j++) {
                arr[i][j] = arr[i-1][j];

                if(j >= w[i]) {
                    arr[i][j] = Math.max(arr[i][j], arr[i-1][j - w[i]] + feel[i]);
                }
            }
        }

        System.out.println(arr[quantity][weight]);
    }
}
