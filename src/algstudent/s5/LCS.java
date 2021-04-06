package algstudent.s5;

import java.util.Random;

public class LCS {
	
	CellTable[][] table; //table with local values
	String str1; // 1st string
	String str2; // 2nd string
	String result; // result string
	int size1; //str1 size
	int size2; //str2 size
	
	/**
	 * Constructor
	 * @param s1 1st string to compare
	 * @param s2 2nd string to compare
	 */
	public LCS(String s1, String s2) {
		str1 = "*" + s1; //str1. 1st column is headed with asterisk
		str2 = "*" + s2; //str2. 1st row is headed with asterisk
		System.out.println("String1: " + str1);
		System.out.println("String2: " + str2);
		size1 = str1.length();
		size2 = str2.length();
		result = "";
		table = new CellTable[size1][size2]; // table used for dynamic programming 
	}
	
	/**
	 * Constructor used to measure times
	 * @param n size for the strings that are generated randomly 
	 */
	public LCS(int n) {
		str1 = "*" + this.genRandomSeq(n);
		str2 = "*" + this.genRandomSeq(n);
		size1 = str1.length();
		size2 = str2.length();
		result = "";
		table = new CellTable[size1][size2]; // table used for dynamic programming para guardar todos los valores de la tabla dinámica 
	}
	
	/**
	 * Generates a random sequence
	 * @param n sequence size
	 * @return random sequence, sting of characters
	 */
	private String genRandomSeq(int n){
		char[] dna_elements = {'A', 'C', 'G', 'T'};
		String result = "";
		Random r = new Random();
		for (int i=0; i<n; i++)
			result += dna_elements[r.nextInt(4)];
		return result;
	}
	
	/**
	 * Table values initialization
	 */
	public void initTable() {
		for (int j=0; j<size2; j++)
			for(int i=0; i<size1; i++)
				table[i][j] = new CellTable();
	}
	
	
	/**
	 * Print the table with the results
	 */
	public void printTable() {
		System.out.printf("%11s", "*");
		for (int i=0; i<size1; i++) 
			System.out.printf("%11s", str1.substring(i, i+1)); // chars str1 (horizontal)
		System.out.println();
		
		for (int j=0; j<size2; j++) {
			System.out.printf("%11s", str2.substring(j, j+1)); // chars str2 (vertical)
			for(int i=0; i<size1; i++)
				System.out.printf("%4d(%2d,%2d)", table[i][j].value, table[i][j].iPrev, table[i][j].jPrev); // table values
			System.out.println();
		}
	}
	
	
	/**
	 * Print current MSC result
	 */
	public void printLongestSubseq(){
		System.out.println(result);
	} 

	public class CellTable{
		public int value; // value for dynamic programming
		public int iPrev; //"pointer" to string 1 character used to compute value 
		public int jPrev; //"pointer" to string 2 character used to compute value
	}
	
	/**
	 * Fill table values for dynamic programming
	 */
	public void fillTable(){
		for (int i = 0; i < size1; i++) {
			for (int j = 0; j < size2; j++) {
				if(i == 0 || j == 0) {
					table[i][j].value = 0;
					table[i][j].iPrev = 0;
					table[i][j].jPrev = 0;
				} else if ( str1.charAt(i) == str2.charAt(j) ){
					table[i][j].value = table[i - 1][j - 1].value + 1;
					table[i][j].iPrev = i - 1;
					table[i][j].jPrev = j - 1;
				} else {
					if(table[i - 1][j].value > table[i][j - 1].value) {
						table[i][j].value = table[i - 1][j].value;
						table[i][j].iPrev = i - 1;
						table[i][j].jPrev = j;
					} else {
						table[i][j].value = table[i][j - 1].value;
						table[i][j].iPrev = i;
						table[i][j].jPrev = j - 1;
					}
				}
			}
		}
	}
	
	/**
	 * Get the index for the maximum of three numbers
	 * @param num1 e.g. input L1=MSC(S1’, S2). S1’ S1 without its last char
	 * @param num2 e.g. input L1=MSC(S1, S2'). S2' S2 without its last char
	 * @param num3 e.g. input L3=MSC(S1’, S2’) or L3+1 when both current chars are equal
	 * @return index of maximum
	 */
	@SuppressWarnings("unused")
	private int longest(int num1, int num2, int num3) {
		return -1;
	}
	
	/**
	 * Find the MSC from the table (dynamic programming)
	 * @param v if True verbose mode activated (To show the path trough the different cells)
	 */
	public void findLongestSubseq(boolean v){
		if (v) {
			int i = size1 - 1;
			int j = size2 - 1;
			int actualValue = table[i][j].value;
			while(i > 0 && j > 0) {
				System.out.println("( "+i + ", "+ j + ")");
				if(table[table[i][j].iPrev][table[i][j].jPrev].value != actualValue) {
					result = String.valueOf(str1.charAt(i)) + result;
					System.out.println(" Char: " + String.valueOf(str1.charAt(i))
					+ " added to the start of the string. Actual Result: " + result);
				}
				int auxI = i;
				int auxJ = j;
				i = table[auxI][auxJ].iPrev;
				j = table[auxI][auxJ].jPrev;
				actualValue = table[i][j].value;
			}
		} else {
			int i = size1 - 1;
			int j = size2 - 1;
			int actualValue = table[i][j].value;
			while(i > 0 && j > 0) {
				if(table[table[i][j].iPrev][table[i][j].jPrev].value != actualValue) {
					result = String.valueOf(str1.charAt(i)) + result; 
				}
				int auxI = i;
				int auxJ = j;
				i = table[auxI][auxJ].iPrev;
				j = table[auxI][auxJ].jPrev;
				actualValue = table[i][j].value;
			}
		}
	}
}
