import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            double a = getValidDouble(scanner, "Введіть константу a:");

            int rows = getValidInt(scanner, "Введіть кількість рядків матриці B:");
            int cols = getValidInt(scanner, "Введіть кількість стовпців матриці B:");

            if (rows <= 0 || cols <= 0) {
                throw new IllegalArgumentException("Розміри матриці повинні бути більшими за 0.");
            }

            double[][] B = generateRandomMatrix(rows, cols, -10, 10);

            System.out.println("\nМатриця B (заповнена випадковими числами):");
            printMatrix(B);

            double[][] C = multiplyMatrixByConstant(B, a);

            System.out.println("\nМатриця C (після множення на a):");
            printMatrix(C);

            double sumOfMinElements = calculateSumOfMinElements(C);

            System.out.printf("\nСума найменших елементів кожного рядка матриці C: %.2f\n", sumOfMinElements);

        } catch (Exception e) {
            System.err.println("Сталася помилка: " + e.getMessage());
        }
    }

    public static double getValidDouble(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                return scanner.nextDouble();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Некоректний ввід. Введіть число.");
                scanner.next(); // Очищення введення
            }
        }
    }

    public static int getValidInt(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Некоректний ввід. Введіть ціле число.");
                scanner.next();
            }
        }
    }

    public static double[][] generateRandomMatrix(int rows, int cols, int min, int max) {
        Random random = new Random();
        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = min + (max - min) * random.nextDouble();
            }
        }

        return matrix;
    }

    public static double[][] multiplyMatrixByConstant(double[][] matrix, double constant) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] * constant;
            }
        }
        return result;
    }

    public static double calculateSumOfMinElements(double[][] matrix) {
        double sum = 0;

        for (double[] row : matrix) {
            double min = row[0];
            for (double value : row) {
                if (value < min) {
                    min = value;
                }
            }
            sum += min;
        }

        return sum;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf("%.2f ", value);
            }
            System.out.println();
        }
    }
}
