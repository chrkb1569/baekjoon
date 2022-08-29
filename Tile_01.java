import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tile_01 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tile = Integer.parseInt(br.readLine());

        arr = new int[tile + 1];

        if(tile == 0) {
            arr[0] = 0;
        }

        else if(tile == 1) {
            arr[0] = 0;
            arr[1] = 1;
        }

        else {
            arr[0] = 0;
            arr[1] = 1;
            arr[2] = 2;
            for(int i = 3; i <= tile; i++) {
                arr[i] = (arr[i - 1] + arr[i - 2]) % 15746;
            }
        }

        System.out.println(arr[tile]);
    }
}
