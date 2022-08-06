import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class GetWood {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        List<Long> wood = new LinkedList<>();

        long woods = Long.parseLong(st.nextToken());
        long needs = Long.parseLong(st.nextToken());
        long minValue = 0;
        long maxValue = Long.MIN_VALUE;

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < woods; i++) {
            long value = Long.parseLong(st.nextToken());

            wood.add(value);

            if(value >= maxValue) {
                maxValue = value;
            }
        }

        while(minValue < maxValue) {
            long sum = 0;
            long midValue = (minValue + maxValue)/2;

            for(long s : wood) {
                if(s > midValue) {
                    sum += (s - midValue);
                }
            }

            if(sum < needs) {
                maxValue = midValue;
            }

            else {
                minValue = midValue + 1;
            }
        }

        System.out.println(minValue - 1);
    }
}