import java.io.*;
import java.util.*;

public class Index_Compression {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        String result = br.readLine();
        StringTokenizer st = new StringTokenizer(result, " ");
        Map<Integer, Integer> mp = new HashMap<>();

        for(int i = 0; i < num; i++) {
            mp.put(Integer.parseInt(st.nextToken()), 0);
        }

        TreeSet<Integer> key = new TreeSet<>(mp.keySet());
        List<Integer> key_lst = new ArrayList<>(key);

        int size = 0;

        for(Iterator itr = key.iterator(); itr.hasNext();) {
            mp.put((Integer)(itr.next()), size);
            size++;
        }

        StringTokenizer result_st = new StringTokenizer(result, " ");

        for(int k = 0; k < num; k++) {
            int value = Integer.parseInt(result_st.nextToken());
            if(mp.containsKey(value)){
                bw.write(mp.get(value) + " ");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}