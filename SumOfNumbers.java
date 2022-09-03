import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SumOfNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Long> set = new HashSet<>();
        int count = 0;

        int number = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < number; i++) {
            set.add(Long.parseLong(st.nextToken()));
        }

        int sum = Integer.parseInt(br.readLine());

        for(long s : set) {
            if(set.contains(sum - s)) {
                count++;
            }
        }

        System.out.println(count / 2);
    }
}
