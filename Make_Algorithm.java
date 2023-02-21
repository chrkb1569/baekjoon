import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Make_Algorithm {

    public static char[] resultArr; // 사용자로부터 암호가 될 수 있는 알파벳들을 저장하기 위한 배열 resultArr 선언
    public static StringBuilder sb = new StringBuilder(); //출력할 문자열을 저장하기 위한 클래스. 해당 클래스를 통하여 가능한 암호의 리스트를 한꺼번에 출력

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int key = Integer.parseInt(st.nextToken());
        int total = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(total);
        mkResultArr(st);

        boolean[] visit = new boolean[total]; // 해당 알파벳을 사용했는지, 사용하지 않았는지 판단하기 위한 논리값 배열 visit 선언 및 초기화

        Arrays.sort(resultArr); // 알파벳을 오름차순으로 정렬

        mkAlgorithm(visit, 0, 0, key); // 암호문을 만들기 위한 로직 실행

        System.out.println(sb.toString()); // StringBuilder 클래스를 통하여 문자열 출력
    }

    public static void mkAlgorithm(boolean[] visit, int start, int now, int key) {
        if(now == key) { // 암호를 만들기 위하여 필요한만큼의 알파벳이 사용되었을 경우
            int gatherCount = 0; // 모음 알파벳의 개수를 파악하기 위한 변수
            int consonantCount = 0; // 자음 알파벳의 개수를 파악하기 위한 변수
            String appendValue = ""; // 암호문을 저장하기 위한 문자열 변수

            for(int i = 0; i < visit.length; i++) {
                if(visit[i]) {
                    char value = resultArr[i]; // 사용한 알파벳 선택

                    // 해당 알파벳이 자음인지, 모음인지를 판단하여 변수값을 더해줌
                    if(value == 'a' || value == 'e' || value == 'i' || value == 'o' || value == 'u') gatherCount++;
                    else consonantCount++;

                    appendValue += value;
                }
            }

            // 만약 완성한 암호의 모음 개수가 1개 이하거나, 자음 개수가 2개 이하인 경우, 암호문으로 사용할 수 없기 때문에 보류해주는 로직
            if(gatherCount >= 1 && consonantCount >= 2) sb.append(appendValue + "\n");

            return;
        }

        for(int i = start; i < resultArr.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                mkAlgorithm(visit, i + 1, now + 1, key);
                visit[i] = false;
            }
        }
    }

    public static void mkResultArr(StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[numValue++] = st.nextToken().charAt(0);
        }
    }

    public static void initResultArr(int value) {
        resultArr = new char[value];
    }
}
