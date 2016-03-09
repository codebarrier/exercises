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
        main.listWordsFromNumbers("324");
    }


    private void listWordsFromNumbers(String phoneNumber) {
        listWordsFromNumbers("", phoneNumber);
    }

    private void listWordsFromNumbers(String prefix, String value) {
        if (value.length() == 0) {
            System.out.println(prefix);
        } else {
            char local = value.charAt(0);
            String rest = value.substring(1);
            String alp = numberMap.get(String.valueOf(local));
            for (int j = 0; j < alp.length(); j++) {
                listWordsFromNumbers(prefix + String.valueOf(alp.charAt(j)), rest);
            }
        }
    }
}
