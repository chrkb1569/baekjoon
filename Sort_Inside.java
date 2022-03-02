import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

class One_word implements Comparable{
    char word;

    public One_word(char ch) {
        this.word = ch;
    }

    @Override
    public int compareTo(Object o) {
        One_word ow = (One_word)o;

        return ow.word - this.word;
    }

    @Override
    public String toString() {
        return word + "";
    }
}

public class Sort_Inside {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        One_word[] arr= new One_word[str.length()];

        for(int i =0; i < str.length(); i++) {
            arr[i] = new One_word(str.charAt(i));
        }

        Arrays.sort(arr);

        for(One_word i : arr) {
            System.out.print(i);
        }
    }
}
