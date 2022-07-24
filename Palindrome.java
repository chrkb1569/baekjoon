import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Palindrome {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<Character, Integer> map = new HashMap<>();
        String str = br.readLine();

        int oneKey = 0, oddCounter = 0;

        for(char s : str.toCharArray()) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1) {
                oneKey++;
            }
            else if(entry.getValue() % 2 == 1){
                oddCounter++;
            }
        }

        if(oneKey > 1 || oddCounter + oneKey >=2) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        for(char s : map.keySet().stream().sorted().collect(Collectors.toList())) {
            for(int i = 0; i < map.get(s)/2; i++) {
                sb.append(s);
            }
        }

        StringBuilder builder = new StringBuilder(sb.toString());

        for(char s : map.keySet().stream().sorted().collect(Collectors.toList())) {
            for(int j = 0; j < map.get(s)%2; j++) {
                sb.append(s);
            }
        }

        sb.append(builder.reverse().toString());

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
