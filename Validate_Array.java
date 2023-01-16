import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Validate_Array {

    public static Set<Integer> resultSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int component = Integer.parseInt(br.readLine());

        initResultSet();

        for(int i = 0; i < component; i++) {
            resultSet.add(Integer.parseInt(br.readLine()));
        }

        System.out.println(findMinValue());
    }

    public static int findMinValue() {
        int countValue = Integer.MAX_VALUE;

        for(int value : resultSet) {
            int count = 0;

            for(int i = 0; i < 5; i++) {
                if(!resultSet.contains(value + i)) count++;
            }

            countValue = Math.min(countValue, count);
        }

        return countValue;
    }

    public static void initResultSet() {
        resultSet = new HashSet<>();
    }
}
