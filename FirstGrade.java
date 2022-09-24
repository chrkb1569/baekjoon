import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FirstGrade {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());

        int[] value = new int[length-1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < length - 1; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        int target = Integer.parseInt(st.nextToken());

        long[][] arr = new long[value.length][21];

        arr[0][value[0]] = 1L;

        for(int i = 0; i < value.length; i++) {
            for(int j = 0; j < 21; j++) {
                if(arr[i][j] != 0) {
                    if(i+1 < value.length && j + value[i+1] >= 0 && j + value[i+1] <= 20) {
                        arr[i+1][j + value[i+1]] += arr[i][j];
                    }

                    if(i+1 < value.length && j - value[i+1] >= 0 && j - value[i+1] <= 20) {
                        arr[i+1][j - value[i+1]] += arr[i][j];
                    }
                }
            }
        }

        System.out.println(arr[value.length-1][target]);
    }
}
