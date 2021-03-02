package algstudent.s2;

//import labs.en._20.lab2sorting.Bubble;
//import labs.en._20.lab2sorting.Insertion;
//import labs.en._20.lab2sorting.QuicksortCentralElement;
//import labs.en._20.lab2sorting.QuicksortFateful;
//import labs.en._20.lab2sorting.QuicksortMedianOfThree;
//import labs.en._20.lab2sorting.Selection;
//import labs.en._20.lab2sorting.Vector;

public class SortingTests {

	/** This program is used to check that sorting algorithms are working
	 * */
	public static void main(String arg []) {
		int i = 640000;  //problem size
		
		
//		testSortingAlgorithm(new Insertion(i));
			
//		testSortingAlgorithm(new Selection(i));
			
//		testSortingAlgorithm(new Bubble(i));
			
//		testSortingAlgorithm(new QuicksortFateful(i));
//			
		testSortingAlgorithm(new QuicksortCentralElement(i));
//			
//		testSortingAlgorithm(new QuicksortMedianOfThree(i));
		
	}
	
	public static void testSortingAlgorithm(Vector v) {
		System.out.println("\n\nSorting test: "+ v.getName());
		
		System.out.println("\nSorting an already-sorted vector");
		v.directlySorted();
		System.out.println("Vector to be sorted:");
		v.write(System.out);	
		long timeBeforeDirectlySort = System.currentTimeMillis();
		v.sort();
		long timeAfterDirectlySort = System.currentTimeMillis() - timeBeforeDirectlySort;
		
		System.out.println("Sorted vector");
		v.write(System.out);

		System.out.println("\nSorting an inverse vector");
		v.inverselySorted();
		System.out.println("Vector to be sorted:");
		v.write(System.out);
		long timeBeforeInverseSort = System.currentTimeMillis();
		v.sort();
		long timeAfterInverseSort = System.currentTimeMillis() - timeBeforeInverseSort;
		
		System.out.println("Sorted vector");
		v.write(System.out);

		System.out.println("\nSorting a random vector");
		v.randomlySorted();
		System.out.println("Vector to be sorted:");
		v.write(System.out);
		long timeBeforeRandomSort = System.currentTimeMillis();
		v.sort();
		long timeAfterRandomSort = System.currentTimeMillis() - timeBeforeRandomSort;
		
		System.out.println("Sorted vector");
		v.write(System.out);
		System.out.println("Time for sort the vector already sorted = " + timeAfterDirectlySort);
		System.out.println("Time for sort the vector inversely sorted = " + timeAfterInverseSort);
		System.out.println("Time for sort the vector randomly sorted = " + timeAfterRandomSort);
		
	}
	
}
