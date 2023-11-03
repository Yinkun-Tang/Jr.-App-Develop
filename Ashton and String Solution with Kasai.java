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

        int[] SuffixArray = new int[size];
        for (int i = 0; i < size; i++) {
            SuffixArray[i] = size - suffixes[i].length();
        }

        int[] LCP = new int[size];
        LCP[0] = 0;

        int Kasai_k = 0;
        int[] rank = new int[size];

        for (int i = 0; i < size; i++) {
            rank[SuffixArray[i]] = i;
        }

        for (int i = 0; i < size; i++) {
            if (Kasai_k > 0) {
                Kasai_k = Kasai_k - 1;
            }
            if (rank[i] == (size - 1)) {
                Kasai_k = 0;
                continue;
            }
            int j = SuffixArray[rank[i] + 1];
            while ((i + Kasai_k) < size && (j + Kasai_k) < size && (s.charAt(i + Kasai_k) == s.charAt(j + Kasai_k))) {
                Kasai_k = Kasai_k + 1;
            }
            LCP[rank[i] + 1] = Kasai_k;
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
