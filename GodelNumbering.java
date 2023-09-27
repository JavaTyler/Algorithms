package Week1;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GodelNumbering {

    public static boolean isPrime(long p) {
        if (p <= 1) {
            return false;
        }

        if (p <= 3) {
            return true;
        }

        for (long i = 2; i < p; i++) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long nextPrime(long p) {

        long i = p;

        if (i % 2 == 0) {
            i++;
        } else if(i==1) {
            i = 2;
        }else {
            i += 2;
        }

        while (true) {
            if (isPrime(i)) {
                break;
            } else {
                i += 2;
            }
        }
        return i;
    }
        /*while (true) {
            if (isPrime(i)
            ) {
                if (i % 2 == 0) {
                    i++;
                } else {
                    i += 2;
                }
                break;
            }
        }
        return i;

        }
        */

    public static ArrayList<Long> primeFactor(long p) {

        ArrayList<Long> sequence = new ArrayList<Long>(1);
        for (int i = 2; i <= p; i++) {
            if (isPrime(i)) {
                long prime = p;
                while (prime % i == 0) {
                    sequence.add(Long.valueOf(i));
                    prime /= i;
                }
            }
        }
        return sequence;
    }

    public static ArrayList<Long> arrListToGodelNum(ArrayList<Long> e) {
        ArrayList<Long> godelArr = new ArrayList<Long>(1);
        int counter = 0;
        for (long i = 2; i <= Collections.max(e); i = nextPrime(i)) {
            int j = 0;
            while(j < e.size()) {
                if(e.get(j).equals(i)) {
                    counter++;
                }
                j++;
            }
            godelArr.add(Long.valueOf(counter));
            counter = 0;
            /*for(int j = 0; j < e.size(); j++) {
                if(e.get(j).equals(i)) {
                    counter++;
                }
                System.out.println("hi");
            }*/
        }
        return godelArr;
    }

    public static long godelNumToValue(ArrayList<Long> godelArr) {

        long e = 1;

        long primeCap = 0;

        for (long j = 0; j < godelArr.size() - 1; j++) {
            if (j == 0) {
                primeCap = 2;
            }
            primeCap = nextPrime(primeCap);
        }

        int arrIndex = 0;
        for (long i = 2; i <= primeCap; i = nextPrime(i)) {
            e *= (long)Math.pow(i, godelArr.get(arrIndex));
            arrIndex++;
        }
        return e;
    }

        /*long primeEnder = 0;
        long e = 0;
        for (long i = 0; i < godelArr.size(); i++) {
            primeEnder = nextPrime(primeEnder); // First prime is 2, next is 3, 5, 7, n. We want = and not something like += so we know what the actual ending prime value of the sequence will be.
        }
        for (long j = 0; j < godelArr.size(); j++) {
            for (long i = 2; i <= primeEnder; i = nextPrime(i)) { // less than or equals to include the final prime value.
                e += Math.pow(i, (double)godelArr.get((int) j));
            }
        }
        return e;
    }*/

    public static void main(String[] args) {
        ArrayList<Long> sequence = primeFactor(1540);
        System.out.print("Original Sequence = ");
        for (int i = 0; i < sequence.size(); i++) {
            System.out.print(sequence.get(i) + ", ");
        }
        System.out.println();

        sequence = arrListToGodelNum(sequence);
        System.out.print("Godel Sequence = ");
        for (int i = 0; i < sequence.size(); i++) {
            System.out.print(sequence.get(i) + ", ");
        }
        System.out.println();

        long e = godelNumToValue(sequence);
        System.out.print(" Value Represented: " + e);
        System.out.println();

        System.out.println("Dr. Reinhart's Sample: ");
        ArrayList<Long> reinhart = new ArrayList<>(1);
        for (long i = 7; i >= 1; i--) {
            reinhart.add(i);
        }

        long reinhartNum = godelNumToValue(reinhart);
        System.out.print(" Value Represented: " + reinhartNum);
        System.out.println();

        ArrayList<Long> reinhart2 = primeFactor(reinhartNum);
        System.out.print(" Prime Factors: " + reinhartNum);
        for (int i = 0; i < reinhart2.size(); i++) {
            System.out.print(reinhart2.get(i) + ", ");
        }
        System.out.println();

        reinhart2 = arrListToGodelNum(reinhart2);
        System.out.print(" Godel Sequence: " + reinhartNum);
        for (int i = 0; i < reinhart2.size(); i++) {
            System.out.print(reinhart2.get(i) + ", ");
        }
        System.out.println();
    }
}
