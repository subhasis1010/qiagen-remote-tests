/*
Find the smallest distance between two neighboring numbers in an array
Implement a Java function that finds two neighboring numbers in an array with the smallest distance to each other. The function should return the index of the first number.
In the sequence 4 8 6 1 2 9 4 the minimum distance is 1 (between 1 and 2). The function should return the index 3 (of number 1).
 */

package programs;

import java.util.*;

public class SmallestDistanceBetweenTwoNumbers {
    public static void main(String[] args) {
        int[] arr = {};
        int x = 1;
        do {
            try {
                System.out.println("Please enter the number of elements to be entered in the array");
                Scanner scan = new Scanner(System.in);
                int len = scan.nextInt();
                arr = new int[len];
                System.out.println("Enter the elements of the array");
                for (int i = 0; i < len; i++) {
                    arr[i] = scan.nextInt();
                }
                x = 2;
            } catch (InputMismatchException e) {
                System.out.println("You have entered a non integer value, please enter only integers. Lets start from beginning!!");
            }
        }
        while (x == 1);
        System.out.println("Index of the first number is " + findSmallestDistance(arr));
    }

    private static int findSmallestDistance(int[] arr) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length - 1; i++) {
            map.put(i, Math.abs(arr[i] - arr[i + 1]));
        }
        List<Integer> values = new ArrayList<Integer>(map.values());
        int smallestDistance = findMinimum(values);
        int firstIndex = 0;
        Set<Map.Entry<Integer, Integer>> entry = map.entrySet();
        for (Map.Entry<Integer, Integer> e : entry) {
            if (e.getValue() == smallestDistance) {
                firstIndex = e.getKey();
                break;
            }
        }
        return firstIndex;
    }

    private static int findMinimum(List<Integer> values) {
        int min = values.get(0);
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) < min) {
                min = values.get(i);
            }
        }
        return min;
    }
}
