package Week5;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
    static Random rn = new Random();
    static long count = 0;
    static int N;
    static long[] comparisonlist = new long[1];
    public static void main(String[] args) {

        System.out.println("Initial sorting test");
        int [] testlist = {6, 2, 4, 7, 1, 3, 8, 5};
        sortBubble(testlist, testlist.length);
        System.out.println(Arrays.toString(testlist));

        comparisonlist = new long[17];
        System.out.println("\nMonte Carlo Fahsion Test");

        for (int e = 0; e <= 16; e++) {
            count = 0;
            N = (int)Math.pow(2, e);
            int list[] = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = i;
            }
            shuffleFY(list, N);
            sortBubble(list, N);
            comparisonlist[e] = count;

        }

        System.out.println(Arrays.toString(comparisonlist));

        count = 0;
        comparisonlist = new long[17];
        System.out.println("\nPre-sorted ascending");

        for (int e = 0; e <= 16; e++) {
            count = 0;
            N = (int)Math.pow(2, e);
            int list[] = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = i;
            }
            sortBubble(list, N);
            comparisonlist[e] = count;

        }

        System.out.println(Arrays.toString(comparisonlist));

        count = 0;
        comparisonlist = new long[17];
        System.out.println("\nPre-sorted descending");

        for (int e = 0; e <= 16; e++) {
            count = 0;
            N = (int)Math.pow(2, e);
            int list[] = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = N - i;
            }
            sortBubble(list, N);
            comparisonlist[e] = count;

        }

        System.out.println(Arrays.toString(comparisonlist));

        System.out.println("\nModified Bubble Sort:\n");

        System.out.println("Initial sorting test");
        sortBubbleM(testlist, testlist.length);
        System.out.println(Arrays.toString(testlist));

        comparisonlist = new long[17];
        System.out.println("\nMonte Carlo Fahsion Test");

        for (int e = 0; e <= 16; e++) {
            count = 0;
            N = (int)Math.pow(2, e);
            int list[] = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = i;
            }
            shuffleFY(list, N);
            sortBubbleM(list, N);
            comparisonlist[e] = count;

        }

        System.out.println(Arrays.toString(comparisonlist));

        count = 0;
        comparisonlist = new long[17];
        System.out.println("\nPre-sorted ascending");

        for (int e = 0; e <= 16; e++) {
            count = 0;
            N = (int)Math.pow(2, e);
            int list[] = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = i;
            }
            sortBubbleM(list, N);
            comparisonlist[e] = count;

        }

        System.out.println(Arrays.toString(comparisonlist));

        count = 0;
        comparisonlist = new long[17];
        System.out.println("\nPre-sorted descending");

        for (int e = 0; e <= 16; e++) {
            count = 0;
            N = (int)Math.pow(2, e);
            int list[] = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = N - i;
            }
            sortBubbleM(list, N);
            comparisonlist[e] = count;

        }

        System.out.println(Arrays.toString(comparisonlist));
    }

    public static void sortBubble(int[] list, int N) {
        int numberOfPairs = N;
        boolean swappedElements = true;
        while(swappedElements) {
            numberOfPairs = numberOfPairs - 1;
            swappedElements = false;
            for (int i = 0; i < numberOfPairs; i++) {
                count++;
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                    swappedElements = true;
                }
            }
        }
    }

    public static void sortBubbleM(int[] list, int N) {
        int numberOfPairs = N;
        boolean swappedElements = true;
        while(swappedElements) {
            numberOfPairs = numberOfPairs - 1;
            swappedElements = false;
            for (int i = 0; i < numberOfPairs; i++) {
                count++;
                if (i + 1 % 2 == 0) { // == instead of !=; Even case.
                    if (list[i] < list[i + 1]) {
                        int temp = list[i];
                        list[i] = list[i + 1];
                        list[i + 1] = temp;
                        swappedElements = true;
                    }
                }
                else { // Odd case.
                    if (list[i] > list[i+1]) {
                        int temp = list[i];
                        list[i] = list[i+1];
                        list[i+1] = temp;
                        swappedElements = true;
                    }
                }
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