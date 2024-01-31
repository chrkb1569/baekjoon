import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Josephus {
    private static List<Integer> resultList = new LinkedList<>();
    private static boolean[] visitArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int person = Integer.parseInt(st.nextToken());
        int index = Integer.parseInt(st.nextToken());

        initVisitArr(person);
        mkResultList(index);

        printResult();
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length];
    }

    private static void mkResultList(int index) {
        int person = 0;
        int count = 0;

        for(int i = 0; ; i = (i+1) % visitArr.length) {
            if(visitArr[i]) continue;

            count++;

            if(count == index) {
                count = 0;
                person++;
                visitArr[i] = true;
                resultList.add(i+1);
            }

            if(person == visitArr.length) break;
        }
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        for(int value : resultList) {
            sb.append(value).append(", ");
        }

        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append(">");

        System.out.println(sb);
    }
}
