import java.io.*;

public class Sum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int result;

        result = get_result(N);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int get_result(int n) {
        int number_of_sum;
        int enable_count = 0, result = 0;

        for(int i = 0; i <= n; i++) {
            number_of_sum = get_number(i);
            if((number_of_sum + i) == n && (enable_count == 0)) {
                result = i;
                enable_count++;
            }
        }

        if(result == 0) {
            return 0;
        }

        else return result;
    }

    public static int get_number(int n) {
        String str = String.valueOf(n);
        int sum = 0;

        for(int i = 0; i < str.length(); i++) {
            sum += (str.charAt(i) - 48);
        }

        return sum;
    }
}
