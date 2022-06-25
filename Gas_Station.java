import java.io.*;
import java.util.StringTokenizer;

public class Gas_Station {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int city = Integer.parseInt(br.readLine());
        long[] distance = new long[city - 1];
        long[] oil_price = new long[city];
        long result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < city - 1; i++) {
            distance[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");

        for (int j = 0; j < city; j++) {
            oil_price[j] = Long.parseLong(st.nextToken());
        }

        for(int i = 0; i < city-1; i++) {
            if(oil_price[i] < oil_price[i+1]) {
                oil_price[i+1] = oil_price[i];
            }
        }

        for(int j = 0; j < city-1; j++) {
            result += oil_price[j] * distance[j];
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}