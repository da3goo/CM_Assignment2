public class Cramer {
    private double[][] matrix;
    private final double[] B;

    public Cramer(double[][] matrix, double[] B) {
        this.matrix = matrix;
        this.B = B;
    }

    public double determinant(double[][] matrix) {
        int n = matrix.length;
        double det = 0;
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]; // Определитель 2x2
        }
        for (int i = 0; i < n; i++) {
            double[][] subMatrix = new double[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                int subCol = 0;
                for (int k = 0; k < n; k++) {
                    if (k == i) continue;
                    subMatrix[j - 1][subCol] = matrix[j][k];
                    subCol++;
                }
            }
            det += Math.pow(-1, i) * matrix[0][i] * determinant(subMatrix);
        }
        return det;
    }


    public double[] solve() {
        double detA = determinant(matrix); // Определитель матрицы коэффициентов

        // Проверка на существование решения (если detA == 0, то решение либо не существует, либо оно не единственное)
        if (detA == 0) {
            throw new ArithmeticException("Определитель матрицы равен нулю. Система не имеет единственного решения.");
        }

        int n = matrix.length;
        double[] solutions = new double[n];


        for (int i = 0; i < n; i++) {
            double[][] matrixCopy = new double[n][n];
            for (int j = 0; j < n; j++) {
                System.arraycopy(matrix[j], 0, matrixCopy[j], 0, n);
            }

            for (int j = 0; j < n; j++) {
                matrixCopy[j][i] = B[j];
            }


            solutions[i] = determinant(matrixCopy) / detA;
        }
        return solutions;
    }
    public void cramerMethod(){
        System.out.println();
        System.out.println();
        double[] solution = solve();
        System.out.println("Cramer solution:");
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
        System.out.println("----------------------------------------------------");
    }
}
