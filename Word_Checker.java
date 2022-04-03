import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Word_Checker {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        String[] arr = new String[num];
        int count = 0, result_count = 0;

        for(int i = 0; i < num; i++) {
            arr[i] = br.readLine();
        }

        for(int j = 0; j < num; j++) {
            int[] arr_alpha = new int[26];
            char[] obj = arr[j].toCharArray();
            char box;

            if(obj.length == 1) {
                result_count++;
            }

            else {
                box = obj[0];
                arr_alpha[obj[0] - 97]++;

                for(int q = 0; q < obj.length; q++) {
                    if(q <= obj.length-1) {
                        if(box != obj[q]) {
                            if(arr_alpha[obj[q] - 97] != 0) {
                                count++;
                                break;
                            }
                            else {
                                arr_alpha[obj[q] - 97]++;
                                box = obj[q];
                            }
                        }
                    }
                }
                if(count == 0) {
                    result_count++;
                }
            }

            count = 0;
        }
        System.out.println(result_count);
    }
}
