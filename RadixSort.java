package Week6;

public class RadixSort {

    public static void main(String[] args) {
        String[] list = {"zero", "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
                "sixteen", "seventeen", "eighteen", "nineteen", "twenty"};
        String[] result = sortRadix(list);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static String[] sortRadix(String[] list) { //int N as parameter?
        String buckets[][] = new String[26][list.length];
        int bucketcounts[];
        int max = 0;
        for (int i = 0; i < list.length; i++) {
            if(list[i].length() > max) {
                max = list[i].length();
            }
        }
        int keysize = max;
        for(int i = 0; i < list.length; i++) {
            if(list[i].length() < keysize) {
                int spaces = keysize - list[i].length();
                for(int f = 0; f < spaces; f++) {
                    list[i] = list[i] + " ";
                }
            }
        }
        //int shift = 1;
        for (int i = 0; i < keysize; i++) {
            bucketcounts = new int[26];
            for (int j = 0; j < list.length; j++) {
                int bn;
                if (list[j].length() > i) {
                    int ascii = (int)(list[j].charAt(list[j].length() - (i + 1)));
                    //Result of 32 indicates a space.
                    if (ascii == 32) {
                        bn = 0;
                    }
                    else {
                        bn = ascii - 97; // 0 - 25
                    }
                    buckets[bn][bucketcounts[bn]++] = list[j];
                }
            }
            list = combineBuckets(buckets, bucketcounts);
            //shift*=10;
        }
        return list;
    }

    private static String[] combineBuckets(String buckets[][], int bucketcounts[]) {
        int N = 0;
        for (int i = 0; i < bucketcounts.length; ++i) {
            N+=bucketcounts[i];
        }
        String combinedbuckets[] = new String[N];
        int j = 0;
        for (int bn = 0; bn < buckets.length; ++bn) { // Was just buckets.length
            for (int b = 0; b < bucketcounts[bn]; ++b) {
                combinedbuckets[j++] = buckets[bn][b];
            }
        }
        return combinedbuckets;
    }
}