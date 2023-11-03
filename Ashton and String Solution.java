import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;

class Result {

    /*
     * Complete the 'ashtonString' function below.
     *
     * The function is expected to return a CHARACTER.
     * The function accepts following parameters:
     * 1. STRING s
     * 2. INTEGER k
     */

    public static char ashtonString(String s, int k) {
        // Write your code here
        int size = s.length();
        int k_copy = k;

        String[] suffixes = new String[size];
        for (int i = 0; i < size; i++) {
            suffixes[i] = s.substring(i);
        }
        Arrays.sort(suffixes);

        int[] LCP = new int[size];
        LCP[0] = 0;
        for (int i = 1; i < size; i++) {
            LCP[i] = longestCommonPrefix(suffixes[i - 1], suffixes[i]);
        }

        char Result = ' ';
        int length = 0;
        boolean Found = false;
        int CurrLen = 0;

        for (int i = 0; i < size; i++) {
            length = suffixes[i].length() - LCP[i] + 1;
            Found = false;
            for (int j = 1; j < length; j++) {
                if (j + LCP[i] > 0) {
                    CurrLen = j + LCP[i];
                    if (k_copy > CurrLen) {
                        k_copy = k_copy - CurrLen;
                    } else {
                        String ResultStr = suffixes[i].substring(0, CurrLen);
                        Result = ResultStr.charAt(k_copy - 1);
                        Found = true;
                        break;
                    }
                }
            }
            if (Found) {
                break;
            }
        }

        return Result;
    }

    public static int longestCommonPrefix(String s1, String s2) {
        int minLen = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return i;
            }
        }
        return minLen;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                int k = Integer.parseInt(bufferedReader.readLine().trim());

                char res = Result.ashtonString(s, k);

                bufferedWriter.write(String.valueOf(res));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
