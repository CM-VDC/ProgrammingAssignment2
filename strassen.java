import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
<<<<<<< HEAD
import java.util.Random;
=======
import java.util.Random; 
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;


public class strassen {

	public int[][] naive(int [][] matrix1, int [][] matrix2){

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
	public int[][] strassen(int[][] mat1, int[][] mat2, int k) {

		int n = mat1.length;
		int[][] product = new int[n][n];
<<<<<<< HEAD

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
=======
		
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
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
				int [][] p1 = strassen(a, subtract(f, h), k);
				int [][] p2 = strassen(add(a, b), h, k);
				int [][] p3 = strassen(add(c, d), e, k);
				int [][] p4 = strassen(d, subtract(g, e), k);
				int [][] p5 = strassen(add(a, d), add(e, h), k);
				int [][] p6 = strassen(subtract(b, d), add(g, h), k);
				int [][] p7 = strassen(subtract(a, c), add(e, f), k);
<<<<<<< HEAD

				// Multiplies split components
=======
				
				// Multiplies split components 
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
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
<<<<<<< HEAD

=======
		
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
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
<<<<<<< HEAD

    	// Check if tested dimension exceeds dimension of matrix
    	if (k > dim) {
    		// Default "Breaking Point" is 2
=======
    	
    	// Check if tested dimension exceeds dimension of matrix
    	if (k > dim) {
    		// Default "Breaking Point" is 2 
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
    		if (breakpt == 2) {
    			System.out.println("n0 not found. ");
    		}
    		else {
    			System.out.println("Optimal Breakpoint: " + breakpt);
    		}
<<<<<<< HEAD

    		return breakpt;
    	}
    	else {

	    	/*System.out.println("Matrix 1: ");
=======
    		
    		return breakpt;  
    	}
    	else {
    		
	    	/*System.out.println("Matrix 1: "); 
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
	    	for(int i = 0; i < dim; i++){
				for(int j = 0; j < dim; j++){
					System.out.print(mat1[i][j] + " ");
				}
				System.out.println();
			}
<<<<<<< HEAD
			System.out.println("Matrix 2: ");
	    	for(int i = 0; i < dim; i++){
				for(int j = 0; j < dim; j++){
					System.out.print(mat2[i][j] + " ");
				}
				System.out.println();
			}
			*/
			System.out.println("n: " + k);
			// Start timer
			long start1 = System.nanoTime();
			// Run strassen
			int [][] product = strassen(mat1, mat2, k);
			long end1 = System.nanoTime();
			long elapsed1 = end1 - start1;
			System.out.println("Strassens takes " + elapsed1 + " nanoseconds");

			/*long start2 = System.nanoTime();
			int[][] tester = naive(mat1, mat2);
			long end2 = System.nanoTime();
			long elapsed2 = end2 - start2;
			System.out.println("Naive takes " + elapsed2 + " nanoseconds");
			*/

			if (elapsed1 < time) {
				System.out.println("n0: " + k);
				System.out.println("time spent: " + elapsed1);
				test1(mat1, mat2, dim, k*2, elapsed1, k);
			}
			else
				test1(mat1, mat2, dim, k*2, time, breakpt);

			return breakpt;
	    }

    }

		public int[][] generateRandomMat(int n, double p){

		// create empty matrix of size n
		int dim = n;
		int [][] mat = new int[dim][dim];

		// populate matrix with 0s and 1's based off given probability p
    	for (int i = 0; i < dim; i++) {
	    		for (int j = 0; j < dim; j++){
						// generate random number for probability
						double rand1 = Math.random();

						// to prevent edges that come from same vertex and point to same vertex
						if (i == j){

						  mat[i][j] = 0;

						}
						// if p is less than or equal to rand
	    			else if (rand1 <= p) {

	    				mat[i][j] = 1;

	    			}
	    			else {
	    				mat[i][j] = 0;
	    			}

	    			}
	    		}

	return mat;
	}

	public static void main(String[] args) throws FileNotFoundException{


=======

			System.out.println("Matrix 2: "); 
	    	for(int i = 0; i < dim; i++){
				for(int j = 0; j < dim; j++){
					System.out.print(mat2[i][j] + " ");
				}
				System.out.println();
			}
			*/
			System.out.println("n: " + k);
			// Start timer 
			long start1 = System.nanoTime(); 
			// Run strassen
			int [][] product = strassen(mat1, mat2, k);
			long end1 = System.nanoTime(); 
			long elapsed1 = end1 - start1; 
			System.out.println("Strassens takes " + elapsed1 + " nanoseconds");

			/*long start2 = System.nanoTime(); 
			int[][] tester = naive(mat1, mat2);
			long end2 = System.nanoTime(); 
			long elapsed2 = end2 - start2; 
			System.out.println("Naive takes " + elapsed2 + " nanoseconds"); 
			*/
			
			if (elapsed1 < time) {
				System.out.println("n0: " + k);
				System.out.println("time spent: " + elapsed1); 
				test1(mat1, mat2, dim, k*2, elapsed1, k);
			}
			else
				test1(mat1, mat2, dim, k*2, time, breakpt);
				
			return breakpt; 
	    }
    	
    }

	public static void main(String[] args) throws FileNotFoundException{
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
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d

		/*int [] l1 = {2, 1, 4, 7};
		int [] l2 = {4, 6, 7, 100};
		int [] l3 = {7529, 42, 1, 7};
		int [] l4 = {4, 7, 2, 9};
<<<<<<< HEAD
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
=======

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

>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
		System.out.print("Naive result: \n");
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				System.out.print(tester[i][j] + " ");
			}
			System.out.println();
		} */
<<<<<<< HEAD
	int dim = Integer.parseInt(args[1]);
	Scanner scn = new Scanner(new File(args[2]));

    int size = scn.nextInt();
    int [] array = new int[size];
    Scanner input = new Scanner(new File(args[2]));

    /*for (int i = 0; i < size; i ++ ) {
    	int j = hi.nextInt();
    	System.out.println("this is a test: " + j);
    }*/
    int [][] mat1 = new int[dim][dim];
    int [][] mat2 = new int[dim][dim];
    int d2 = dim*dim;
    while(input.hasNextInt()) {
    	int i = 0;
		for (int j = 0; j < 2*dim; j++) {
			for (int k = 0; k < dim; k++) {
				if (i < d2) {
					mat1[j][k] = input.nextInt();
=======
	int dim = Integer.parseInt(args[1]); 
	Scanner scn = new Scanner(new File(args[2]));

    int size = scn.nextInt(); 
    int [] array = new int[size]; 
    Scanner input = new Scanner(new File(args[2]));
    
    /*for (int i = 0; i < size; i ++ ) {
    	int j = hi.nextInt(); 
    	System.out.println("this is a test: " + j);
    }*/
    int [][] mat1 = new int[dim][dim]; 
    int [][] mat2 = new int[dim][dim];
    int d2 = dim*dim;  
    while(input.hasNextInt()) {
    	int i = 0; 
		for (int j = 0; j < 2*dim; j++) {
			for (int k = 0; k < dim; k++) {
				if (i < d2) {
					mat1[j][k] = input.nextInt(); 
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
				}
				else{
					//System.out.println("hiiii");
					mat2[j-dim][k] = input.nextInt();
				}
<<<<<<< HEAD
				i++;
=======
				i++; 
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
			}
		}
    }

<<<<<<< HEAD
    System.out.println("Matrix 1: ");
=======
    System.out.println("Matrix 1: "); 
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
	    	for(int i = 0; i < dim; i++){
				for(int j = 0; j < dim; j++){
					System.out.print(mat1[i][j] + " ");
				}
				System.out.println();
			}
<<<<<<< HEAD
	System.out.println("Matrix 2: ");
=======
	System.out.println("Matrix 2: "); 
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
	    	for(int i = 0; i < dim; i++){
				for(int j = 0; j < dim; j++){
					System.out.print(mat2[i][j] + " ");
				}
				System.out.println();
<<<<<<< HEAD
=======
			}
    

    /*while(input.hasNextInt())
	{
   		int j = input.nextInt();
   		System.out.println("this is a test: " + j);
    }
    */
		strassen s = new strassen();
		
		int k = 2; 
		long start2 = System.nanoTime(); 
			int[][] tester = s.naive(mat1, mat2);
			long end2 = System.nanoTime(); 
		long elapsed2 = end2 - start2; 
		System.out.println("Naive takes " + elapsed2 + " nanoseconds"); 
		int testing = s.test1(mat1, mat2, dim, k, elapsed2, k);
		System.out.println("Resulting matrix: ");
		
		int [][] result = s.strassen(mat1, mat2, testing);


		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (i == j) {
					System.out.print(result[i][j] + " "); 
				}
				System.out.print(" "); 
>>>>>>> 62fa2daef9943339a61d2aa8efd292b4445b515d
			}
			System.out.println();


    /*while(input.hasNextInt())
	{
   		int j = input.nextInt();
   		System.out.println("this is a test: " + j);
    }
    */
		strassen s = new strassen();

		int k = 2;
		long start2 = System.nanoTime();
			int[][] tester = s.naive(mat1, mat2);
			long end2 = System.nanoTime();
		long elapsed2 = end2 - start2;
		System.out.println("Naive takes " + elapsed2 + " nanoseconds");
		int testing = s.test1(mat1, mat2, dim, k, elapsed2, k);
		System.out.println("Resulting matrix: ");

		int [][] result = s.strassen(mat1, mat2, testing);


		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (i == j) {
					System.out.print(result[i][j] + " ");
				}
				System.out.print(" ");
			}
			System.out.println();

		}
		


	}


	// function that calculates the number of triangles
	public int numTriangles(int p){

		// generate random matrix
		int [][] randomMat = generateRandomMat(1024, p);


		// variable to store sum of diagonal entries
		int addDiagonal = 0;

		// find cube of random matrix
		int [][] randomMatSq = strassen(randomMat, randomMat, 1024);
		int [][] randomMatCube = strassen (randomMatSq, randomMat, 1024);

		// find total of diagonals of the cubed matrix
		for(int i = 0; i < 1024; i++){

			addDiagonal =+ randomMatCube[i][i];
		}

		int numTriangles = addDiagonal/6;

		return numTriangles;
	}
}
