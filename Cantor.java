import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cantor {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String value = br.readLine();

        while(value != null && !value.isEmpty()) {
            int upper = Integer.parseInt(value);
            sb = new StringBuilder();

            String str = sb.append("-".repeat((int)Math.pow(3, upper))).toString();

            int length = str.length();

            customize(length, 0);

            System.out.println(sb);

            value = br.readLine();
        }
    }

    public static void customize(int length, int start) {
        if(length == 1) {
            return;
        }

        customize(length/3, start);
        makeBlank(length/3, start + length/3);
        customize(length/3, start + length/3 * 2);
    }

    public static void makeBlank(int length, int start) {
        sb.replace(start ,start + length, " ".repeat(length));
    }
}
