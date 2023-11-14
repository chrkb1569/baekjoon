import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Stick {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());

        System.out.println(getBinaryCount(length));
    }

    public static int getBinaryCount(int length) {
        int count = 0;

        while(length != 0) {
            if(length % 2 == 1) count++;
            length /= 2;
        }

        return count;
    }
}
