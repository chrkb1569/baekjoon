import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ecology {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        int count = 1;

        String value = br.readLine();

        while(value != null && !value.isEmpty()) {
            count++;
            map.put(value, map.getOrDefault(value, 0) + 1);
            value = br.readLine();
        }

        count--;

        List<String> lst = new LinkedList<>(map.keySet());

        Collections.sort(lst);

        for(String s : lst) {
            sb.append(s).append(" ").append(String.format("%.4f", ((double)map.get(s) / (double)count) * 100)).append("\n");
        }

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
