public class Gauss {
    private double[][] matrix;
    private double[] B;
    public Gauss(double[][] matrix, double[] B) {
        this.matrix = matrix;
        this.B = B;
    }


    public double[] solve() {
        int n = matrix.length;
        double[][] augmentedMatrix = new double[n][n + 1];

        augmentMatrix(augmentedMatrix);


        forwardElimination(augmentedMatrix);


        return backSubstitution(augmentedMatrix);
    }

    private void augmentMatrix(double[][] augmentedMatrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
            }
            augmentedMatrix[i][matrix[i].length] = B[i];
        }
    }


    private void forwardElimination(double[][] augmentedMatrix) {
        int n = augmentedMatrix.length;

        for (int i = 0; i < n; i++) {

            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(augmentedMatrix[k][i]) > Math.abs(augmentedMatrix[maxRow][i])) {
                    maxRow = k;
                }
            }

            swapRows(augmentedMatrix, i, maxRow);

            for (int j = i + 1; j < n; j++) {
                double factor = augmentedMatrix[j][i] / augmentedMatrix[i][i];
                for (int k = i; k <= n; k++) {
                    augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                }
            }
        }
    }


    private void swapRows(double[][] augmentedMatrix, int row1, int row2) {
        double[] temp = augmentedMatrix[row1];
        augmentedMatrix[row1] = augmentedMatrix[row2];
        augmentedMatrix[row2] = temp;
    }


    private double[] backSubstitution(double[][] augmentedMatrix) {
        int n = augmentedMatrix.length;
        double[] solution = new double[n];

        for (int i = n - 1; i >= 0; i--) {
            solution[i] = augmentedMatrix[i][n] / augmentedMatrix[i][i];
            for (int j = i - 1; j >= 0; j--) {
                augmentedMatrix[j][n] -= augmentedMatrix[j][i] * solution[i];
            }
        }

        return solution;
    }


    private void printSolution(double[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
    }
    public void gaussMethod(){
        double[] solution = solve();
        System.out.println("Gauss solution:");

        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
        System.out.println("----------------------------------------------------");

    }
}
