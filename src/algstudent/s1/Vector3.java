package algstudent.s1;

public class Vector3 {
	static int [] v;
	
	public static void main(String arg[]) {
		int totalSum;
		long timeBefore, timeAfter;
		for (int n = 10; n <= Integer.MAX_VALUE; n *= 5) {
			
			v = new int[n];
			Vector1.fillIn(v);
			
			timeBefore = System.currentTimeMillis();
			
			totalSum = Vector1.sum(v);
			
			timeAfter = System.currentTimeMillis();
			
			System.out.println("Size of " + n + ". Time = " + (timeAfter - timeBefore) + " ms and the total Sum = " + totalSum);
			
		}
	}
}
