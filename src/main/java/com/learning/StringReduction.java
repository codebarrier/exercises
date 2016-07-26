package com.learning;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by maruthi on 25/07/16.
 */
public class StringReduction {

    static Map<String, Integer> map = new HashMap<>();

    static int stringRed(String a) {
        char[] arr = a.toCharArray();
        Arrays.sort(arr);
        a = new String(arr);
        if (map.get(a) != null) {
            return map.get(a);
        }

        int min = a.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < a.length() - 1; i++) {
            if (a.charAt(i) != a.charAt(i + 1)) {
                char newChar = getNewChar(a.charAt(i), a.charAt(i + 1));
                builder.append(a.substring(0, i));
                builder.append(newChar);
                builder.append(a.substring(i + 2));
                int res = stringRed(builder.toString());
                builder.setLength(0);
                if (res < min) {
                    min = res;
                }
            }
        }
        map.put(a, min);
        return min;
    }

    private static char getNewChar(char c, char c1) {
        if (c == 'a' && c1 == 'b') {
            return 'c';
        } else if (c == 'b' && c1 == 'a') {
            return 'c';
        } else if (c == 'a' && c1 == 'c') {
            return 'b';
        } else if (c == 'c' && c1 == 'a') {
            return 'b';
        } else if (c == 'b' && c1 == 'c') {
            return 'a';
        } else {
            return 'a';
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;

        int _t_cases = Integer.parseInt(in.nextLine().trim());
        for (int _t_i = 0; _t_i < _t_cases; _t_i++) {
            String _a = in.nextLine().trim();
            res = stringRed(_a);
            System.out.println(res);
        }
    }
}
