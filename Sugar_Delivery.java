import java.io.*;

public class Sugar_Delivery {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int result = 0, enable = 0;

        for(int i = N/5; i >=0; i--) {
            for(int j = 0; j <= N/3; j++) {
                if((5 * i + 3 * j) == N && enable == 0) {
                    result = i+j;
                    enable++;
                    break;
                }
            }
        }

        if(result == 0) {
            bw.write(-1 + "\n");
        }

        else {
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
