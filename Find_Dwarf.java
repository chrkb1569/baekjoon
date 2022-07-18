import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Find_Dwarf {

    static boolean[] selection = new boolean[9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dwarf = new int[9];

        for(int i = 0; i < 9; i++) {
            dwarf[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(dwarf);

        getSum(dwarf, selection, 9, 0, 0);
    }

    public static void getSum(int[] height, boolean[] selection, int total, int start, int count) {
        if(count == 7) {
            int sum = 0;
            for(int i = 0; i < 9; i++) {
                if(selection[i] == true) {
                    sum += height[i];
                }
            }
            if(sum == 100) {
                print(height, selection);
                System.exit(0);
            }
            return;
        }

        for(int i = start; i < total; i++) {
            if(selection[i] != true) {
                selection[i] = true;
                getSum(height, selection, total, i+1, count+1);
                selection[i] = false;
            }
        }
    }

    public static void print(int[] height, boolean[] selection) {
        for(int i = 0; i < 9; i++) {
            if(selection[i] == true) {
                System.out.println(height[i]);
            }
        }
    }
}
