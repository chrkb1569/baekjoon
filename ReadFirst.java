import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFirst {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxValue = Integer.MIN_VALUE;

        String[] arr = new String[5];

        for(int i = 0; i < 5; i++) {
            String value = br.readLine();

            arr[i] = value;

            if(value.length() >= maxValue) {
                maxValue = value.length();
            }
        }

        for(int i = 0; i < maxValue; i++) {
            for(int j = 0; j < 5; j++) {
                if(arr[j].length() <= i) {
                    continue;
                }

                Character value = arr[j].charAt(i);

                System.out.print(value);
            }
        }
    }
}
