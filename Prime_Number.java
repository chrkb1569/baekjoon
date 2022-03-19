import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Prime_Box {
    public int number;

    public Prime_Box(int number) {
        this.number = number;
    }

    public boolean Is_Prime(int number) {
        if(number == 1) {
            return false;
        }

        else if(number == 2) {
            return true;
        }
        else {
            for(int i = 2; i < number; i++) {
                if(number % i == 0) {
                    return false;
                }
                else continue;
            }
            return true;
        }
    }
}

public class Prime_Number {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Prime_Box_2[] arr = new Prime_Box_2[num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int prime_count = 0;

        for(int i = 0; i < num; i++) {
            arr[i] = new Prime_Box_2(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < num; i++) {
            if(arr[i].Is_Prime(arr[i].number)) {
                prime_count++;
            }
            else {
                continue;
            }
        }

        System.out.println(prime_count);

    }
}
