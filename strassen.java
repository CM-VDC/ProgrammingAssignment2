
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.Random; 
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Strassen {

	public int[][] naive(int [][] matrix1, int [][] matrix2){

		int n = matrix1.length;
		int result[][]=new int[n][n];

        // Multiplying matrices
        for(int i = 0; i < n; i++){

            for(int j = 0; j < n; j++){

				// Find entries per row
                result[i][j]=0;

                for(int k = 0; k <n; k++)
                {
                    result[i][j] += (matrix1[i][k] * matrix2[k][j]);
                }
            }
        }
        return result;

	}
	public int[][] strassen(int[][] mat1, int[][] mat2, int k) {

		int n = mat1.length;
		int[][] product = new int[n][n];
		
		if (k == n) {
			product = naive(mat1, mat2); 
		}
		else {

			// 1 by 1 matrix multiplication 
			if (mat1.length == 1) {
				product[0][0] = mat1[0][0] * mat2[0][0];
			}

			else {
				// Divide each matrix into 4 parts  
				int [][] a = new int[n/2][n/2];
				int [][] b = new int[n/2][n/2];
				int [][] c = new int[n/2][n/2];
				int [][] d = new int[n/2][n/2];
				int [][] e = new int[n/2][n/2];
				int [][] f = new int[n/2][n/2];
				int [][] g = new int[n/2][n/2];
				int [][] h = new int[n/2][n/2];
				
				// Populates divided matrices 
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
				}

				// 7 multiplications required for strassens 
				int [][] p1 = strassen(a, subtract(f, h), k);
				int [][] p2 = strassen(add(a, b), h, k);
				int [][] p3 = strassen(add(c, d), e, k);
				int [][] p4 = strassen(d, subtract(g, e), k);
				int [][] p5 = strassen(add(a, d), add(e, h), k);
				int [][] p6 = strassen(subtract(b, d), add(g, h), k);
				int [][] p7 = strassen(subtract(a, c), add(e, f), k);
				
				// Multiplies split components 
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
		}
		
		// If needed: print out matrix
		/*System.out.print("Strassen result: \n");
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(product[i][j] + " ");
			}
			System.out.println();
		} */
		return product;
		

	}

	// Matrix Addition Function
    public int[][] add(int[][] mat1, int[][] mat2){
    	int dim = mat1.length;
    	int[][] sum = new int[dim][dim];

    	for (int i = 0; i < dim; i++) {
    		for (int j = 0; j < dim; j++) {
    			sum[i][j] = mat1[i][j] + mat2[i][j];
    		}
    	}
    	return sum;
    }

    // Matrix Subtraction Function
    public int[][] subtract(int[][] mat1, int[][] mat2){
    	int dim = mat1.length;
    	int[][] diff = new int[dim][dim];

    	for (int i = 0; i < dim; i++) {
    		for (int j = 0; j < dim; j++) {
    			diff[i][j] = mat1[i][j] - mat2[i][j];
    		}
    	}
    	return diff;
    }

    // Function used to determine matrix breaking point
    public int test1(int[][] mat1, int [][] mat2, int dim, int k, long time, int breakpt) {
    	
    	// Check if tested dimension exceeds dimension of matrix
    	if (k > dim) {
    		// Default "Breaking Point" is 2 
    		if (breakpt == 2) {
    			//System.out.println("n0 not found. ");
    		}
    		else {
    			//System.out.println("Optimal Breakpoint: " + breakpt);
    		}
    		
    		return breakpt;  
    	}
    	else {
    		
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

			// Start timer 
			long start1 = System.nanoTime(); 
			// Run strassen
			int [][] product = strassen(mat1, mat2, k);
			// End timer & calculate elapsed time of strassen
			long end1 = System.nanoTime(); 
			long elapsed1 = end1 - start1; 
			//System.out.println("Strassens takes " + elapsed1 + " nanoseconds");

			// Checks if new crossover point is more optimal 		
			if (elapsed1 < time) {
				//System.out.println("n0: " + k);
				//System.out.println("time spent: " + elapsed1); 
				test1(mat1, mat2, dim, k*2, elapsed1, k);
			}
			else
				test1(mat1, mat2, dim, k*2, time, breakpt);
				
			return breakpt; 
	    }
    	
    }

    public int[][] generateRandomMat(int n, double p){

		// Create empty matrix of size n
		int dim = n;
		int [][] mat = new int[dim][dim];
		int edges = 0; 
		// Populate matrix with 0s and 1's based off given probability p
    	for (int i = 0; i < dim; i++) {
	    	for (int j = 0; j < dim; j++){
				// Generate random number for probability
				double rand1 = Math.random();
				
				// To prevent edges that come from same 
				// vertex and point to same vertex
				if (i == j){
				  mat[i][j] = 0;
				}
				else if (mat[i][j] == 0) {
					if (rand1 <= p) {
						edges++; 
	    				mat[i][j] = 1;
	    				//mat[j][i] = 1; 
	    				}
    				else {
    				mat[i][j] = 0;
    				}
				}
    		}
    	}
		return mat;
	}

	// Calcultaes the number of triangles in 1024 vertex graph
    public int numTriangles(double p){

		// generate random matrix
		int d = 1024;
		int [][] randomMat = generateRandomMat(d, p);
		
		// variable to store sum of diagonal entries
		int addDiagonal = 0;

		// find cube of random matrix
		int [][] randomMatSq = strassen(randomMat, randomMat, 32);
		int [][] randomMatCube = strassen (randomMatSq, randomMat, 32);

		// find total of diagonals of the cubed matrix
		for(int i = 0; i < d; i++){ 

			addDiagonal += randomMatCube[i][i];
			//System.out.println("diagonal val: " + addDiagonal);
		}

		int numTriangles = addDiagonal/6;

		return numTriangles;
	}

	public static void main(String[] args) throws FileNotFoundException{
		
		// Declare new object
		Strassen s = new Strassen();

		// Calculates triangles in diff graphs for different probabilities
		/*int p1 = s.numTriangles(.01);
		int p2 = s.numTriangles(.02);
		int p3 = s.numTriangles(.03);
		int p4 = s.numTriangles(.04);
		int p5 = s.numTriangles(.05);

		System.out.println("num triangles when p = .01: " + p1);
		System.out.println("num triangles when p = .02: " + p2);
		System.out.println("num triangles when p = .03: " + p3);
		System.out.println("num triangles when p = .04: " + p4);
		System.out.println("num triangles when p = .05: " + p5);
		*/


		

		/*int dim = 1024;
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
		*/

		
	// Stores dimension and input file from command line
	int dim = Integer.parseInt(args[1]); 
    Scanner input = new Scanner(new File(args[2]));
    
    // Declare matrix to store values from input file 
    int [][] mat1 = new int[dim][dim]; 
    int [][] mat2 = new int[dim][dim];
    int d2 = dim*dim;  
    // Before the file ends, populate matrices 
    while(input.hasNextInt()) {
    	int i = 0; 
		for (int j = 0; j < 2*dim; j++) {
			for (int k = 0; k < dim; k++) {
				if (i < d2) {
					mat1[j][k] = input.nextInt(); 
				}
				else{
					//System.out.println("hiiii");
					mat2[j-dim][k] = input.nextInt();
				}
				i++; 
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

		int k = 2; 

		//Start timer to generate naive time, optional 
		/*long start2 = System.nanoTime(); 
			int[][] tester = s.naive(mat1, mat2);
			long end2 = System.nanoTime(); 
		long elapsed2 = end2 - start2; 
		System.out.println("time: " + elapsed2);*/
		
		//System.out.println("Naive takes " + elapsed2 + " nanoseconds"); 

		// Generates ideal crossover value
		//int testing = s.test1(mat1, mat2, dim, k, 2147483647, k);
		//System.out.println("Resulting matrix: ");
		
		// Runs strassens, random breakpoint 32 tested. 
		int [][] result = s.strassen(mat1, mat2, 32);

		// Prints out diagonal entries 
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (i == j) {
					System.out.print(result[i][j]); 
				}
				//System.out.print(" "); 
			}
			System.out.println();

		}
		System.out.println("");
		

	}
	
}

