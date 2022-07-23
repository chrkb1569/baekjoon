import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Solution_Report {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, List<String>> status = new HashMap<>();
        Map<String, Integer> ban = new HashMap<>();
        List<String> ban_list = new LinkedList<>();
        List<Integer> final_result = new LinkedList<>();

        reportStatus(report, status);
        banStatus(status, ban);
        makeBanList(id_list, k, ban, ban_list);

        for(String s : id_list) {
            if(status.containsKey(s)) {
                int size = status.get(s).size();
                status.get(s).removeAll(ban_list);
                final_result.add(size - status.get(s).size());
            }
            else {
                final_result.add(0);
            }
        }

        return final_result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void makeBanList(String[] id_list, int k, Map<String, Integer> ban, List<String> ban_list) {
        for(String s : id_list) {
            if(ban.containsKey(s) && ban.get(s) >= k) {
                ban_list.add(s);
            }
            else continue;
        }
    }

    private void banStatus(Map<String, List<String>> status, Map<String, Integer> ban) {
        for(Map.Entry<String, List<String>> entry : status.entrySet()) {
            for(String s : entry.getValue()) {
                ban.put(s, ban.getOrDefault(s, 0)+1);
            }
        }
    }

    private void reportStatus(String[] report, Map<String, List<String>> map) {
        StringTokenizer st;

        for(String s : report) {
            st = new StringTokenizer(s, " ");

            String reporter = st.nextToken();
            String subject = st.nextToken();

            List<String> lst = new LinkedList<>();

            if(!map.containsKey(reporter)) {
                lst.add(subject);
                map.put(reporter, lst);
            }
            else {
                if(map.get(reporter).contains(subject)) {
                    continue;
                }

                lst.add(subject);

                for (String z : map.get(reporter)) {
                    lst.add(z);
                }

                map.put(reporter, lst);
            }
        }
    }
}

public class Get_Report {
    public static void main(String[] args) {
        Solution_Report result = new Solution_Report();
        int[] arr = result.solution(
                new String[] {"muzi", "frodo", "apeach", "neo"},
                new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
                2);

        for(int s : arr) {
            System.out.println(s);
        }
    }
}
