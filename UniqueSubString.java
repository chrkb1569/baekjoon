import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class UniqueSubString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < str.length(); i++) {
            for(int j = 1; j<= str.length(); j++) {
                if(i+j >= str.length()) {
                    map.put(str.substring(i, str.length()), 0);
                }
                else {
                    map.put(str.substring(i, i+j), 0);
                }
            }
        }
        bw.write(map.size() + "\n");
        bw.flush();
        bw.close();
        br.close();

    }
}
