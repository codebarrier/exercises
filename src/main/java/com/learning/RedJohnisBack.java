package com.learning;

import java.util.*;

/**
 * Created by maruthi on 19/09/16.
 * https://www.hackerrank.com/challenges/red-john-is-back
 */
public class RedJohnisBack {
    public static void main(String[] args) {
        fact(40);

        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        for (int i = 0; i < testCount; i++) {
            int n = scanner.nextInt();
            if (results.get(n) != null) {
                System.out.println(results.get(n));
                continue;
            }
            int fours = n / 4;
            int ones = n % 4;
            double numberofWays = 0;
            while (fours >= 0) {

                if (fours > 0 && ones > 0) {
                    numberofWays += facts.get(fours + ones) / (facts.get(fours) * facts.get(ones));
                } else {
                    numberofWays += 1;
                }

                fours--;
                ones += 4;

            }

            int primeCount = 0;
            for (double j = 2; j <= numberofWays; j++) {
                boolean prime = true;
                double max = Math.sqrt(j);
                for (Double primeVal : primeList) {
                    if (primeVal > max) {
                        break;
                    } else {
                        if (j % primeVal == 0) {
                            prime = false;
                            break;
                        }
                    }
                }

                if (prime) {
                    primeCount++;
                    primeList.add(j);
                }
            }

            results.put(n, primeCount);
            System.out.println(primeCount);
        }
    }

    static Map<Integer, Double> facts = new HashMap<>();
    static Map<Integer, Integer> results = new HashMap<>();
    static List<Double> primeList = new ArrayList<>();

    private static double fact(int num) {
        if (num <= 0) {
            return 1;
        }

        if (facts.get(num) != null) {
            return facts.get(num);
        }

        double val = num * fact(num - 1);
        facts.put(num, val);
        return val;
    }
}
