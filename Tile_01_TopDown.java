import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tile_01_TopDown {

    static Integer[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());

        arr = new Integer[number + 1];

        System.out.println(getResult(number));
    }

    public static int getResult(int number) {
        if(number == 0) {
            return 0;
        }
        else if(number == 1) {
            return 1;
        }

        else if(number == 2) {
            return 2;
        }

        if(arr[number] == null) {
            arr[number] = (getResult(number - 1) + getResult(number - 2)) % 15746;
        }

        return arr[number];
    }
}
