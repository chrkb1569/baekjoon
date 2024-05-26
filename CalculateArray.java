import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CalculateArray {
    private static List<List<Integer>> resultList = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int R = Integer.parseInt(st.nextToken()); // height
        int C = Integer.parseInt(st.nextToken()); // width
        int K = Integer.parseInt(st.nextToken()); // value

        initResultList();

        for(int height = 0; height < 3; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultList(st, height);
        }

        if(checkValidation(R, C, K)) {
            System.out.println(0);
            return;
        }

        int minTime = -1;

        for(int time = 1; time <= 100; time++) {
            sortResultList();

            if(checkValidation(R, C, K)) {
                minTime = time;
                break;
            }
        }

        System.out.println(minTime);
    }

    private static void initResultList() {
        for(int height = 0; height < 3; height++) {
            resultList.add(new LinkedList<>());
        }
    }

    private static void mkResultList(StringTokenizer st, int height) {
        for(;st.hasMoreTokens();) {
            resultList.get(height).add(Integer.parseInt(st.nextToken()));
        }
    }

    private static boolean checkValidation(int height, int width, int value) {
        if(resultList.size() < height || resultList.get(0).size() < width) return false;

        int reqValue = resultList.get(height-1).get(width-1);

        return reqValue == value;
    }

    private static void sortResultList() {
        int height = resultList.size();
        int width = resultList.get(0).size();

        if(height >= width) {
            commandR();
            return;
        }

        commandC();
    }

    // 행 >= 열, 모든 행에 대해서 정렬
    private static void commandR() {
        int maxLength = Integer.MIN_VALUE;

        for(int height = 0; height < resultList.size(); height++) {
            Map<Integer, Integer> resultMap = mkMap(resultList.get(height));
            List<Integer> sortedList = convertToList(resultMap);

            maxLength = Math.max(maxLength, sortedList.size());

            resultList.get(height).clear();
            resultList.get(height).addAll(sortedList);
        }

        for(int height = 0; height < resultList.size(); height++) {
            List<Integer> currentList = resultList.get(height);
            int currentLength = currentList.size();

            if(currentLength == maxLength) continue;

            for(int index = 0; index < maxLength - currentLength; index++) {
                currentList.add(0);
            }
        }
    }

    // 행 < 열, 모든 열에 대해서 정렬
    private static void commandC() {
        int maxLength = Integer.MIN_VALUE;

        List<List<Integer>> binList = new LinkedList<>();

        for(int width = 0; width < resultList.get(0).size(); width++) {
            binList.add(new LinkedList<>());
        }

        for(int width = 0; width < resultList.get(0).size(); width++) {
            List<Integer> valueList = extractValueList(width);
            Map<Integer, Integer> resultMap = mkMap(valueList);
            List<Integer> sortedList = convertToList(resultMap);

            maxLength = Math.max(maxLength, sortedList.size());

            binList.get(width).addAll(sortedList);
        }

        for(int height = 0; height < binList.size(); height++) {
            List<Integer> currentList = binList.get(height);
            int currentLength = currentList.size();

            if(currentLength == maxLength) continue;

            for(int index = currentLength; index < maxLength; index++) {
                currentList.add(0);
            }
        }

        resultList = mkFinalList(binList);
    }

    private static Map<Integer, Integer> mkMap(List<Integer> valueList) {
        Map<Integer, Integer> resultMap = new HashMap<>();

        for(int value : valueList) {
            if(value == 0) continue;
            resultMap.put(value, resultMap.getOrDefault(value, 0) + 1);
        }

        return resultMap;
    }

    private static List<Integer> convertToList(Map<Integer, Integer> resultMap) {
        PriorityQueue<Component> resultQueue = new PriorityQueue<>();
        List<Integer> valueList = new LinkedList<>();

        for(Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            resultQueue.add(new Component(entry.getKey(), entry.getValue()));
        }

        while(!resultQueue.isEmpty()) {
            Component component = resultQueue.poll();

            valueList.add(component.getNumber());

            if(valueList.size() >= 100) break;

            valueList.add(component.getCount());

            if(valueList.size() >= 100) break;
        }

        return valueList;
    }

    private static List<Integer> extractValueList(int width) {
        List<Integer> valueList = new LinkedList<>();

        for(int height = 0; height < resultList.size(); height++) {
            valueList.add(resultList.get(height).get(width));
        }

        return valueList;
    }

    private static List<List<Integer>> mkFinalList(List<List<Integer>> valueList) {
        List<List<Integer>> finalList = new LinkedList<>();

        for(int height = 0; height < valueList.get(0).size(); height++) {
            finalList.add(new LinkedList<>());
        }

        for(int width = 0; width < valueList.size(); width++) {
            List<Integer> currentList = valueList.get(width);

            for(int height = 0; height < valueList.get(0).size(); height++) {
                int currentValue = currentList.get(height);
                finalList.get(height).add(currentValue);
            }
        }

        return finalList;
    }

    private static class Component implements Comparable<Component> {
        private int number;
        private int count;

        public Component(int number, int count) {
            this.number = number;
            this.count = count;
        }

        public int getNumber() {
            return this.number;
        }

        public int getCount() {
            return this.count;
        }

        @Override
        public int compareTo(Component component) {
            if(this.count != component.getCount()) return this.count - component.getCount();
            return this.number - component.getNumber();
        }
    }
}
