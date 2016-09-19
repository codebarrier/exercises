package com.learning;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by maruthi on 19/09/16.
 * https://www.hackerrank.com/challenges/mandragora
 */
public class MandragoraForest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        for (int i = 0; i < testCount; i++) {
            int n = scanner.nextInt();
            int[] health = new int[n];
            for (int j = 0; j < n; j++) {
                health[j] = scanner.nextInt();
            }

            long s = 1;
            long totalSum = 0;
            Arrays.sort(health);
            for (int j = 0; j < n; j++) {
                totalSum += health[j];
            }
            long maxp = totalSum;

            for (int j = 0; j < n; j++) {
                s++;
                totalSum -= health[j];
                long p = totalSum * s;

                if (maxp < p) {
                    maxp = p;
                } else {
                    break;
                }
            }

            System.out.println(maxp);
        }
    }
}
