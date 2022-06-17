import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ATM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        int sum = 0;

        int[] arr = new int[num];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i < num; i++) {
            sum += arr[i] * (num - i);
        }

        bw.write(sum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
