import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Cup_Holder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int person = Integer.parseInt(br.readLine());

        bw.write(Math.min(person, br.readLine().replace("LL", "S").length()+1) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
