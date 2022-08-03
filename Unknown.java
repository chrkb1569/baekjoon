import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Unknown {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        List<String> lst = new LinkedList<>();

        for(int i = 0; i < num1; i++) {
            set.add(br.readLine());
        }

        for(int j = 0; j < num2; j++) {
            String value = br.readLine();
            if(set.contains(value)) {
                lst.add(value);
            }
        }

        Collections.sort(lst);

        sb.append(lst.size()).append("\n");

        for(String s : lst) {
            sb.append(s).append("\n");
        }

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
