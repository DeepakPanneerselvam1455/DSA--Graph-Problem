/*You are working as a data security analyst in a cyber-surveillance unit.
Your system receives encoded messages from agents in the field.
These messages contain a hidden code word that follows a specific rule:

The hidden code word is the longest substring of the message that:

Starts and ends with the same letter (case-insensitive).

Contains at least one vowel (a, e, i, o, u).

Does not contain any digit.

If there are multiple substrings with the same length, choose the lexicographically smallest one.

If no such substring exists, return "NO CODE".

Test case 01 

message = "abxbae"

Output: abxba

Test case 02 

message = "xy123z"
Output: NO CODE

Test case 03 :

message = "momentsareawesome"
Output: momentsaream
*/
import java.util.*;
public class Q7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        System.out.println(findHiddenCode(message));
    }

    private static String findHiddenCode(String message) {
        int n = message.length();
        String longestCode = "";
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (message.charAt(i) == message.charAt(j) && isValidSubstring(message, i, j)) {
                    String candidate = message.substring(i, j + 1);
                    if (candidate.length() > longestCode.length() ||
                        (candidate.length() == longestCode.length() && candidate.compareTo(longestCode) < 0)) {
                        longestCode = candidate;
                    }
                }
            }
        }
        return longestCode.isEmpty() ? "NO CODE" : longestCode;
    }

    private static boolean isValidSubstring(String message, int start, int end) {
        boolean hasVowel = false;
        for (int i = start; i <= end; i++) {
            char c = message.charAt(i);
            if (Character.isDigit(c)) return false;
            if ("aeiouAEIOU".indexOf(c) != -1) hasVowel = true;
        }
        return hasVowel;
    }
}
