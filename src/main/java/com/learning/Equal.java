package com.learning;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by maruthi on 27/07/16.
 * https://www.hackerrank.com/challenges/equal
 * <p/>
 * Your Equal submission got 23.08 points. TODO (Three TC fails)
 */
public class Equal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        for (int j = 0; j < testCount; j++) {
            int n = scanner.nextInt();
            int[] values = new int[n];
            for (int i = 0; i < n; i++) {
                values[i] = scanner.nextInt();
            }


            Arrays.sort(values);
            System.out.println(getChangeCount(values, 0));
            map.clear();
        }
    }

    static Map<Integer, Long> map = new HashMap<>();

    private static long getChangeCount(int[] values, int pos) {
        if (pos >= values.length - 1) {
            return 0;
        }

        //System.out.println(Arrays.toString(values));

        int diff = values[pos + 1] - values[pos];
        long stepcnt = 0;
        int val1 = 0;
        if (diff >= 5) {
            int diffMul = diff / 5;
            val1 = 5 * diffMul;
            stepcnt += diffMul;
            diff = diff % 5;
        }

        int val2 = 0;
        if (diff >= 2) {
            int diffMul = diff / 2;
            val2 = 2 * diffMul;
            stepcnt += diffMul;
            diff = diff % 2;
        }

        stepcnt += diff;

        int totalValue = val1 + val2 + diff;

        for (int i = pos + 2; i < values.length; i++) {
            values[i] = values[i] + totalValue;
        }

        Arrays.sort(values);
        stepcnt += getChangeCount(values, pos + 1);

        return stepcnt;
    }
}
