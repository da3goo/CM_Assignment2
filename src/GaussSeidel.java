public class GaussSeidel {
    private double[][] matrix;
    private double[] B;
    private double tolerance = 1e-4;
    private int maxIterations = 100;

    public GaussSeidel(double[][] matrix, double[] B) {
        this.matrix = matrix;
        this.B = B;

    }

    public double[] solve() {
        int n = matrix.length;
        double[] solution = new double[n];
        double[] previousSolution = new double[n];
        boolean converged = false;

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            converged = true;

            for (int i = 0; i < n; i++) {
                double sum = B[i];

                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= matrix[i][j] * solution[j];
                    }
                }

                solution[i] = sum / matrix[i][i];

                if (Math.abs(solution[i] - previousSolution[i]) > tolerance) {
                    converged = false;
                }

                previousSolution[i] = solution[i];
            }

            // Проверка сходимости после завершения всех вычислений
            if (converged) {
                System.out.println("Converged in " + (iteration + 1) + " iterations.");
                break;
            }
        }

        if (!converged) {
            System.out.println("Did not converge within the maximum number of iterations.");
        }

        return solution;
    }

    public void gaussSeidelMethod() {
        double[] solution = solve();
        System.out.println("Gauss-Seidel solution:");

        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
        System.out.println("----------------------------------------------------");
    }
}
