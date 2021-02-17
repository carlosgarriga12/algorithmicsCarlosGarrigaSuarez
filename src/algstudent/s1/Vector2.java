package algstudent.s1;

public class Vector2 {
	public static void main(String arg []){
		int n = Integer.parseInt(arg[0]); //Size of the problem in the first argument
		int[] v = new int[n];
		Vector1.fillIn(v);
		long timeBefore = System.currentTimeMillis();
		Vector1.sum(v);
		long timeAfter = System.currentTimeMillis();
		long totalTime = timeAfter - timeBefore;
		
		System.out.println("Time taken to execute the sum of elements in the vector: "+ totalTime);
	}
}
