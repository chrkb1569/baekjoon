class Solution {
    public String solution(String new_id) {
        String answer = "";

        answer = new_id.toLowerCase().replaceAll("[^a-z\\d\\-_.]", "");

        answer = answer.replaceAll("\\.{2,}", ".");

        answer = answer.replaceAll("^[.]|[.]$", "");

        if(answer.isBlank() || answer.isEmpty()) {
            answer = "a";
        }

        if(answer.length() >= 16) {
            answer = answer.substring(0,15);
            answer = answer.replaceAll("[.]$","");
        }

        if(answer.length() <= 2) {
            StringBuilder sb = new StringBuilder(answer);
            while(true) {
                if(sb.length() == 3) {
                    break;
                }
                sb.append(answer.charAt(answer.length()-1));
            }
            answer = sb.toString();
        }

        return answer;
    }
}

public class Get_id {
    public static void main(String[] args) {
        Solution result = new Solution();
        result.solution("abcdefghijklmn.p");
    }
}