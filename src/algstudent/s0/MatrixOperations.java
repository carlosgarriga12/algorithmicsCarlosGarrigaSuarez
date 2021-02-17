package algstudent.s0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixOperations {
	
	private int size;
	private int [][] matrix;

	/**
	 * Creates a new matrix of size n x n and fills it with random values. 
	 * These random values must be parameterizable between a maximum
	 * (max) and a minimum (min) value.
	 * @param n
	 * @param min
	 * @param max
	 */
	public MatrixOperations(int n, int min, int max) {
		Random r = new Random();
		matrix = new int [n][n];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = r.nextInt((max - min) + 1) + min;
			}
		}
		
		this.size = matrix.length;
	}
	
	/**
	 * Creates a matrix using data of the file provided as parameter. 
	 * This file must have 1 integer number as the first line. 
	 * Following lines contain n values to represent every element of the matrix row. 
	 * Each of the values will be separated by a tabulator.
	 * @param filename
	 * @throws IOException 
	 */
	public MatrixOperations(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String str;

		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null){
		    list.add(str);
		}
		
		String[] lines = list.toArray(new String[0]);
		in.close();
		this.size = Integer.valueOf(lines[0]);
		matrix = new int[size][size];
		String contentOfLine = "";
		for (int i = 0; i < size; i++) {
			contentOfLine = lines[i + 1];
			String [] contentsInArr = lines[i + 1].split("\t");
			int[] row = new int[contentsInArr.length];
			for (int j = 0; j < contentsInArr.length; j++) {
				row[j] = Integer.valueOf(contentsInArr[j]);
			}
			matrix[i] = row;
		}
	}
	
	/**
	 * Returns the matrix size (n).
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Prints in the console all the matrix elements.
	 */
	public void write() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Computes the summation of all the elements of the matrix diagonal.
	 * This implementation must iterate over all the matrix elements,
	 * but only sums appropriate elements. 
	 * So, the complexity is quadratic.
	 * @return
	 */
	public int sumDiagonal1() {
		int sum = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (i == j) {
					sum += matrix[i][j];
				}
			}
		}
		return sum;
	}
	
	/**
	 * Computes the summation of all the elements of the matrix diagonal. 
	 * This second version should only consider the elements of the main diagonal. 
	 * So, the complexity is linear.
	 * @return
	 */
	public int sumDiagonal2() {
		int sum = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			sum+= matrix[i][i];
		}
		return sum;
	}
	
	/**
	 * Given a matrix with integer numbers between 1 and 4,
	 * this method iterates through the matrix starting at position (i, j) 
	 * according to the following number meanings: 
	 * 1 – move up; 2 – move right; 3 – move down; 4 – move left. 
	 * Traversed elements would be set to -1 value. 
	 * The process will finish if it goes beyond the limits 
	 * of the matrix or an already traversed position is reached. 
	 */
	public void travelPath(int i, int j) {
		int x = i;
		int y = j;
		int movements = 0;
		int number = matrix[x][y];
		boolean condition = true;
		matrix[x][y] = -1;
		while(condition) {
			movements++;
			matrix[x][y] = -1;
			switch(number) {
				case 1:
					x -= 1;
					break;
				case 2:
					y += 1;
					break;
				case 3:
					x += 1;
					break;
				case 4:
					y -= 1;
					break;
			}
			if (outOfLimits(x, y)) {
				condition = false;
			} else {
				number = matrix[x][y];
			}
		}
		write();
		System.out.println("Number of movements: " + movements);
	}

	private boolean outOfLimits(int x, int y) {
		if (x > getSize() - 1 || x < 0) {
			return true;
		}
		if (y > getSize() - 1 || y < 0) {
			return true;
		}
		return false;
	}
	
}
