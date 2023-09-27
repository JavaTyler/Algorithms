package Week11;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PolyFit {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\minot\\Downloads\\NoisyPolynomialData.csv";
        for(int i = 1; i <= 7; i++) {
            mLinearFitting(filePath, i);
        }
    }

    public static double[] mLinearFitting(String fileLocation, int degree) {
        try {
            Scanner file = new Scanner(new File(fileLocation));

            double[][] Q = new double[degree+1][degree+1];
            double[][] U = new double[degree+1][1];
            while(file.hasNext()) {
                String line = file.nextLine();
                String[] coordinates = line.split(",");
                double x = Double.parseDouble(coordinates[0]);
                double y = Double.parseDouble(coordinates[1]);
                for (int i = 0; i <= degree; i++) {
                    for (int j = 0; j <= degree; j++) {
                        Q[i][j] += Math.pow(x, i + j);
                    }
                    U[i][0] += Math.pow(x, i) * y;
                }
            }
            double[][] QI = invert(Q);
            double[][] coef = matmult(QI, U);

            file = new Scanner(new File(fileLocation));
            System.out.print("Degree: " + degree + "\n");
            while (file.hasNext()) {
                String line = file.nextLine();
                String[] coordinates = line.split(",");
                double x = Double.parseDouble(coordinates[0]);
                double y = Double.parseDouble(coordinates[1]);
                double modelY = coef[degree][0];
                for (int i = degree - 1; i >= 0; i--) {
                    modelY = modelY * x + coef[i][0];
                }
                System.out.printf("%f, %f, %f\n", x, y, modelY);
            }
            double[] getCoeff = new double[degree + 1];
            for (int i = 0; i <= degree; i++) {
                getCoeff[degree - i] = coef[i][0];
            }
            return getCoeff;
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
            throw new IllegalArgumentException("cannot find file");
        }
    }

    public static double[][] invert(double[][] A) throws IllegalArgumentException {
        if (A.length != A[0].length) {
            throw new IllegalArgumentException("Matrix not invertible");
        }

        int rows = A.length;
        int cols = A[0].length;

        double[][] I = new double[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (i == j) I[i][j] = 1.0;
            }
        }

        for (int i = 0; i < rows; ++i) {
            int pivotloc = i;
            double pivot = A[pivotloc][i];
            try {
                while (Math.abs(pivot) < 0.000000001) {
                    ++pivotloc;
                    pivot = A[pivotloc][i];
                }
                swaprows(A, i, pivotloc);
                swaprows(I, i, pivotloc);
                for (int j = 0; j < cols; ++j) {
                    A[i][j] /= pivot;
                    I[i][j] /= pivot;
                }

                for (int ii = 0; ii< rows; ++ii) {
                    if (i == ii) continue;
                    double multplier = A[ii][i];
                    for (int j = 0; j < cols; ++j) {
                        A[ii][j] -= multplier * A[i][j];
                        I[ii][j] -= multplier * I[i][j];
                    }
                }
            }
            catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Singular Matrix");
            }
        }

        return I;
    }

    private static void swaprows (double[][] A, int r0, int r1) {
        int cols = A[0].length;

        for (int j = 0; j < cols; ++j) {
            double temp = A[r0][j];
            A[r0][j] = A[r1][j];
            A[r1][j] = temp;

        }
    }

    public static void printMatrix(double[][] A) {
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[i].length; ++j) {
                System.out.println(A[i][j]);
            }
            System.out.println();
        }
    }

    public static double [][] matmult (double[][] A, double [][] B) {

        double result[][] = new double[A.length][B[0].length];
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < B[0].length; ++j) {
                for (int k = 0; k < A.length; ++k) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

}