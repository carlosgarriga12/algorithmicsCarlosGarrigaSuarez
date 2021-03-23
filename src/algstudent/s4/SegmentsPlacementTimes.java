package algstudent.s4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SegmentsPlacementTimes {

	public static final int TIMES = 100; //Hundreds of milliseconds
	public static void main(String args[]) {
		
		long startTime1, startTime2, startTime3;
		long endTime1,endTime2, endTime3;
		long totalTime1, totalTime2, totalTime3;
		@SuppressWarnings("unused")
		double result1, result2, result3;
		
		System.out.println("--------HUNDREDS OF MILLISECONDS----------");
		
		for (int i = 100; i < Integer.MAX_VALUE; i *= 2) {

			
			List<Integer> segments = fillArray(i);
			
			SegmentsPlacement sp = new SegmentsPlacement(segments);
			
			if(i < 1638400) {
				
				startTime1 = System.currentTimeMillis();
				for(int j = 0; j < TIMES; j++) {
					result1 = sp.greedy1();
				}
				endTime1 = System.currentTimeMillis();
				totalTime1 = (endTime1 - startTime1);
				
				startTime2 = System.currentTimeMillis();
				for(int j = 0; j < TIMES; j++) {
					result2 = sp.greedy2();
				}
				endTime2 = System.currentTimeMillis();
				totalTime2 = (endTime2 - startTime2);
				
				startTime3 = System.currentTimeMillis();
				for(int j = 0; j < TIMES; j++) {
					result3 = sp.greedy2();
				}
				endTime3 = System.currentTimeMillis();
				totalTime3 = (endTime3 - startTime3);
				

			} else {
				System.out.println("---------------------------------");
				startTime1 = System.currentTimeMillis();
				result1 = sp.greedy1();
				endTime1 = System.currentTimeMillis();
				
				totalTime1 = (endTime1 - startTime1);
				
				
				startTime2 = System.currentTimeMillis();
				result2 = sp.greedy2();
				endTime2 = System.currentTimeMillis();
				
				totalTime2 = (endTime2 - startTime2);
				
				
				startTime3 = System.currentTimeMillis();
				result3 = sp.greedy2();
				endTime3 = System.currentTimeMillis();
				
				totalTime3 = (endTime3 - startTime3);
			}
			
			
			if(i < 1600) {
				System.out.println("N = " + i + "\t\tGreedy1 Time:\t" + totalTime1    /* " Result: " + result1*/
						  + "\tGreedy2 Time:\t" + totalTime2    /*" Result: " + result2*/
						  + "\tGreedy3 Time:\t" + totalTime3    /*" Result: " + result3*/);
			} else {
				System.out.println("N = " + i + "\tGreedy1 Time:\t" + totalTime1    /* " Result: " + result1*/
						  + "\tGreedy2 Time:\t" + totalTime2    /*" Result: " + result2*/
						  + "\tGreedy3 Time:\t" + totalTime3    /*" Result: " + result3*/);
			}
			
			
			

		}
	}

	protected static List<Integer> fillArray(int n) {
		List<Integer> list = new ArrayList<>();
		int max = 100;
		int min = 1;
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			list.add(rand.nextInt((max - min) + 1) + min);
		}
		
		return list;
	}

}
