package algstudent.s32;

import java.util.List;

public class Inversions {

	private List<Integer> ranking;

	public Inversions(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public String start() {
		
		return String.valueOf(mergeSort(0, this.ranking.size() - 1));
	}
	
	public long mergeSort(int left, int right) {
		long inversions = 0;
		if (left < right) {
			int center = (left + right) / 2;
			
			inversions += mergeSort(left, center);
			inversions += mergeSort(center + 1, right);
			
			inversions += combine(left, center, center + 1, right, this.ranking);
		}
		
//		System.out.println("Printing ranking: " + ranking.toString());
		
		return inversions;
	}

	private long combine(int x1, int x2, int y1, int y2, List<Integer> ranking) {
		long inversions = 0;
		
		int sizeX = x2 - x1 + 1;
		int sizeY = y2 - y1 + 1;
		int[] x = new int[sizeX];
		int[] y = new int[sizeY];

		// Copy the elements from left to center into a helper
		for (int i = 0; i < sizeX; i++)
			x[i] = ranking.get(x1 + i);
		// Copy the elements from center+1 to right into a helper
		for (int i = 0; i < sizeY; i++) {
			y[i] = ranking.get(y1 + i);
		}
		
//		System.out.println(Arrays.toString(x));
//		System.out.println(Arrays.toString(y));

		// Copy the smallest elements from either the left or the right

		int posX = 0;   //Index of the left array
		int posY = 0;	//Index of the right array
		int i = x1;
		while (posX < x.length && posY < y.length) {
			if (x[posX] <= y[posY]) {
				ranking.set(i++, x[posX++]);
			} else {
				ranking.set(i++, y[posY++]);
				inversions += (x2 + 1) - (x1 + posX);
			}
		}

		// Copy the rest of the elements into the collection
		while (posX < x.length) {
			ranking.set(i++, x[posX++]);
		}
		
		while (posY < y.length) {
			ranking.set(i++, y[posY++]);
		}


		
		return inversions;
	}
	
	public List<Integer> getRanking() {
		return this.ranking;
	}

}
