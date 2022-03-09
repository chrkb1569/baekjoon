import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Result implements Comparable {
    public int xpos;
    public int ypos;

    public Result(int x, int y) {
        this.xpos = x;
        this.ypos = y;
    }

    @Override
    public String toString() {
        return xpos + " " + ypos;
    }

    @Override
    public int compareTo(Object obj) {
        Result i = (Result)obj;

        if(this.ypos > i.ypos) {
            return 2;
        }

        else if(this.ypos < i.ypos) {
            return -2;
        }

        else {
            if(this.xpos > i.xpos) {
                return 1;
            }

            else if(this.xpos < i.xpos) {
                return -1;
            }
            else return 0;
        }
    }
}


public class Index_Sort_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        int x, y;
        ArrayList<Result> arr = new ArrayList<>();

        for(int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            arr.add(new Result(x,y));
        }

        Collections.sort(arr);

        for(Result i : arr) {
            bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}