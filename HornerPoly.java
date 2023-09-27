package Week9;

public class HornerPoly {
    public static double HornersMethod(int[] polynomial, int n, double x) {
        double result = polynomial[0];

        // Horner's method
        for (int i = 1; i < n; i++) {
            result = (result * x) + polynomial[i];
        }
        return result;
    }

    public static void main (String[] args) {
        // p(x) = x^7 + 4x^6 + 8x^4 + 6x^3 + 9x^2 + 2x + 3
        // p(x) = (x^4 + 5)(x^3 + 4x^2 + 8) + (x^3 - 11x^2 + 2x - 37)

        System.out.println("Test Cases: ");

        int[] polynomial = {1, 4, 0, 8, 6, 9, 2, 3};
        int x = 5;
        int n = polynomial.length;

        System.out.println("Value of poly: " + HornersMethod(polynomial, n, x));


        int[] p1 = {1, 0, 0, 0, 5}, p2 = {1, 4, 0, 8}, p3 = {1, -11, 2, -37};
        int n1 = p1.length, n2 = p2.length, n3 = p3.length;
        double result = (HornersMethod(p1, n1, x) * HornersMethod(p2, n2, x)) + HornersMethod(p3, n3, x);

        System.out.println("Value of poly: " + result);

        System.out.println("Polynomial w/o Processing");
        for (double i = -20; i <= 20; i+=0.2) {
            System.out.println(Math.round(i * 10)/10.0 + ", " + HornersMethod(polynomial, n, i));
        }

        System.out.println("Processed Polynomial");
        for (double i = -20; i <= 20; i+=0.2) {
            System.out.println(Math.round(i * 10)/10.0 + ", " + (HornersMethod(p1, n1, i) * HornersMethod(p2, n2, i)) + HornersMethod(p3, n3, i));
        }

    }

}
