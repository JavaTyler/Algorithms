package Week3;
import java.sql.SQLOutput;
import java.util.Random;



public class dice {

    public static void main(String[] args) {
        prob2();
        prob3();
        prob4();
    }

    static Random rn = new Random();
    static int n = 1000000; // base for loop iterative cap
    public static void prob2() {
        System.out.println("Problem #2");
        System.out.println();
        int[] rollcount = new int[5];
        int dice = rn.nextInt(5) + 1;
        double avgval = 0;
        for (int i = 0; i < n; i++) {
            dice = rn.nextInt(5) + 1;
            rollcount[dice - 1] += 1;
        }
        for (int i = 0; i < rollcount.length; i++) {
            System.out.println("Number " + (i + 1) + " rolled: " + rollcount[i] + " times.");
        }
        for (int i = 0; i < rollcount.length; i++) {
            avgval+=rollcount[i] * (i + 1);
        }
        System.out.println("Average value: " + avgval/n);
        System.out.println("If the average value is very close to 3, there is an equivalent distribution of dice values (20% to each) as n (roll count) approaches infinity.");
        System.out.println("\nProblem #2 part two\n");

        int[] rolls = new int[9];
        for (int i = 0; i < n; i++) {
            rolls[rn.nextInt(5)  + rn.nextInt(5)] += 1;
        }
        System.out.println("Probability of two-die rolls: ");
        double sum = 0;
        for (int i = 0; i < rolls.length; i++) {
            System.out.println((i + 2) + ": %" + (double)rolls[i]/n * 100);
            sum+= (double)rolls[i]/n;
        }
        System.out.println("(Should be extremely close to 1) Probability of any roll: " + sum);
    }

    public static void prob3() {
        System.out.println("\nProblem #3");
        System.out.println();
        int[] rollcount = new int[8];
        int[] dice = {1, 2, 3, 3, 4, 5, 5, 5};
        double avgval = 0;
        for (int i = 0; i < n; i++) {
            rollcount[rn.nextInt(8)] += 1; // We can say "hey index 2-3 represents 3, and 5-7 represent 5 when doing our calculations since its a uniform distribution of values.
        }
        System.out.println("Probabilities: ");
        double[] rollprob = {(double) rollcount[0] / n, (double) rollcount[1] / n, ((double) rollcount[2] + rollcount[3]) / n, (double) rollcount[4] / n, ((double) rollcount[5] + rollcount[6] + rollcount[7]) / n};
        for (int i = 0; i < rollprob.length; i++) {
            System.out.println((i + 1) + ": %" + (rollprob[i] * 100));
        }
        for (int i = 0; i < rollprob.length; i++) {
            avgval += rollprob[i];
        }
        System.out.println("(Should be one) Total proability: " + avgval);
        System.out.println("\nProblem #3 Part two");

        System.out.println("Possible combinations: ");


        int[] rollcount2 = new int[9];
        for (int i = 0; i < n/9; i++) { // n/9 to prevent calculation error,.
            for (int j = 0; j < rollcount2.length; j++) {
                rollcount2[dice[rn.nextInt(8)] + dice[rn.nextInt(8)] - 2] += 1;
            }
        }

        System.out.println("");
        double[] rollprob2 = new double[9];
        for (int i = 0; i < rollprob2.length; i++ ) {
            rollprob2[i] = (double)rollcount2[i]/n;
            System.out.println((i + 2) + ": %" + (rollprob2[i] * 100));
        }
        avgval = 0;
        for (int i = 0; i < rollprob2.length; i++) {
            avgval += rollprob2[i];
        }
        System.out.println("(Should be close to one) Total probability: " + avgval);
     }

     public static void prob4() {
        int[] d1 = {1,2,3,9,10,11};
        int[] d2 = {0,1,7,8,8,9};
        int[] d3 = {5,5,6,6,7,7};
        int[] d4 = {3,4,4,5,11,12};

         System.out.println("\nProblem #4\n");

        System.out.println("d1 against d2, d3, and d4: " + compare(d1 ,d2) + " " + compare(d1, d3) + " " + compare(d1, d4));
         System.out.println("d2 against d1, d3, and d4: " + compare(d2 ,d1) + " " + compare(d2, d3) + " " + compare(d2, d4));
         System.out.println("d3 against d1, d2, and d4: " + compare(d3 ,d1) + " " + compare(d3, d2) + " " + compare(d3, d4));
         System.out.println("d4 against d1, d2, and d3: " + compare(d4 ,d1) + " " + compare(d4, d2) + " " + compare(d4, d3));


     }

     public static double compare(int[] first,  int[] second) { //first goes against second
        double prob = 0;
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < second.length; j++) {
                if(first[i] > second[j]) {
                    prob++;
                }
            }
        }
        return Math.round(prob/(first.length * second.length) * 100.0) / 100.0;
     }
}
