import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Time implements Comparable{
    public int start_time;
    public int end_time;

    public Time(int start, int end) {
        start_time = start;
        end_time = end;
    }

    @Override
    public int compareTo(Object ob) {
        Time t = (Time)ob;

        if(this.end_time != t.end_time) {
            return this.end_time - t.end_time;
        }

        else {
            return this.start_time - t.start_time;
        }
    }
}

public class Office_Arrangement {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int start, end;
        int start_value, end_value;
        List<Time> hour = new ArrayList<>();
        int meet = Integer.parseInt(br.readLine());

        for(int i = 0; i < meet; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            hour.add(new Time(start, end));
        }

        Collections.sort(hour);

        start_value = hour.get(0).start_time;
        end_value = hour.get(0).end_time;
        int enable_count = 1;

        for(int i = 1; i < hour.size(); i++) {
            if((hour.get(i).start_time >= end_value) && (hour.get(i).end_time >= end_value)) {
                start_value = hour.get(i).start_time;
                end_value = hour.get(i).end_time;
                enable_count++;
            }
        }

        bw.write(enable_count + "\n");
        bw.flush();
        bw.close();
        br.close();
        }
    }