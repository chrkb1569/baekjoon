import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class StringSet {

    public static Set<String> resultSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int setNumber = Integer.parseInt(st.nextToken());
        int lstNumber = Integer.parseInt(st.nextToken());

        for(int i = 0; i < setNumber; i++) {
            mkStringSet(br.readLine());
        }

        int count = 0;

        for(int i = 0; i < lstNumber; i++) {
            String lstComp = br.readLine();
            if(checkDup(resultSet, lstComp)) count++;
        }

        System.out.println(count);
    }

    public static void mkStringSet(String component) {
        resultSet.add(component);
    }

    public static boolean checkDup(Set<String> set, String component) {
        return set.contains(component)? true : false;
    }
}
