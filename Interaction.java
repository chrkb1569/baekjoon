import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Interaction {
    private static int[][] resultArr = new int[26][200_000];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String word = br.readLine();
        int testCase = Integer.parseInt(br.readLine());

        mkResultArr(word);

        for(int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int result = getResult(st);
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static void mkResultArr(String word) {
        char[] arr = word.toCharArray();

        resultArr[arr[0] - 'a'][0]++;

        for(int i = 1; i < word.length(); i++) {
            resultArr[arr[i] - 'a'][i]++;

            for(int j = 0; j < 26; j++) {
                resultArr[j][i] += resultArr[j][i-1];
            }
        }
    }

    private static int getResult(StringTokenizer st) {
        char value = st.nextToken().charAt(0);
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        if(from == 0) return resultArr[value - 'a'][to];
        return resultArr[value - 'a'][to] - resultArr[value - 'a'][from - 1];
    }
}
