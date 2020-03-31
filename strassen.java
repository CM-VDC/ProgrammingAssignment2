import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.Random; 


public class strassen {

	public static int[][] naive(int [][] matrix1, int [][] matrix2){

		int n = matrix1.length;
		int result[][]=new int[n][n];

        //multiplying matrices
        for(int i = 0; i < n; i++){

            for(int j = 0; j < n; j++){

								// find entries per row
                result[i][j]=0;

                for(int k = 0; k <n; k++)
                {
                    result[i][j] += (matrix1[i][k] * matrix2[k][j]);
                }

                // printing current matrix element
            }
						// print newline for next row
        }
        return result;

	}
	/*
	consider this implementation
	ArrayList<List<Object>> mat1 = new ArrayList();
	ArrayList<Object> listInner = new ArrayList();
	linstInner.add()
	*/
	public static int[][] strassen(int[][] mat1, int[][] mat2) {

		int n = mat1.length;
		int[][] product = new int[n][n];

		if (mat1.length == 1) {
			product[0][0] = mat1[0][0] * mat2[0][0];
		}

		else {
			int [][] a = new int[n/2][n/2];
			int [][] b = new int[n/2][n/2];
			int [][] c = new int[n/2][n/2];
			int [][] d = new int[n/2][n/2];
			int [][] e = new int[n/2][n/2];
			int [][] f = new int[n/2][n/2];
			int [][] g = new int[n/2][n/2];
			int [][] h = new int[n/2][n/2];
			// also consider just having empty lists and then adding to them
			// in the funciton rather than indexing through them

			// Splits mat1 and mat2 into 4 parts each
			for (int i = 0; i < (n/2); i++) {
				for (int j = 0; j < (n/2); j++){
					a[i][j] = mat1[i][j];
					b[i][j] = mat1[i][j + n/2];
					c[i][j] = mat1[i + n/2][j];
					d[i][j] = mat1[i + n/2][j + n/2];

					e[i][j] = mat2[i][j];
					f[i][j] = mat2[i][j + n/2];
					g[i][j] = mat2[i + n/2][j];
					h[i][j] = mat2[i + n/2][j + n/2];


				}
				/*a[i] = mat1[i].subList(0, n/2);
				b[i] = mat1[i].subList(n/2, n);
				e[i] = mat2[i].subList(0, n/2);
				f[i] = mat2[i].subList(n/2, n);
				c[i] = mat1[n/2 + i].subList(0, n/2);
				d[i] = mat1[n/2 + i].subList(n/2, n);
				g[i] = mat2[n/2 + i].subList(0, n/2);
				h[i] = mat2[n/2 + i].subList(n/2, n); */

			}
			int [][] p1 = strassen(a, subtract(f, h));
			int [][] p2 = strassen(add(a, b), h);
			int [][] p3 = strassen(add(c, d), e);
			int [][] p4 = strassen(d, subtract(g, e));
			int [][] p5 = strassen(add(a, d), add(e, h));
			int [][] p6 = strassen(subtract(b, d), add(g, h));
			int [][] p7 = strassen(subtract(a, c), add(e, f));

			// Resulting quadrants, first being the upper left and second the upper
			// right
			int [][] first = add(subtract(add(p5, p4), p2), p6);
			int [][] second =  add(p1, p2);
			int [][] third = add(p3, p4);
			int [][] fourth = subtract(subtract(add(p5, p1), p3), p7);

			// Recombines split comoponents
			for (int i = 0; i < (n/2); i++) {
				for(int j = 0; j < n/2; j++) {
					product[i][j] = first[i][j];
					product[i][j + n/2] = second[i][j];
					product[i + n/2][j] = third[i][j];
					product[i + n/2][j + n/2] = fourth[i][j];
				}
			}
		}
		/*System.out.print("Strassen result: \n");
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(product[i][j] + " ");
			}
			System.out.println();
		}*/
		return product;


	}

	// Matrix Addition Function
    public static int[][] add(int[][] mat1, int[][] mat2){
    	int dim = mat1.length;
    	int[][] sum = new int[dim][dim];

    	for (int i = 0; i < dim; i++) {
    		for (int j = 0; j < dim; j++) {
    			sum[i][j] = mat1[i][j] + mat2[i][j];
    		}
    	}
    	return sum;
    }

    public static int[][] subtract(int[][] mat1, int[][] mat2){
    	int dim = mat1.length;
    	int[][] diff = new int[dim][dim];

    	for (int i = 0; i < dim; i++) {
    		for (int j = 0; j < dim; j++) {
    			diff[i][j] = mat1[i][j] - mat2[i][j];
    		}
    	}
    	return diff;
    }

    public static int test1(int dim) {
    	int [][] mat1 = new int[dim][dim]; 
    	int [][] mat2 = new int[dim][dim]; 

    	for (int i = 0; i < dim; i++) {
    		for (int j = 0; j < dim; j++){
    			double rand1 = Math.random(); 
    			double rand2 = Math.random(); 
    			if (rand1 >= .5) {
    				mat1[i][j] = 1;
    			}
    			else {
    				mat1[i][j] = 0; 
    			}

    			if (rand2 >= .5) {
    				mat2[i][j] = 1;
    			}
    			else {
    				mat2[i][j] = 0; 
    			}
    		} 
    	}
    	/*System.out.println("Matrix 1: "); 
    	for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				System.out.print(mat1[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("Matrix 2: "); 
    	for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				System.out.print(mat2[i][j] + " ");
			}
			System.out.println();
		}
		*/
		System.out.println("dimension: " + dim);
		long start1 = System.nanoTime(); 
		int [][] product = strassen(mat1, mat2);
		long end1 = System.nanoTime(); 
		long elapsed1 = end1 - start1; 
		System.out.println("Strassens takes " + elapsed1 + " nanoseconds");

		long start2 = System.nanoTime(); 
		int[][] tester = naive(mat1, mat2);
		long end2 = System.nanoTime(); 
		long elapsed2 = end2 - start2; 
		System.out.println("Naive takes " + elapsed2 + " nanoseconds"); 

		if ((elapsed1 - elapsed2) <0){
			System.out.println("n: " + dim);
		}
		else {
			test1(dim*2); 
		}
		return dim; 
    }

	public static void main(String[] args){

		/*int [] l1 = {2, 1, 4, 7};
		int [] l2 = {4, 6, 7, 100};
		int [] l3 = {7529, 42, 1, 7};
		int [] l4 = {4, 7, 2, 9};

		int [] l5 = {4, 9 , 1, 1};
		int [] l6 = {10, 13, 5, 6};
		int [] l7 = {11, 300, 65, 0}; 
		int [] l8 = {1, 1, 69, 1}; 

		int [][] test1 = {l1, l2, l3, l4};
		int [][] test2 = {l5, l6, l7, l8};

		int[][] product;
		long start1 = System.nanoTime(); 
		product = strassen(test1, test2);
		long end1 = System.nanoTime(); 
		long elapsed1 = end1 - start1; 
		System.out.println("Strassens takes " + elapsed1 + " nanoseconds");

		long start2 = System.nanoTime(); 
		int[][] tester = naive(test1, test2);
		long end2 = System.nanoTime(); 
		long elapsed2 = end2 - start2; 
		System.out.println("Naive takes " + elapsed2 + " nanoseconds"); 

		System.out.print("Naive result: \n");
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				System.out.print(tester[i][j] + " ");
			}
			System.out.println();
		} */
		int dim = 2; 
		int result = test1(dim); 
		

	}
}

