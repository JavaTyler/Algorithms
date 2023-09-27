package Week7;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    static long counter = 0;
    static Random rn = new Random();
    static int N;
    static long[] comparisonlist = new long[1];

    public static void main(String[] args) {
        System.out.println("\nSample Test");
        int[] test = {6, 2, 4, 7, 1, 3, 8, 5};
        sortMerge(test, 0, test.length - 1);
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
            sortMerge(list, 0, list.length - 1);
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
            sortMerge(list, 0, list.length - 1);
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
            sortMerge(list, 0, list.length - 1);
            comparisonlist[e] = counter;

        }

        System.out.println(Arrays.toString(comparisonlist));
    }

    public static void sortMerge(int[] list, int first, int last) {
        if (first < last) {
            int middle = (first + last) / 2;
            sortMerge(list, first, middle);
            sortMerge(list, middle + 1, last);
            mergeList(list, first, middle, middle + 1, last);
        }
    }

    private static void mergeList(int[] list, int start1, int end1, int start2, int end2) {
        int finalStart = start1;
        int finalEnd = end2;
        int indexC = 0;
        int[] result = new int[list.length];
        while (start1 <= end1 && start2 <= end2) {
            counter++;
            if (list[start1] < list[start2]) {
                result[indexC] = list[start1];
                start1 = start1 + 1;
            } else {
                result[indexC] = list[start2];
                start2 = start2 + 1;
            }
            indexC++;
        }
        //moves part of list that is left over

        if (start1 <= end1) {
            for (int i = start1; i <= end1; i++) {
                result[indexC] = list[i];
                indexC++;
            }
        }
        else {
            for (int i = start2; i <= end2; i++) {
                result[indexC] = list[i];
                indexC++;
            }
        }
        //put result back into list
        indexC = 0;
        for (int i = finalStart; i <= finalEnd; i++) {
            {
                list[i] = result[indexC];
                indexC++;
            }
        }

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
