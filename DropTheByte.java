import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DropTheByte {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String cypher = br.readLine(); // 사용자로부터 암호화된 문자열을 입력 받음
        int N = Integer.parseInt(br.readLine()); // 암호를 구성하는 값의 개수
        String[] wordsInfo = br.readLine().split(" "); // 값의 자료형 정보

        String[] cypherArr = generateCypherArr(cypher, N, wordsInfo);

        System.out.println(getResultString(cypherArr));
    }

    private static String[] generateCypherArr(String cypher, int N, String[] wordsInfo) {
        String[] cypherArr = new String[N];
        int wordIndex = 0;

        for(int index = 0; index < N; index++) {
            String wordInfo = wordsInfo[index];
            int length = getLength(wordInfo);

            cypherArr[index] = cypher.substring(wordIndex, wordIndex + length);
            wordIndex += length;
        }

        return cypherArr;
    }

    private static int getLength(String info) {
        if("char".equals(info)) return 2;
        if("int".equals(info)) return 8;
        return 16;
    }

    private static String getResultString(String[] cypherArr) {
        StringBuilder sb = new StringBuilder();

        for(int index = 0; index < cypherArr.length; index++) {
            String cypher = cypherArr[index];
            String plainText = decrypt(cypher);
            sb.append(plainText);

            if(index == cypherArr.length - 1) continue;

            sb.append(" ");
        }

        return sb.toString();
    }

    private static String decrypt(String cypher) {
        return String.valueOf(Long.parseLong(cypher, 16));
    }
}
