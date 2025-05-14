import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class WhatTheFoxSay {

    private static Map<String, String> noiseMap = new HashMap<>();

    private final static String WHAT_THE_FOX_SAY = "what does the fox say?";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        for(int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            initNoiseMap();

            while(true) {
                String noise = br.readLine();

                if(WHAT_THE_FOX_SAY.equals(noise)) break;

                String[] noiseArr = noise.split(" ");

                noiseMap.put(noiseArr[2], noiseArr[0]);
            }

            mkFoxString(st, sb);

            if(testCase != T - 1) sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void initNoiseMap() {
        noiseMap.clear();
    }

    private static void mkFoxString(StringTokenizer st, StringBuilder sb) {
        for(;st.hasMoreTokens();) {
            String noise = st.nextToken();

            if(noiseMap.containsKey(noise)) continue;

            sb.append(noise);

            if(st.hasMoreTokens()) sb.append(" ");
        }
    }
}
