package com.learning;

import java.util.Scanner;

/**
 * Created by maruthi on 24/08/16.
 * https://www.hackerrank.com/challenges/richie-rich
 */
public class RichieRich {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String number = scanner.next();
        char[] numbers = number.toCharArray();
        int changedCount = 0;
        int diff = 0;
        for (int i = 0; i < (n / 2); i++) {
            if (numbers[i] != numbers[(n - 1) - i]) {
                diff++;
            }
        }
        int excess = k - diff;

        for (int i = 0; (changedCount < k) && (i < (n / 2)); i++) {
            if (numbers[i] > numbers[(n - 1) - i]) {
                if (excess > 0) {
                    if (numbers[(n - 1) - i] != '9' && numbers[i] != '9') {
                        numbers[(n - 1) - i] = '9';
                        numbers[i] = '9';
                        excess--;
                        changedCount++;
                    } else {
                        numbers[(n - 1) - i] = '9';
                        numbers[i] = '9';
                        changedCount++;
                    }
                } else {
                    numbers[(n - 1) - i] = numbers[i];
                    changedCount++;
                }
            } else if (numbers[i] < numbers[(n - 1) - i]) {
                if (excess > 0) {
                    if (numbers[(n - 1) - i] != '9' && numbers[i] != '9') {
                        numbers[(n - 1) - i] = '9';
                        numbers[i] = '9';
                        excess--;
                        changedCount++;
                    } else {
                        numbers[(n - 1) - i] = '9';
                        numbers[i] = '9';
                        changedCount++;
                    }
                } else {
                    numbers[i] = numbers[(n - 1) - i];
                    changedCount++;
                }
            } else if (excess > 1) {
                if (numbers[(n - 1) - i] != '9') {
                    numbers[(n - 1) - i] = '9';
                    excess--;
                }
                if (numbers[i] != '9') {
                    numbers[i] = '9';
                    excess--;
                }
            }
        }
        int rem = excess + (diff - changedCount);

        for (int i = 0; i < (n / 2) && rem > 0; i++) {
            if (numbers[i] != '9' && rem > 1) {
                numbers[(n - 1) - i] = '9';
                numbers[i] = '9';
                rem -= 2;
            }
        }

        if (n % 2 > 0 && rem > 0 && numbers[n / 2] != '9') {
            numbers[n / 2] = '9';
        }

        for (int i = 0; i < (n / 2); i++) {
            if (numbers[i] != numbers[(n - 1) - i]) {
                System.out.println("-1");
                return;
            }
        }
        System.out.println(new String(numbers));
    }
}
