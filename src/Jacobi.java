public class Jacobi {
    private final double[][] a;
    private final double[] b;
    private final int nIter = 100;
    private final double tol = 1e-4;

    public Jacobi(double[][] a, double[] b) {
        this.a = a;
        this.b = b;
    }

    public double[] solve() {
        int n = b.length;
        double[] x = new double[n];
        double[] xNew = new double[n];

        for (int iter = 0; iter < nIter; iter++) {
            for (int i = 0; i < n; i++) {
                double sigma = 0.0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sigma += a[i][j] * x[j];
                    }
                }
                xNew[i] = (b[i] - sigma) / a[i][i];
            }


            if (converged(x, xNew)) {
                System.out.println("Getted for" + (iter + 1) + " iterations");
                return xNew;
            }


            System.arraycopy(xNew, 0, x, 0, n);
        }

        System.out.println("The maximum number of iterations without convergence has been achieved.");
        return xNew;
    }

    private boolean converged(double[] x, double[] xNew) {
        double maxDiff = 0.0;
        for (int i = 0; i < x.length; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(xNew[i] - x[i]));
        }
        return maxDiff < tol;
    }



    public void jacobiMethod() {
        System.out.println();
        System.out.println("Jacobi solution");
        double[] solution = solve();
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
        System.out.println("----------------------------------------------------");
    }
}
