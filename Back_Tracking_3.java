import java.io.*;
import java.util.StringTokenizer;

public class Back_Tracking_3 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int total = Integer.parseInt(st.nextToken());
        int quantity = Integer.parseInt(st.nextToken());
        int[] values = new int[total];
        int[] output = new int[quantity];

        Get_Values(values);

        Get_Result(values, output, total, quantity, 0);

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void Get_Values(int[] values) {
        for(int i = 0; i < values.length; i++) {
            values[i] = i+1;
        }
    }

    public static void Get_Result(int[] values, int[] output, int total, int r, int count){
        if(r == 0) {
            for(int s : output) {
                sb.append(s).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < total; i++) {
            output[count] = values[i];
            Get_Result(values, output, total, r-1, count+1);
            output[count] = 0;
        }
    }
}