import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class FrequencySort {

    private static int[] resultArr;

    private static Map<Integer, Element> resultMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        initResultArr(N);

        st = new StringTokenizer(br.readLine(), " ");
        mkResultArr(st);

        mkResultMap();

        List<Map.Entry<Integer, Element>> resultList = convertToList();
        printResult(resultList);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkResultMap() {
        for(int index = 0; index < resultArr.length; index++) {
            int value = resultArr[index];

            if(resultMap.containsKey(value)) {
                Element element = resultMap.get(value);
                element.updateFrequency();
                resultMap.put(value, element);

                continue;
            }

            Element element = new Element(index);
            resultMap.put(value, element);
        }
    }

    private static List<Map.Entry<Integer, Element>> convertToList() {
        List<Map.Entry<Integer, Element>> resultList = new ArrayList<>(resultMap.entrySet());

        resultList.sort(Comparator.comparing(Map.Entry::getValue));

        return resultList;
    }

    private static void printResult(List<Map.Entry<Integer, Element>> resultList) {
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<Integer, Element> entry : resultList) {
            int value = entry.getKey();
            int frequency = entry.getValue().getFrequency();

            for(int i = 0; i < frequency; i++) {
                sb.append(value).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static class Element implements Comparable<Element>{
        private int initialIndex;
        private int frequency;

        public Element(int initialIndex) {
            this.initialIndex = initialIndex;
            this.frequency = 1;
        }

        public int getInitialIndex() {
            return this.initialIndex;
        }

        public int getFrequency() {
            return this.frequency;
        }

        public void updateFrequency() {
            this.frequency++;
        }

        @Override
        public int compareTo(Element element) {
            int frequency = element.getFrequency();
            int initialIndex = element.getInitialIndex();

            if(this.frequency != frequency) return -1 * Integer.compare(this.frequency, frequency);
            return Integer.compare(this.initialIndex, initialIndex);
        }
    }
}
