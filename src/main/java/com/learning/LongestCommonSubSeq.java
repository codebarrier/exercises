package com.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by maruthi on 12/07/16.
 * https://www.hackerrank.com/challenges/dynamic-programming-classics-the-longest-common-subsequence
 * Used the algorithm described in the youtube video https://www.youtube.com/watch?v=NnD96abizww . Found it to be great.
 */
public class LongestCommonSubSeq {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            b[i] = scanner.nextInt();
        }

        int[][] grid = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i] == b[j]) {
                    grid[i][j] = grid[i - 1][j - 1] + 1;
                } else {
                    int max = grid[i][j - 1];
                    if (max < grid[i - 1][j]) {
                        max = grid[i - 1][j];
                    }
                    grid[i][j] = max;
                }
            }
        }
        //System.out.println("Size = " + grid[n][m]);
        int xlim1 = n;
        int ylim1 = m;

        StringBuilder builder1 = new StringBuilder();
        List<Integer> result = new ArrayList<>();

        while (xlim1 > 0 && ylim1 > 0) {
            if (a[xlim1] == b[ylim1]) {
                result.add(a[xlim1]);
                xlim1--;
                ylim1--;
            } else {
                if (grid[xlim1][ylim1 - 1] < grid[xlim1 - 1][ylim1]) {
                    xlim1--;
                } else {
                    ylim1--;
                }
            }
        }

        for (int i = result.size() - 1; i >= 0; i--) {
            builder1.append(result.get(i));
            builder1.append(" ");
        }
        System.out.println(builder1.toString().trim());
    }
}
