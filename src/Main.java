public class Main {
    public static void main(String[] args) {
        double[][] matrix = {
                {3, -5, 47, 20},
                {11, 16, 17, 10},
                {56, 22, 11, -18},
                {17, 66, -12, 7}
        };
        double[] b = {18, 26, 34, 82};
        Cramer cramer = new Cramer(matrix,b);
        Gauss gauss = new Gauss(matrix,b);
        Jacobi jacobi = new Jacobi(matrix,b);
        GaussSeidel gaussSeidel = new GaussSeidel(matrix,b);

        cramer.cramerMethod();
        gauss.gaussMethod();
        jacobi.jacobiMethod();
        gaussSeidel.gaussSeidelMethod();
    }
}
