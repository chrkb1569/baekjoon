import java.io.*;

public class Number_Of_End {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int count = 0, result = 0;

        for(int i = 0; ;i++) {
            if(String.valueOf(i).contains("666")) {
                result = i;
                count++;
            }

            if(count == N) {
                break;
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
