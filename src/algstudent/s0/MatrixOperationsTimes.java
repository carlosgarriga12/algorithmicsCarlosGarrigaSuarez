package algstudent.s0;

public class MatrixOperationsTimes {

	public static void main(String arg[]) {
		int times = Integer.parseInt(arg[0]);
		long timeBeforeSum1, timeAfterSum1;
		long timeBeforeSum2, timeAfterSum2;
		
		for (int n = 10 ; n <= Integer.MAX_VALUE; n*=3 ) {
			MatrixOperations mo = new MatrixOperations(n, 1, 5);
			
			timeBeforeSum1 = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				mo.sumDiagonal1();
			}
			timeAfterSum1 = System.currentTimeMillis();
			
			timeBeforeSum2 = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				mo.sumDiagonal2();
			}
			timeAfterSum2 = System.currentTimeMillis();
			
			System.out.print("Size of " + n + " Time taken sumDiagonal1 = " + (timeAfterSum1 - timeBeforeSum1));
			System.out.println(" Time taken sumDiagonal2 = " + (timeAfterSum2 - timeBeforeSum2));
		}
	}

}
