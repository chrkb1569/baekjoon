import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class File_Organization {

    public static Map<String, Integer> resultMap; // 해당 확장자를 가지는 파일의 개수를 저장하기 위한 resultMap 선언

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int file = Integer.parseInt(br.readLine());

        initResultMap();
        StringTokenizer st;

        for(int i = 0; i < file; i++) {
            st = new StringTokenizer(br.readLine(), "."); // 마침표를 기준으로 파일의 이름과 확장자를 분리

            st.nextToken(); // 파일의 이름은 필요하기 않기 때문에 버려줌

            String value = st.nextToken(); // 파일의 확장자를 변수 value에게 대입

            resultMap.put(value, resultMap.getOrDefault(value, 0)+1); // 변수를 통하여 resultMap에 값을 저장
        }

        for(String value : resultMap.keySet().stream().sorted().collect(Collectors.toList())) {
            System.out.println(value + " " + resultMap.get(value)); // resultMap을 정렬하여 출력
        }
    }

    public static void initResultMap() {
        resultMap = new ConcurrentHashMap<>();
    }
}
