import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Big_Number {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        BigInteger bi = new BigInteger(st.nextToken());
        BigInteger bi2 = new BigInteger(st.nextToken());

        bw.write(bi.add(bi2) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
