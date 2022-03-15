import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.*;

class word_sort_result implements Comparable {
    private String len;

    public word_sort_result(String str) {
        this.len = str;
    }

    @Override
    public String toString() {
        return len;
    }

   @Override
    public int compareTo(Object obj) {
        word_sort_result ws = (word_sort_result)obj;

        if(this.len.length() > ws.len.length()) {
            return 2;
        }

        else if(this.len.length() < ws.len.length()) {
            return -2;
        }

        else {
            char[] arr1 = this.len.toCharArray();
            char[] arr2 = ws.len.toCharArray();

            for(int i = 0; i < arr1.length; i++) {
                if(arr1[i] > arr2[i]) {
                    return 1;
                }

                else if(arr1[i] < arr2[i]) {
                    return -1;
                }

                else {
                    continue;
                }
            }

            return 0;
        }
        }
    }

public class Word_Sort {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        ArrayList<word_sort_result> arr = new ArrayList<>();

        for(int i = 0; i < num; i++) {
            arr.add(new word_sort_result(br.readLine()));
        }

        Set<word_sort_result> st = new TreeSet<>(arr);
        ArrayList<word_sort_result> arr2 = new ArrayList<>(st);

        Collections.sort(arr2);

        for(int i = 0; i < arr2.size(); i++) {
            System.out.println(arr2.get(i));
        }
    }
}
