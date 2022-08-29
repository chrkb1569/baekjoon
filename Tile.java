import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tile {
    static Integer[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(br.readLine());


        arr = new Integer[target + 1];

        if(arr.length == 1) {
            arr[0] = 0;
        }

        else if(arr.length == 2) {
            arr[0] = 0;
            arr[1] = 1;
        }

        else {
            arr[0] = 0;
            arr[1] = 1;
            arr[2] = 2;
        }

        System.out.println(getResult(target));
    }

    public static int getResult(int number) {
        if(!(arr[number] == null)) return arr[number];

        else if(number > 2) {
            arr[number] = (getResult(number - 1) + getResult(number - 2)) % 10007;
        }

        return arr[number];
    }
}
