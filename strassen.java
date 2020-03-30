import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.

public class Strassen {

	public int[][] naive(){

	}
	/*
	consider this implementation 
	ArrayList<List<Object>> mat1 = new ArrayList(); 
	ArrayList<Object> listInner = new ArrayList(); 
	linstInner.add()
	*/
	public int[][] strassen(int[][] mat1, int[][] mat2) {
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
				a[i] = mat1.get(i).subList(0, n/2); 
				b[i] = mat1.get(i).subList(n/2, n); 
				e[i] = mat2.get(i).subList(0, n/2); 
				f[i] = mat2.get(i).subList(n/2, n); 
				c[i] = mat1.get(n/2 + i).subList(0, n/2); 
				d[i] = mat1.get(n/2 + i).subList(n/2, n); 
				g[i] = mat2.get(n/2 + i).subList(0, n/2); 
				h[i] = mat2.get(n/2 + i).subList(n/2, n); 

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
			int [][] first = add(subtract(add(p5, p4), p2), p2); 
			int [][] second =  add(p1, p2); 
			int [][] third = add(p3, p4); 
			int [][] fourth = subtract(subtract(add(p5, p1), p3), p7); 
			
			for (int i = 0; i < (n/2); i++) {
				product[i] = ArrayUtils.addAll(first[i], second[i]); 
				product[n/2 + i] = ArrayUtils.addAll(third[i], fourth[i]); 
			}
		}
		
		

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



	public void main(){
		int [] l1 = {1, 1, 1, 1}; 
		int [] l2 = {1, 1, 1, 1}; 
		//int [] l3 = [1, 1, 1, 1]; 
		//int [] l4 = [1, 1, 1, 1]; 
		int [][] test = {l1, l2}; 
		strassen(test, test); 

	}
}