package Week4;

public class BinaryFibonacciSearch {

    public static int count = 0;

    public static void main(String [] args) {
        /*int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
        int count = (searchBin(arr, 2));
        System.out.println("Count comparisons and average " + count + ", " + (double)count/N);*/

        double[] avgcount = new double[21];
        for (int i = 0; i <= 20; i++) {
            int N = (int)Math.pow(2, i);
            int[] arr = new int[N];
                for (int j = 0; j < N; j++) {
                    arr[j] = j;
                }
                for (int k = 0; k < N; k++) {
                    count = 0;
                    avgcount[i] += (double)searchBin(arr, k, N) / N;
                }
        }
        for (int i = 0; i <= 20; i++) {
            System.out.println("N = " + i + " avg comparison: " + avgcount[i]);
        }

        double[] avgcount2 = new double[21];
        for (int i = 0; i <= 20; i++) {
            int N = (int)Math.pow(2, i);
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = j;
            }
            for (int k = 0; k < N; k++) {
                count = 0;
                avgcount2[i] += (double)fibonacciSearch(arr, k) / N;
            }
        }
        for (int i = 0; i <= 20; i++) {
            System.out.println("N = " + i + " avg comparison: " + avgcount2[i]);
        }

    }

    // This binary search does not return the index where the value is, it is just for figuring out comparison count to find the position of the index where the target value is.
    public static int searchBin(int[] arr, int target, int N) {
        int start = 0;
        int end = N - 1;
        int middle;

        while(start <= end) {
            middle = (start + end) / 2;
            count++;
            switch(Integer.compare(arr[middle], target)) {
                case -1:
                    start = middle +1;
                    break;
                case 0:
                    return count;
                case 1:
                    end = middle -1;
            }
        }
        return -1;

        /*while (start <= end) {
            middle = (start + end) / 2;

            count++;
            if(arr[middle] == target) {
                return count;
            }
            count++;
            if (target < arr[middle]) {
                end = middle - 1;
            }
            else {
                start = middle + 1;
            }*/
    }

    public static int fibonacciSearch(int[] list, int target) {
        int N = list.length;

        // -- degenerate case
        count++;
        if (N == 1 && list[N-1] == target)
            return count;

        // --Initialize fibonacci numbers
        int m2 = 0;
        int m1 = 1;
        int m = 1;

        // -- find the smallest fibonacci number greater than or
        //    equal to the list size (along with the
        //    two previous fibonacci numbers)
        while (m < N) {
            m2 = m1;
            m1 = m;
            m = m2 + m1;
        }

        // -- keep track of the discarded elements of the front of the list
        //    initially they are all kept
        int start = -1;

        // -- search for the target value within the interval
        //    [m2..N]
        while (m > 1) {
            // -- is m2 within the list bounds?
            int index = (start + m2 < N - 1) ? start + m2 : N - 1;

            // -- if the target is greater than the value at m2 + offset
            //    drop the lower portion of the array (the target is not there)
            ++count;
            if (target > list[index]) {
                m = m1;
                m1 = m2;
                m2 = m - m1;
                start = index;
            }
            // -- else if the target is less than the value at m2 + offset
            //    drop the upper portion of the array (the target is not there)
            else {
                count++;
                if (target < list[index]) {
                    m = m2;
                    m1 = m1 - m2;
                    m2 = m - m1;
                }
                else {
                    return count;
                }
            }
        }

        // -- target was not in the list
        return -1;
    }

}
