package Week7;

import java.util.Arrays;
import java.util.Random;

// YOUR GOOGLE SHEETS: https://docs.google.com/spreadsheets/d/1WRxC6zcLD7-vNKowksWEPKyj5mlUsNh1b3ImoD7d-PA/edit?usp=sharing

public class QuickSort {

    static long counter = 0;
    static Random rn = new Random();
    static int N;
    static long[] comparisonlist = new long[1];

    public static void main(String[] args) {

        System.out.println("\nSample Test");
        int[] test = {6, 2, 4, 7, 1, 3, 8, 5};
        sortQuick(test, 0, test.length - 1);
        System.out.println(Arrays.toString(test));

        comparisonlist = new long[17];
        System.out.println("\nFisher-Yates Test");

        for (int e = 0; e <= 16; e++) {
            counter = 0;
            N = (int)Math.pow(2, e);
            int list[] = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = i;
            }
            shuffleFY(list, N);
            sortQuick(list, 0, list.length - 1);
            comparisonlist[e] = counter;

        }

        System.out.println(Arrays.toString(comparisonlist));

        counter = 0;
        comparisonlist = new long[17];
        System.out.println("\nPre-sorted ascending");

        for (int e = 0; e <= 16; e++) {
            counter = 0;
            N = (int)Math.pow(2, e);
            int list[] = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = i;
            }
            sortQuick(list, 0, list.length - 1);
            comparisonlist[e] = counter;

        }

        System.out.println(Arrays.toString(comparisonlist));

        counter = 0;
        comparisonlist = new long[17];
        System.out.println("\nPre-sorted descending");

        for (int e = 0; e <= 16; e++) {
            counter = 0;
            N = (int)Math.pow(2, e);
            int list[] = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = N - i;
            }
            sortQuick(list, 0, list.length - 1);
            comparisonlist[e] = counter;

        }

        System.out.println(Arrays.toString(comparisonlist));
    }

    public static void sortQuick(int[] list, int first, int last) {
        if (first < last) {
            int pivot = pivotList(list, first, last);
            sortQuick(list, first, pivot-1);
            sortQuick(list, pivot+1, last);
        }
    }

    // From last to first
    /*public static int pivotList(int[] list, int first, int last) {
        int pivotValue = list[last];
        int pivotPoint = first - 1; // i representation
        for (int j = first; j <= last - 1; j++) {
            counter++;
            if (list[j] < pivotValue ) {
                pivotPoint++;
                int temp = list[pivotPoint];
                list[pivotPoint] = list[j];
                list[j] = temp;
            }
        }
        pivotPoint++;
        int temp = list[pivotPoint];
        list[pivotPoint] = list[last];
        list[last] = temp;
        return pivotPoint;
    }*/

    // From first to last
     /*public static int pivotList(int[] list, int first, int last) { // Base is first, last; To pivot at last switch first & last; if you want a random value as the pivot just generate a random number between first and last and then swap the first value and the value at the random index
        int pivotValue = list[first];
        int pivotPoint = first;
        for (int i = first + 1; i <= last; i++) {
            counter++;
            if (list[i] < pivotValue ) {
                pivotPoint = pivotPoint + 1;
                int temp = list[pivotPoint];
                list[pivotPoint] = list[i];
                list[i] = temp;
            }
        }
        int temp = list[first];
        list[first] = list[pivotPoint];
        list[pivotPoint] = temp;
        return pivotPoint;
    }*/

    // Random value between first & last
    public static int pivotList(int[] list, int first, int last) { // Base is first, last; To pivot at last switch first & last; if you want a random value as the pivot just generate a random number between first and last and then swap the first value and the value at the random index
        int random = first + (int)(Math.random() * ((last - first) + 1));

        int temp = list[first];
        list[first] = list[random];
        list[random] = temp;

        int pivotValue = list[first];
        int pivotPoint = first;
        for (int i = first + 1; i <= last; i++) {
            counter++;
            if (list[i] < pivotValue ) {
                pivotPoint = pivotPoint + 1;
                temp = list[pivotPoint];
                list[pivotPoint] = list[i];
                list[i] = temp;
            }
        }
        temp = list[first];
        list[first] = list[pivotPoint];
        list[pivotPoint] = temp;
        return pivotPoint;
    }

    public static void shuffleFY(int[] list, int N) {
        int j;
        for (int i = N-1; i > 0; i--) {
            j = rn.nextInt(i + 1);
            int temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }
    }
}