import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Prime_Box_2 {
    public int number;

    public Prime_Box_2(int number) {
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

public class Prime_Number_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0, min_count = 0, min_num = 0;
        int bottom = Integer.parseInt(br.readLine());
        int top = Integer.parseInt(br.readLine());

        Prime_Box_2[] arr = new Prime_Box_2[top-bottom+1];

        for(int i = bottom; i <= top; i++) {
            arr[i-bottom] = new Prime_Box_2(i);
        }

        for(int j = bottom; j <= top; j++) {
            if(arr[j-bottom].Is_Prime(arr[j-bottom].number)) {
                if(min_count == 0) {
                    min_num = arr[j-bottom].number;
                    min_count++;
                }

                sum += arr[j-bottom].number;
            }
            else {
                continue;
            }
        }

        if(sum == 0) {
            System.out.println(-1);
        }

        else {
            System.out.println(sum);
            System.out.println(min_num);
        }
    }
}
