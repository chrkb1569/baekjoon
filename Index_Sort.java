import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class index implements Comparable {
    public int xpos;
    public int ypos;

    public index(int x, int y) {
        this.xpos = x;
        this.ypos = y;
    }

    @Override
    public String toString() {
        return xpos + " " + ypos;
    }

    @Override
    public int compareTo(Object obj) {
        index i = (index)obj;

        if(this.xpos > i.xpos) {
            return 2;
        }

        else if(this.xpos < i.xpos) {
            return -2;
        }

        else {
            if(this.ypos > i.ypos) {
                return 1;
            }

            else if(this.ypos < i.ypos) {
                return -1;
            }
            else return 0;
        }
    }
}


public class Index_Sort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        int x, y;
        ArrayList<index> arr = new ArrayList<>();

        for(int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            arr.add(new index(x,y));
        }

        Collections.sort(arr);

        for(index i : arr) {
            bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
    }