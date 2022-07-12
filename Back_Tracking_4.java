import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_Tracking_4 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int total = Integer.parseInt(st.nextToken());
        int quantity = Integer.parseInt(st.nextToken());

        int[] values = new int[total];
        int[] output = new int[quantity];

        Get_Values(total, values);

        Get_Result(values, output, 0,0, total, quantity);

        System.out.println(sb);
    }

    private static void Get_Values(int total, int[] values) {
        for(int i = 0; i < total; i++) {
            values[i] = i+1;
        }
    }

    public static void Get_Result(int[] values, int[] output, int count, int start, int total, int r) {
        if(r == 0) {
            for(int s : output) {
                sb.append(s).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = start; i < total; i++) {
            output[count] = values[i];
            Get_Result(values, output, count+1, i, total, r-1);
            output[count] = 0;
        }
    }

}
