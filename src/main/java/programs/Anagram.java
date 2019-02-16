/*
Check for anagrams
An anagram is a word or a phrase that can be created by rearranging the letters of another given
word or phrase. We ignore white spaces and letter case. The all letters of "Desperation" can be
rearranged to the phrase "A Rope Ends It".
Implement a Java program that checks the given Strings to determine whether one is an anagram
of the other.
 */

package programs;

import java.util.Scanner;

public class Anagram {
    public static void main(String[] args) {
        System.out.println("Please enter the first word or phrase");
        Scanner scan = new Scanner(System.in);
        String str1 = scan.nextLine();
        System.out.println("Please enter the second word or phrase");
        String str2 = scan.nextLine();
        checkAnagram(str1, str2);
        scan.close();
    }

    private static void checkAnagram(String str1, String str2) {
        int count = 0;
        //Regular expression to remove spaces
        char[] ch1 = str1.toLowerCase().trim().replaceAll("\\s", "").toCharArray();
        char[] ch2 = str2.toLowerCase().trim().replaceAll("\\s", "").toCharArray();
        int len1 = ch1.length;
        int len2 = ch2.length;
        if (len1 == len2) {
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    if (ch1[i] == ch2[j]) {
                        count += 1;
                        break;
                    }
                }
            }
            if (count == len1) {
                System.out.println("Entered words/phrases are anagram of each other");
            } else {
                System.out.println("Entered words/phrases are not anagram of each other");
            }
        } else {
            System.out.println("Entered words/phrases are not anagram of each other");
        }
    }
}
