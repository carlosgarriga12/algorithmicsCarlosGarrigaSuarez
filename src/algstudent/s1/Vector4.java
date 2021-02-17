package algstudent.s1;

public class Vector4 {

	public static void main(String arg[]) {
		int times = Integer.parseInt(arg[0]);
		long timeBeforeSum, timeAfterSum;
		long timeBeforeFill, timeAfterFill;
		long timeBeforeMax, timeAfterMax;
		int totalSum = 0;
		int [] v;
		
		for (int n = 10; n < Integer.MAX_VALUE; n*= 3) {
			v = new int[n];
			timeBeforeFill = System.currentTimeMillis();
			for (int j = 1; j <= times; j++) {
				Vector1.fillIn(v);
			}
			timeAfterFill = System.currentTimeMillis();
			
			timeBeforeSum= System.currentTimeMillis();
			for (int j = 1; j <= times; j ++) {
				totalSum = Vector1.sum(v);
			}
			timeAfterSum = System.currentTimeMillis();
			int m [] = new int [2];
			timeBeforeMax = System.currentTimeMillis();
			for (int j = 1; j <= times; j ++) {
				Vector1.maximum(v, m);
			}
			
			timeAfterMax = System.currentTimeMillis();
			
			System.out.println("Size of " + n + ". Time of the Sum = " + (timeAfterSum - timeBeforeSum)
					+ " ms. Time of filling = " +(timeAfterFill - timeBeforeFill)
					+ " Time of maximum = " + (timeAfterMax - timeBeforeMax) + " and the total Sum = " + totalSum);
			
		}
	}

}
