package Week5;
import java.util.Random;
import java.util.Arrays;

public class InsertionSort {
    static Random rn = new Random();
    static long count = 0;
    static int N;
    static long[] comparisonlist = new long[1];
    public static void main(String[] args) {

        System.out.println("Initial sorting test");
        int [] testlist = {6, 2, 4, 7, 1, 3, 8, 5};
        sortInsertion(testlist, testlist.length);
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
            sortInsertion(list, N);
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
            sortInsertion(list, N);
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
            sortInsertion(list, N);
            comparisonlist[e] = count;

        }

        System.out.println(Arrays.toString(comparisonlist));
    }

    public static void sortInsertion(int[] list, int N) {

        for (int i = 1; i < N; i++) {
            int newElement = list[i];
            int location = i - 1;
            count++;
            while(location >= 0 && list[location] > newElement) {
                list[location + 1] = list[location];
                location = location - 1;
                count++;
            }
            list[location + 1] = newElement;
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