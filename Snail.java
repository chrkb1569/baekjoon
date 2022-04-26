import java.io.*;
import java.util.StringTokenizer;

public class Snail {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int count = 0;

        double up = Integer.parseInt(st.nextToken());
        double down = Integer.parseInt(st.nextToken());
        double height= Integer.parseInt(st.nextToken());

        count = (int)Math.ceil((height-down) / (up-down)) ;
        System.out.println(count);
    }
}
