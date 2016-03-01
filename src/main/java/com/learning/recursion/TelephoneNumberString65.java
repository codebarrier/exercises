package com.learning.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maruthi on 01/03/16.
 */
public class TelephoneNumberString65 {

    static Map<String, String> numberMap = new HashMap<>();

    static {
        numberMap.put("2", "abc");
        numberMap.put("3", "def");
        numberMap.put("4", "ghi");
        numberMap.put("5", "jkl");
        numberMap.put("6", "mno");
        numberMap.put("7", "prs");
        numberMap.put("8", "tuv");
        numberMap.put("9", "wxy");
    }

    public static void main(String[] args) {
        TelephoneNumberString65 main = new TelephoneNumberString65();
        main.listPermutationsFromNumbers("3");
    }

    private void listPermutationsFromNumbers(String phoneNumber) {
        String stringequivalent = "";
        for (int i = 0; i < phoneNumber.length(); i++) {
            stringequivalent = stringequivalent + (numberMap.get(String.valueOf(phoneNumber.charAt(i))) != null ? numberMap.get(String.valueOf(phoneNumber.charAt(i))) : "");
        }

        listPermutations("", stringequivalent);
    }

    private void listPermutations(String prefix, String value) {
        if (value.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < value.length(); i++) {
                char local = value.charAt(i);
                String rest = value.substring(0, i) + value.substring(i + 1);
                listPermutations(prefix + local, rest);
            }
        }
    }
}
